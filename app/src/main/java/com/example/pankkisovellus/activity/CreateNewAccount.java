package com.example.pankkisovellus.activity;

import androidx.appcompat.app.AppCompatActivity;

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
import java.util.List;

public class CreateNewAccount extends AppCompatActivity {

    EditText editAccountName, editAccountLimit;
    Spinner spinnerType;
    static String itemvalue;
    String accountName, accountType;
    int accountHolder, accountId;
    float accountBalance, accountLimit;
    User user;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);
        user = (User) getIntent().getSerializableExtra("user");
        databaseHelper = new DatabaseHelper(CreateNewAccount.this);

        editAccountName = (EditText) findViewById(R.id.editAccountName);
        editAccountLimit = (EditText) findViewById(R.id.editAccountLimit);
        spinnerType = (Spinner) findViewById(R.id.spinnerType);

        //Choices for account type
        List<String> list = new ArrayList<>();
        list.add("Debit");
        list.add("Credit");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CreateNewAccount.this, android.R.layout.simple_spinner_item, list);
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

    public void CreateAccount(View v) {
        accountId = 0;
        accountHolder = user.getUserId();
        accountName = editAccountName.getText().toString();
        accountType = itemvalue;
        accountBalance = 0.0f;
        accountLimit = Float.parseFloat(editAccountLimit.getText().toString());
        Account account = new Account(accountId, accountHolder, accountName, accountType, accountBalance, accountLimit);
        boolean worked = databaseHelper.newAccount(account);

        if (worked == true) {
            Toast.makeText(CreateNewAccount.this,"Successfully created an account.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(CreateNewAccount.this,"Creating an account failed.", Toast.LENGTH_LONG).show();
        }
    }
}
