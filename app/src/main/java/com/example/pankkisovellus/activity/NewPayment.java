package com.example.pankkisovellus.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pankkisovellus.Account;
import com.example.pankkisovellus.DatabaseHelper;
import com.example.pankkisovellus.R;
import com.example.pankkisovellus.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;

public class NewPayment extends AppCompatActivity {

    EditText editAmount, editReceiverAcc, editReceiverUser;
    Spinner spinnerAccount;
    DatabaseHelper databaseHelper;
    static String accountValue;
    static Account account;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_payment);
        user = (User) getIntent().getSerializableExtra("user");
        spinnerAccount = (Spinner) findViewById(R.id.spinnerAccount);
        editReceiverAcc = (EditText) findViewById(R.id.editReceiverAcc);
        editReceiverUser = (EditText) findViewById(R.id.editReceiverUser);
        editAmount = (EditText) findViewById(R.id.editAmount);
        databaseHelper = new DatabaseHelper(NewPayment.this);


        ArrayList<Account> account_array = databaseHelper.fetchUserAccounts(user);
        ArrayAdapter<Account> adapterAccount = new ArrayAdapter<Account>(NewPayment.this, android.R.layout.simple_spinner_item, account_array);
        adapterAccount.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAccount.setAdapter(adapterAccount);
        spinnerAccount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                accountValue = parent.getItemAtPosition(position).toString();
                account = (Account) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }

    public void transferMoney(View v) {
        Account payerAccount = account;
        String receiverAccount = editReceiverAcc.getText().toString();
        String receiverUser = editReceiverUser.getText().toString();
        float amount = Float.parseFloat(editAmount.getText().toString());
        boolean receiverExists = databaseHelper.receiverExists(receiverAccount, receiverUser);

        if ((account.getAccountBalance() > amount) && receiverExists) {
            databaseHelper.transferMoney(payerAccount, receiverAccount, receiverUser, amount);
            Toast.makeText(NewPayment.this,"Succesfully transferred the money", Toast.LENGTH_LONG).show();

            //Writing tranfer information to files, and printing a receipt
            printReceipt(user.getUserName(), account.getAccountName(), receiverAccount, receiverUser, amount);
            writeTransferEvent(user.getUserName(), receiverUser, account.getAccountName(), receiverAccount, amount);

            //Moving back to the AccountInformation window
            Intent intent = new Intent(getBaseContext(), Dashboard.class);
            intent.putExtra("user", user);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(NewPayment.this,"Failed to transfer the money", Toast.LENGTH_LONG).show();
        }
    }

    public void writeTransferEvent(String payerUser, String receiverUser, String payerAccount, String receiverAccount, float amount) {
        //Payer write
        StringBuilder dataPayer = new StringBuilder();
        dataPayer.append("Transfer/"+payerUser+"/"+receiverUser+"/"+String.valueOf(amount)+"\n");
        try {
            FileOutputStream fOut = openFileOutput(payerUser+"_"+payerAccount+".csv", MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.write(dataPayer.toString());
            osw.close();
            fOut.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        //Receiver write
        StringBuilder dataReceiver = new StringBuilder();
        dataReceiver.append("Transfer/"+receiverUser+"/"+payerUser+"/"+String.valueOf(amount)+"\n");
        try {
            FileOutputStream fOut = openFileOutput(receiverUser+"_"+receiverAccount+".csv", MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.write(dataReceiver.toString());
            osw.close();
            fOut.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }


    public void printReceipt(String payerName,String payerAccount, String receiverAccount, String receiverUser, float amount) {
        StringBuilder data = new StringBuilder();
        data.append("Payer Username: "+payerName+"\nPayer Account: "+payerAccount+"\nReceiver Account: "+receiverAccount+"\nReceiver Username: "+receiverUser+"\nAmount: "+String.valueOf(amount));
        try {
            //saving the file into device
            FileOutputStream out = openFileOutput("receipt.csv", Context.MODE_PRIVATE);
            out.write((data.toString()).getBytes());
            out.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }


}
