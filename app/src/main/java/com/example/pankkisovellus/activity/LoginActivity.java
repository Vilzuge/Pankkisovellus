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

    private EditText username, password;
    private User user;
    public DatabaseHelper databaseHelper;

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

                user = databaseHelper.tryLogging(userUsername, userPassword);
                if (user != null) {
                    Toast.makeText(LoginActivity.this, "Successfully Logged In", Toast.LENGTH_LONG).show();
                    //If the user-object was returned, log the user in with their user information.
                    Intent intent = new Intent(getBaseContext(), AccountInformation.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Username/Password", Toast.LENGTH_LONG).show();
                }
            }
        } catch(Exception e){
            Toast.makeText(LoginActivity.this, "Logging in failed.", Toast.LENGTH_LONG).show();
        }
    }

    public void CreateUser(View v) {
        try {
            Intent intent = new Intent(getBaseContext(), CreateNewUser.class);
            startActivity(intent);
        } catch(Exception e) {
            Toast.makeText(LoginActivity.this, "Failed to open the user creation screen.", Toast.LENGTH_LONG).show();
        }
    }

}
