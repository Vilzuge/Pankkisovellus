package com.example.pankkisovellus.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pankkisovellus.DatabaseHelper;
import com.example.pankkisovellus.R;
import com.example.pankkisovellus.User;

public class LoginActivity extends AppCompatActivity {

    public static EditText username, password;
    DatabaseHelper databaseHelper;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.editPaymentLimit);
        password = (EditText) findViewById(R.id.editPassword);
        databaseHelper = new DatabaseHelper(LoginActivity.this);
    }

    public void Login(View w) {
        String userUsername = username.getText().toString();
        String userPassword = password.getText().toString();
        try {
            if (userUsername.length() > 0 && userPassword.length() > 0) {
                //If the object was returned, log the user in.
                user = databaseHelper.tryLogging(userUsername, userPassword);
                if (user != null) {
                    Toast.makeText(LoginActivity.this, "Successfully Logged In", Toast.LENGTH_LONG).show();
                    //Logging the user to the dashboard with the object of their information
                    Intent intent = new Intent(getBaseContext(), Dashboard.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Username/Password", Toast.LENGTH_LONG).show();
                }
            }
        } catch(Exception e){
            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void CreateUser(View v) {
        Intent intent = new Intent(getBaseContext(), CreateNewUser.class);
        startActivity(intent);
    }


}
