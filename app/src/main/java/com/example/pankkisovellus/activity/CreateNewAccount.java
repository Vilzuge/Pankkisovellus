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
import java.util.List;

public class CreateNewAccount extends AppCompatActivity {

    private EditText editAccountName, editAccountLimit;
    static String itemvalue;
    User user;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);
        user = (User) getIntent().getSerializableExtra("user");
        editAccountName = (EditText) findViewById(R.id.editAccountName);
        editAccountLimit = (EditText) findViewById(R.id.editAccountLimit);
        Spinner spinnerType = (Spinner) findViewById(R.id.spinnerType);
        databaseHelper = new DatabaseHelper(CreateNewAccount.this);

        //Spinner for choosing the account type
        List<String> list = new ArrayList<>();
        list.add("Checking");
        list.add("Savings");
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
    //If back is pressed in "CreateNewAccount"-view, transition back to "Dashboard" by running the view again
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getBaseContext(), Dashboard.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }

    //Creating a new account for the user by calling databaseHelpers method newAccount()
    public void CreateAccount(View v) {
        int accountId = 0;
        int accountHolder = user.getUserId();
        String accountName = editAccountName.getText().toString();
        String accountType = itemvalue;
        float accountBalance = 0.0f;
        float accountLimit = Float.parseFloat(editAccountLimit.getText().toString());
        boolean isValidAccount = databaseHelper.isUniqueAccount(accountHolder, accountName);

        if (!isValidAccount) {
            Toast.makeText(CreateNewAccount.this,"You already have a account with that name.", Toast.LENGTH_LONG).show();
        }

        //Creating the account from the given information.
        if(isValidAccount) {
            Account account = new Account(accountId, accountHolder, accountName, accountType, accountBalance, accountLimit);
            boolean worked = databaseHelper.newAccount(account);
            if (worked) {
                Toast.makeText(CreateNewAccount.this,"Successfully created an account.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getBaseContext(), Dashboard.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(CreateNewAccount.this,"Creating an account failed.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
