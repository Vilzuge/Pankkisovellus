package com.example.pankkisovellus.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pankkisovellus.Account;
import com.example.pankkisovellus.DatabaseHelper;
import com.example.pankkisovellus.R;

import com.example.pankkisovellus.RecyclerViewAdapter;
import com.example.pankkisovellus.User;

import java.util.ArrayList;

public class AccountInformation extends AppCompatActivity {

    User user;
    DatabaseHelper databaseHelper;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_information);
        user = (User) getIntent().getSerializableExtra("user");
        databaseHelper = new DatabaseHelper(AccountInformation.this);
        ArrayList<Account> account_array = databaseHelper.fetchUserAccounts(user);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(AccountInformation.this, account_array);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AccountInformation.this));


        for(int i = 0; i < account_array.size(); i++) {
            System.out.println(account_array.get(i).getAccountId());
            System.out.println(account_array.get(i).getAccountHolder());
            System.out.println(account_array.get(i).getAccountName());
            System.out.println(account_array.get(i).getAccountType());
            System.out.println(account_array.get(i).getAccountBalance());
            System.out.println(account_array.get(i).getAccountLimit());
            System.out.println("**********************");
        }

    }

    public void createNewAccount(View v) {
        Intent intent = new Intent(getBaseContext(), CreateNewAccount.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void depositOrWithdraw(View v) {
        Intent intent = new Intent(getBaseContext(), DepositOrWithdraw.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void newPayment(View v) {
        Intent intent = new Intent(getBaseContext(), NewPayment.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }


}
