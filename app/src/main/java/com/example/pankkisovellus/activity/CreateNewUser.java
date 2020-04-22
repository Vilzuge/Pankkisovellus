package com.example.pankkisovellus.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pankkisovellus.DatabaseHelper;
import com.example.pankkisovellus.R;
import com.example.pankkisovellus.User;

public class CreateNewUser extends AppCompatActivity {

    public static EditText userName, firstName, lastName, dob, password, confirmPassword;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_user);

        userName = (EditText) findViewById(R.id.editUsername);
        password = (EditText) findViewById(R.id.editPassword);
        firstName = (EditText) findViewById(R.id.editFirstName);
        lastName = (EditText) findViewById(R.id.editLastName);
        dob = (EditText) findViewById(R.id.editDOB);
        confirmPassword = (EditText) findViewById(R.id.editConfirmPassword);

        databaseHelper = new DatabaseHelper(this);
    }

    public void createAccount(View v) {

        String username = userName.getText().toString();
        String password_first = password.getText().toString();
        String password_second = confirmPassword.getText().toString();
        String first_name = firstName.getText().toString();
        String last_name = lastName.getText().toString();
        String date_of_birth = dob.getText().toString();

        //Lets check if the passwords were identical and create an user object of the data
        if (password_first.equals(password_second)) {
            User user = new User(username, password_first, first_name, last_name, date_of_birth);
            System.out.println(user.getFirstName());
            System.out.println(user.getLastName());
            System.out.println(user.getDOB());
            System.out.println(user.getPassword());
            System.out.println(user.getUserName());
            boolean worked = databaseHelper.newUser(user);

            if (worked == true) {
                Toast.makeText(CreateNewUser.this,"Successfully created an account.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(CreateNewUser.this,"Creating an account failed.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
