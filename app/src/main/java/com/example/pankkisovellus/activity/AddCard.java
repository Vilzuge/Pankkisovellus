package com.example.pankkisovellus.activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pankkisovellus.Account;
import com.example.pankkisovellus.DatabaseHelper;
import com.example.pankkisovellus.R;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddCard extends AppCompatActivity {

    TextView accName;
    Account account;
    DatabaseHelper databaseHelper;
    static String itemvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        account = (Account) getIntent().getSerializableExtra("account");
        databaseHelper = new DatabaseHelper(AddCard.this);
        Spinner spinnerCardType = (Spinner) findViewById(R.id.spinnerCardType);
        accName = (TextView) findViewById(R.id.textOrderAccountName);
        accName.setText(account.getAccountName());

        //Spinner for choosing the card type
        List<String> list = new ArrayList<>();
        list.add("Debit");
        list.add("Credit");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddCard.this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCardType.setAdapter(adapter);
        spinnerCardType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemvalue = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //Simulating ordering a new card for the specific account.
    public void OrderCard(View v) {
        String cardType = itemvalue;
        if (databaseHelper.orderCard(account, cardType)) {
            Toast.makeText(AddCard.this,"New card has been ordered.", Toast.LENGTH_LONG).show();
        }
    }
}