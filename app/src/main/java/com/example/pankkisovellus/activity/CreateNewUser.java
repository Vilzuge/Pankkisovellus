package com.example.pankkisovellus.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.pankkisovellus.DatabaseHelper;
import com.example.pankkisovellus.DateChecker;
import com.example.pankkisovellus.PasswordChecker;
import com.example.pankkisovellus.R;
import com.example.pankkisovellus.User;

public class CreateNewUser extends AppCompatActivity {

    EditText userName, firstName, lastName, dob, password, confirmPassword;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_user);
        userName = (EditText) findViewById(R.id.editPaymentLimit);
        password = (EditText) findViewById(R.id.editPassword);
        firstName = (EditText) findViewById(R.id.editAccountName);
        lastName = (EditText) findViewById(R.id.editLastName);
        dob = (EditText) findViewById(R.id.editDOB);
        confirmPassword = (EditText) findViewById(R.id.editConfirmPassword);
        databaseHelper = new DatabaseHelper(CreateNewUser.this);
    }


    //Creating a new account, first validating the infromation that the user is giving,
    //and then calling databaseHelpers method newUser()
    public void createAccount(View v) {
        DateChecker datechecker = new DateChecker();
        String username = userName.getText().toString();
        String password_first = password.getText().toString();
        String password_second = confirmPassword.getText().toString();
        String first_name = firstName.getText().toString();
        String last_name = lastName.getText().toString();
        String date_of_birth = dob.getText().toString();

        //Checking if the password is good enough.
        PasswordChecker checker = new PasswordChecker();
        if(!checker.passwordLength(password_first)) {
            Toast.makeText(CreateNewUser.this, "Password is not long enough.", Toast.LENGTH_LONG).show();
        }
        if(!checker.passwordNumbers(password_first)) {
            Toast.makeText(CreateNewUser.this, "Password does not have numbers.", Toast.LENGTH_LONG).show();
        }
        if(!checker.hasUppercase(password_first)) {
            Toast.makeText(CreateNewUser.this, "Password does not have uppercase letters.", Toast.LENGTH_LONG).show();
        }
        boolean isValidPassword = checker.isValidPassword(password_first);

        //Checking if the date is in correct format by calling the DateChecker
        if(!datechecker.isValidDate(date_of_birth)) {
            Toast.makeText(CreateNewUser.this, "Date is not in the correct format. 'dd.mm.yyyy'", Toast.LENGTH_LONG).show();
        }
        boolean isValidDOB = datechecker.isValidDate(date_of_birth);

        //Checking if the username is unique by comparing it to other usernames in the database
        boolean isValidUsername = databaseHelper.isUniqueUser(username);
        if(!isValidUsername) {
            Toast.makeText(CreateNewUser.this, "Username is already taken.", Toast.LENGTH_LONG).show();
        }
        //Checking that the passwords are correct, the username is unique and the password is complex enough
        //and creating the user if everything is correct
        if (password_first.equals(password_second) && isValidPassword && isValidUsername && isValidDOB) {
            User user = new User(0, username, password_first, first_name, last_name, date_of_birth);
            boolean worked = databaseHelper.newUser(user);
            if (worked) {
                Toast.makeText(CreateNewUser.this,"Successfully created an user.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(CreateNewUser.this,"Creating an user failed.", Toast.LENGTH_LONG).show();
            }
            finish();
        }
    }
}
