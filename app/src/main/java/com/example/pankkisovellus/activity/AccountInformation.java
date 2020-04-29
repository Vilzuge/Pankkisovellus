package com.example.pankkisovellus.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pankkisovellus.R;
import com.example.pankkisovellus.User;

public class AccountInformation extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_information);
        user = (User) getIntent().getSerializableExtra("user");

    }




    public void createNewAccount(View v) {
        Intent intent = new Intent(getBaseContext(), CreateNewAccount.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void newPayment(View v) {
        //Tähän siirtyminen maksuihin
    }


}
