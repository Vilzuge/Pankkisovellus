package com.example.pankkisovellus.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pankkisovellus.DatabaseHelper;
import com.example.pankkisovellus.R;

public class LoginActivity extends AppCompatActivity {

    public static EditText username, password;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.editUsername);
        password = (EditText) findViewById(R.id.editPassword);
        databaseHelper = new DatabaseHelper(this);
    }

    public void Login(View w) {
        String userUsername = username.getText().toString();
        String userPassword = password.getText().toString();

        if (databaseHelper.tryLogging(userUsername, userPassword)) {
            Toast.makeText(LoginActivity.this,"Successfully Logged In", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(LoginActivity.this,"Invalid Username/Password", Toast.LENGTH_LONG).show();
        }

    }

    public void CreateUser(View v) {
        Intent intent = new Intent(getBaseContext(), CreateNewUser.class);
        startActivity(intent);
    }

}
