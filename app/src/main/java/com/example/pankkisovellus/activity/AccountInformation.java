package com.example.pankkisovellus.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.pankkisovellus.Account;
import com.example.pankkisovellus.EventInformation;
import com.example.pankkisovellus.R;
import com.example.pankkisovellus.User;

import java.util.ArrayList;

public class AccountInformation extends AppCompatActivity {

    User user;
    Account account;
    TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_information);
        account = (Account) getIntent().getSerializableExtra("account");
        header = (TextView) findViewById(R.id.textAccountName);
        header.setText(account.getAccountName());

    }


}
