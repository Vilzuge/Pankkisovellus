package com.example.pankkisovellus.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.pankkisovellus.R;
import com.example.pankkisovellus.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {

    User user = null;
    TextView nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        //Bundle extras = getIntent().getExtras();
        //user = (User) extras.getSerializable("olio") jos monta!
        user = (User) getIntent().getSerializableExtra("user");
        nameText = (TextView) findViewById(R.id.textViewName);
        nameText.setText(user.getUserName());
    }


    public void changeInformation(View v) {
        Intent intent = new Intent(getBaseContext(), UserInformation.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}
