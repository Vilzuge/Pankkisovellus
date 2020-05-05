package com.example.pankkisovellus.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pankkisovellus.Account;
import com.example.pankkisovellus.R;
import com.example.pankkisovellus.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class AccountInformation extends AppCompatActivity {

    User user;
    Account account;
    TextView header;
    ListView listView;
    ArrayList<String> event_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_information);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        account = (Account) extras.getSerializable("account");
        user = (User) extras.getSerializable("user");
        listView = (ListView) findViewById(R.id.listView);
        header = (TextView) findViewById(R.id.textOrderAccountName);
        header.setText(account.getAccountName());

        event_list = readAccountFile(user.getUserName(), account.getAccountName());
        Collections.reverse(event_list);
        ArrayAdapter arrayAdapter = new ArrayAdapter(AccountInformation.this, android.R.layout.simple_list_item_1, event_list);
        listView.setAdapter(arrayAdapter);
    }

    public void addCard(View v) {
        Intent intent = new Intent(getBaseContext(), AddCard.class);
        intent.putExtra("account", account);
        startActivity(intent);
    }


    public ArrayList<String> readAccountFile(String user, String account) {
        ArrayList<String> list = new ArrayList<String>();
        try {
            String file = user + "_" + account + ".csv";
            InputStream ins = AccountInformation.this.openFileInput(file);

            String s = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(ins));

            while((s = br.readLine()) != null) {
                list.add(s + "\n");
            }
            ins.close();
            return list;
        } catch (IOException e) {
            Log.e("IOException","Error in input");
        } finally {
            System.out.println("File read");
        }
        return list;
    }


}
