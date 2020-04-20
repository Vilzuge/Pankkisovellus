package com.example.pankkisovellus;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";


    SQLiteDatabase database;
    private static final String databaseName = "database.db";

    private static final String tableUsers = "USERS";
    private static final String userId = "USERID";
    private static final String userUsername = "USERNAME";
    private static final String userPassword = "PASSWORD";
    private static final String userFirstName = "FIRSTNAME";
    private static final String userLastName = "LASTNAME";
    private static final String userDOB = "DOB";

    private static final String tableAdmins = "ADMINS";
    private static final String adminId = "ADMINID";
    private static final String adminUsername = "USERNAME";
    private static final String adminPassword = "LASTNAME";

    private static final String tableAccounts = "ACCOUNTS";
    private static final String accountId = "ACCOUNTID";
    private static final String accountName = "ACCOUNTNAME";
    private static final String accountType = "ACCOUNTTYPE";
    private static final String accountBalance = "ACCOUNTBALANCE";
    private static final String accountLimit = "ACCOUNTLIMIT";

    //Korteille omat muuttujat mahdollisesti!

    public DatabaseHelper(Context context) {
        super(context, databaseName, null, 1);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createUserTable = "CREATE TABLE " + tableUsers + " (" +
                userId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                userUsername + " TEXT, " +
                userPassword + " TEXT, " +
                userFirstName + " TEXT, " +
                userLastName + " TEXT, " +
                userDOB + " TEXT)";
        db.execSQL(createUserTable);

        String createAdminTable = "CREATE TABLE " + tableAdmins + " (" +
                adminId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                adminUsername + " TEXT, " +
                adminPassword + " TEXT)";
        db.execSQL(createAdminTable);

        String createAccountTable = "CREATE TABLE " + tableAccounts + " (" +
                accountId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                accountName + " TEXT, " +
                accountType + " TEXT, " +
                accountBalance + " TEXT, " +
                accountLimit + " TEXT)";
        db.execSQL(createAccountTable);

        //Tähän vielä lisäksi mahdollisesti kortteja varten omat pöydät


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + tableUsers);
        db.execSQL("DROP TABLE IF EXISTS " + tableAdmins);
        db.execSQL("DROP TABLE IF EXISTS " + tableAccounts);
        onCreate(db);
    }

    //Näihin vielä parametreiksi käyttäjä oliot
    public void addUser () {

    }

    public void addAdmin () {

    }

    public void addAccount () {

    }

}