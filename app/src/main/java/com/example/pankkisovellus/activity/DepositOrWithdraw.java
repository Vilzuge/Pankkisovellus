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


public class DepositOrWithdraw extends AppCompatActivity {

    EditText editAmount;
    Spinner spinnerAccount, spinnerAction;
    DatabaseHelper databaseHelper;
    User user;
    static String accountValue, actionValue;
    static Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_or_withdraw);
        user = (User) getIntent().getSerializableExtra("user");
        editAmount = (EditText) findViewById(R.id.editAmount);
        spinnerAccount = (Spinner) findViewById(R.id.spinnerAccount);
        spinnerAction = (Spinner) findViewById(R.id.spinnerAction);
        databaseHelper = new DatabaseHelper(DepositOrWithdraw.this);


        //Configuring the AccountSpinner
        ArrayList<Account> account_array = databaseHelper.fetchUserAccounts(user);
        ArrayAdapter<Account> adapterAccount = new ArrayAdapter<Account>(DepositOrWithdraw.this, android.R.layout.simple_spinner_item, account_array);
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

        //Configuring the ActionSpinner
        List<String> list = new ArrayList<>();
        list.add("Withdraw");
        list.add("Deposit");
        ArrayAdapter<String> adapterAction = new ArrayAdapter<String>(DepositOrWithdraw.this, android.R.layout.simple_spinner_item, list);
        adapterAction.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAction.setAdapter(adapterAction);
        spinnerAction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                actionValue = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void executeAction(View v) {
        float amount = Float.parseFloat(editAmount.getText().toString());
        boolean worked = databaseHelper.depositOrWithdraw(user, account, actionValue, amount);
        if (worked == true) {
            if (actionValue == "Withdraw") {
                Toast.makeText(DepositOrWithdraw.this,"Successfully withdrew the amount of money.", Toast.LENGTH_LONG).show();
            } else if (actionValue == "Deposit") {
                Toast.makeText(DepositOrWithdraw.this,"Successfully deposited the amount of money.", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(DepositOrWithdraw.this,"Action failed", Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(getBaseContext(), AccountInformation.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }


}
