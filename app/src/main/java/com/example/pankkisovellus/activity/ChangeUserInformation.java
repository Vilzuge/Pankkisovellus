package com.example.pankkisovellus.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pankkisovellus.DatabaseHelper;
import com.example.pankkisovellus.DateChecker;
import com.example.pankkisovellus.R;
import com.example.pankkisovellus.User;

public class ChangeUserInformation extends AppCompatActivity {

    EditText editFirstName, editLastName, editUsername, editDOB;
    String firstName, lastName, userName, userPassword, userDOB;
    int userid;
    User user;
    DatabaseHelper databaseHelper;
    DateChecker datechecker;

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
        userPassword = user.getPassword();
        firstName = editFirstName.getText().toString();
        lastName = editLastName.getText().toString();
        userDOB = editDOB.getText().toString();

        boolean isValidDOB = datechecker.isValidDate(userDOB);
        if (!isValidDOB) {
            Toast.makeText(ChangeUserInformation.this, "Incorrect dateformat", Toast.LENGTH_LONG).show();
        }

        if (isValidDOB) {
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
