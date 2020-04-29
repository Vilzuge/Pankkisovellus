package com.example.pankkisovellus.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pankkisovellus.DatabaseHelper;
import com.example.pankkisovellus.R;
import com.example.pankkisovellus.User;

public class UserInformation extends AppCompatActivity {

    EditText editFirstName, editLastName, editUsername, editDOB;
    String firstName, lastName, userName, userPassword, userDOB;
    int userid;
    User user;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        user = (User) getIntent().getSerializableExtra("user");
        databaseHelper = new DatabaseHelper(UserInformation.this);

        editFirstName = (EditText) findViewById(R.id.editFirstName);
        editLastName = (EditText) findViewById(R.id.editLastName);
        editUsername = (EditText) findViewById(R.id.editUsername);
        editDOB = (EditText) findViewById(R.id.editDOB);

        editFirstName.setText(user.getFirstName());
        editLastName.setText(user.getLastName());
        editUsername.setText(user.getUserName());
        editDOB.setText(user.getDOB());

    }

    public void changeInformation(View v) {
        userid = user.getUserId();
        userName = editUsername.getText().toString();
        userPassword = user.getPassword();
        firstName = editFirstName.getText().toString();
        lastName = editLastName.getText().toString();
        userDOB = editDOB.getText().toString();

        User tempUser = new User(userid, userName, userPassword, firstName, lastName, userDOB);

        if (databaseHelper.alterUser(tempUser) != null){
            Toast.makeText(UserInformation.this, "Successfully changed information.", Toast.LENGTH_LONG).show();
            user = tempUser;
        }
        else {
            Toast.makeText(UserInformation.this, "Changing user information failed.", Toast.LENGTH_LONG).show();
            user = user;
        }


    }
}
