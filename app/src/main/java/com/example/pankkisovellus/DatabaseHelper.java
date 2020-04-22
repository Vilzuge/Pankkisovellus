package com.example.pankkisovellus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.pankkisovellus.User;


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

    private static final String tableCards = "CARDS";
    private static final String cardId = "CARDID";
    private static final String cardHolder = "HOLDERNAME";
    private static final String cardAccount = "ACCOUNTNAME";
    private static final String cardType = "CARDTYPE";

    private static int DATABASE_VERSION = 2;



    //Korteille omat muuttujat mahdollisesti!

    public DatabaseHelper(Context context) {
        super(context, databaseName, null, DATABASE_VERSION);
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

        String createCardTable = "CREATE TABLE " + tableCards + " (" +
                cardId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                cardHolder + " INT, " +
                cardAccount + " INT, " +
                cardType + " TEXT)";
        db.execSQL(createCardTable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int newVer, int oldVer) {
        if (newVer > oldVer) {
            db.execSQL("DROP TABLE IF EXISTS " + tableUsers);
            db.execSQL("DROP TABLE IF EXISTS " + tableAdmins);
            db.execSQL("DROP TABLE IF EXISTS " + tableAccounts);
            db.execSQL("DROP TABLE IF EXISTS " + tableCards);
            onCreate(db);
        }
    }

    //Näihin vielä parametreiksi käyttäjä oliot

    public User tryLogging(String signingUsername, String signingPassword) {
        //Creating an empty User object first.
        User user = new User();
        Cursor cursor = database.rawQuery("SELECT * FROM " + tableUsers + " WHERE " + userUsername + "=? AND " + userPassword + "=?", new String[]{signingUsername,signingPassword});
        if (cursor != null) {
           if(cursor.getCount() > 0) {
               String username = cursor.getString(cursor.getColumnIndexOrThrow(userUsername));
               String password = cursor.getString(cursor.getColumnIndexOrThrow(userPassword));
               String firstname = cursor.getString(cursor.getColumnIndexOrThrow(userFirstName));
               String lastname = cursor.getString(cursor.getColumnIndexOrThrow(userLastName));
               String dob = cursor.getString(cursor.getColumnIndexOrThrow(userDOB));

               //Setting the data from the database to the object
               user.setUserName(username);
               user.setPassword(password);
               user.setFirstName(firstname);
               user.setLastName(lastname);
               user.setDOB(dob);

               return user;
           }
       }
       return null;
    }

    public boolean newUser (User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(userUsername, user.getUserName());
        contentValues.put(userPassword, user.getPassword());
        contentValues.put(userFirstName, user.getFirstName());
        contentValues.put(userLastName, user.getLastName());
        contentValues.put(userDOB, user.getDOB());

        long result = database.insert(tableUsers, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public void addAdmin () {

    }

    public void addAccount () {

    }

}