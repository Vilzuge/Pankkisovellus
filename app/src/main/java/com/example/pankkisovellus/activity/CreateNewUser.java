package com.example.pankkisovellus.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.pankkisovellus.R;

public class CreateNewUser extends AppCompatActivity {


    public static EditText firstName, lastName, dob, password, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_user);

        firstName = (EditText) findViewById(R.id.editFirstName);
        lastName = (EditText) findViewById(R.id.editLastName);
        dob = (EditText) findViewById(R.id.editDOB);
        password = (EditText) findViewById(R.id.editPassword);
        confirmPassword = (EditText) findViewById(R.id.editConfirmPassword);
    }

    public void createAccount(View v) {
        String first_name = firstName.getText().toString();
        String last_name = firstName.getText().toString();
        String date_of_birth = firstName.getText().toString();
        String password_first = firstName.getText().toString();
        String password_second = firstName.getText().toString();

    }
}
