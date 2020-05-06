package com.example.pankkisovellus.activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pankkisovellus.Account;
import com.example.pankkisovellus.DatabaseHelper;
import com.example.pankkisovellus.R;
import com.example.pankkisovellus.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ChangeAccountInformation extends AppCompatActivity {

    User user;
    Account account;
    EditText editAccountLimit;
    DatabaseHelper databaseHelper;
    String accName, accType;
    float accPayLimit, accBalance;
    int accId, accHolder;
    static String itemvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_account_information);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        account = (Account) extras.getSerializable("account");
        user = (User) extras.getSerializable("user");
        editAccountLimit = (EditText) findViewById(R.id.editPaylimit);
        Spinner spinnerType = (Spinner) findViewById(R.id.spinnerAccountType);
        databaseHelper = new DatabaseHelper(ChangeAccountInformation.this);

        //Spinner for choosing the account type
        List<String> list = new ArrayList<>();
        list.add("Checking");
        list.add("Savings");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ChangeAccountInformation.this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);
        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemvalue = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void changeAccountInformation(View v) {
        accId = account.getAccountId();
        accHolder = account.getAccountHolder();
        accName = account.getAccountName();
        accBalance = account.getAccountBalance();
        accType = itemvalue;
        try {
            accPayLimit = Float.parseFloat(editAccountLimit.getText().toString());
        } catch (Exception e) {
            Toast.makeText(ChangeAccountInformation.this, "Pay limit not in float format.", Toast.LENGTH_LONG).show();
        }

        Account tempAccount = new Account(accId, accHolder, accName, accType, accBalance, accPayLimit);
        if (databaseHelper.alterAccount(tempAccount) != null) {
            Toast.makeText(ChangeAccountInformation.this, "Successfully changed information.", Toast.LENGTH_LONG).show();
            account = tempAccount;
            Bundle extras2 = new Bundle();
            extras2.putSerializable("account", account);
            extras2.putSerializable("user",user);
            Intent intent = new Intent(getBaseContext(), AccountInformation.class);
            intent.putExtras(extras2);
            startActivity(intent);
        } else {
            Toast.makeText(ChangeAccountInformation.this, "Changing account information failed.", Toast.LENGTH_LONG).show();
            account = account;
        }
    }
}
