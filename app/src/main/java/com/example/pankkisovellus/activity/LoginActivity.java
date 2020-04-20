package com.example.pankkisovellus.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.pankkisovellus.R;

public class LoginActivity extends AppCompatActivity {

    public static EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username = (EditText) findViewById(R.id.editUsername);
        password = (EditText) findViewById(R.id.editPassword);

    }

    public void Login(View w) {
        String userUsername = username.getText().toString();
        String userPassword = password.getText().toString();

    }

    public void CreateUser(View v) {
        Intent intent = new Intent(getBaseContext(), CreateNewUser.class);
        startActivity(intent);
    }

}
