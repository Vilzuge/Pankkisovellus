package com.example.pankkisovellus.activity;

import androidx.appcompat.app.AppCompatActivity;

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

            //Moving back to the AccountInformation window
            Intent intent = new Intent(getBaseContext(), AccountInformation.class);
            intent.putExtra("user", user);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(NewPayment.this,"Failed to transfer the money", Toast.LENGTH_LONG).show();
        }
    }


}
