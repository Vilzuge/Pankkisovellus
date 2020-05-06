package com.example.pankkisovellus.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pankkisovellus.DatabaseHelper;
import com.example.pankkisovellus.DateChecker;
import com.example.pankkisovellus.PasswordChecker;
import com.example.pankkisovellus.R;
import com.example.pankkisovellus.User;

public class ChangeUserInformation extends AppCompatActivity {

    EditText editFirstName, editLastName, editDOB, editOldPassword, editPassword, editConfirmPassword;
    String firstName, lastName, userName, userOldPassword,  userPassword, userConfirmPassword, userDOB;
    int userid;
    User user;
    DatabaseHelper databaseHelper;
    DateChecker datechecker;
    PasswordChecker checker = new PasswordChecker();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_information);
        user = (User) getIntent().getSerializableExtra("user");
        databaseHelper = new DatabaseHelper(ChangeUserInformation.this);
        datechecker = new DateChecker();
        editFirstName = (EditText) findViewById(R.id.editAccountName);
        editLastName = (EditText) findViewById(R.id.editLastName);
        editDOB = (EditText) findViewById(R.id.editDOB);
        editOldPassword = (EditText) findViewById(R.id.editOldPassword);
        editPassword = (EditText) findViewById(R.id.editPassword);
        editConfirmPassword = (EditText) findViewById(R.id.editConfirmPassword);

        //Filling the fields with the present information
        editFirstName.setText(user.getFirstName());
        editLastName.setText(user.getLastName());
        editDOB.setText(user.getDOB());

    }

    //Changing user information by calling a databaseHelper method alterUser()
    //opening up the Dashboard again if the change happens
    public void changeInformation(View v) {
        userid = user.getUserId();
        userName = user.getUserName();
        userOldPassword = editOldPassword.getText().toString();
        userPassword = editPassword.getText().toString();
        userConfirmPassword = editConfirmPassword.getText().toString();
        firstName = editFirstName.getText().toString();
        lastName = editLastName.getText().toString();
        userDOB = editDOB.getText().toString();

        boolean isValidDOB = datechecker.isValidDate(userDOB);
        if (!isValidDOB) {
            Toast.makeText(ChangeUserInformation.this, "Incorrect dateformat", Toast.LENGTH_LONG).show();
        }

        boolean oldPasswordOk = userOldPassword.equals(user.getPassword());
        if (!oldPasswordOk) {
            Toast.makeText(ChangeUserInformation.this, "Old password is not correct", Toast.LENGTH_LONG).show();
        }

        boolean passwordsMatch = userPassword.equals(userConfirmPassword);
        if (!passwordsMatch) {
            Toast.makeText(ChangeUserInformation.this, "Passwords don't match", Toast.LENGTH_LONG).show();
        }

        boolean isValidPassword = checker.isValidPassword(userPassword);
        if (!isValidPassword) {
            Toast.makeText(ChangeUserInformation.this, "Password does not meet the conditions.", Toast.LENGTH_LONG).show();
        }

        if (isValidDOB && passwordsMatch && oldPasswordOk && isValidPassword) {
            User tempUser = new User(userid, userName, userPassword, firstName, lastName, userDOB);
            if (databaseHelper.alterUser(tempUser) != null) {
                Toast.makeText(ChangeUserInformation.this, "Successfully changed information.", Toast.LENGTH_LONG).show();
                user = tempUser;
                Intent intent = new Intent(getBaseContext(), Dashboard.class);
                intent.putExtra("user", user);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(ChangeUserInformation.this, "Changing user information failed.", Toast.LENGTH_LONG).show();
                user = user;
            }
        }
    }
}
