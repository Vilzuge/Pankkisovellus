package com.example.pankkisovellus.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.pankkisovellus.Account;
import com.example.pankkisovellus.DatabaseHelper;
import com.example.pankkisovellus.R;

import com.example.pankkisovellus.RecyclerViewAdapter;
import com.example.pankkisovellus.User;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity implements RecyclerViewAdapter.OnAccountListener {

    TextView nameText;
    User user;
    Account acc;
    DatabaseHelper databaseHelper;
    ArrayList<Account> account_array = new ArrayList<Account>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        user = (User) getIntent().getSerializableExtra("user");
        databaseHelper = new DatabaseHelper(Dashboard.this);
        nameText = (TextView) findViewById(R.id.textViewName);
        nameText.setText(user.getUserName());

        account_array = databaseHelper.fetchUserAccounts(user);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(Dashboard.this, account_array, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Dashboard.this));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void changeInformation(View v) {
        Intent intent = new Intent(getBaseContext(), ChangeUserInformation.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void createNewAccount(View v) {
        Intent intent = new Intent(getBaseContext(), CreateNewAccount.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
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

    @Override
    public void onAccountClick(int position) {
        acc = account_array.get(position);
        System.out.println( acc.getAccountName() );
        Intent intent = new Intent(getBaseContext(), AccountInformation.class);
        intent.putExtra("account", acc);
        startActivity(intent);
    }
}
