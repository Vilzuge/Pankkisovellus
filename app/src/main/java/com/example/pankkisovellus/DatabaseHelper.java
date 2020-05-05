package com.example.pankkisovellus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.pankkisovellus.User;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

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
    private static final String accountHolder = "ACCOUNTHOLDER";
    private static final String accountName = "ACCOUNTNAME";
    private static final String accountType = "ACCOUNTTYPE";
    private static final String accountBalance = "ACCOUNTBALANCE";
    private static final String accountLimit = "ACCOUNTLIMIT";

    private static final String tableCards = "CARDS";
    private static final String cardId = "CARDID";
    private static final String cardHolder = "HOLDERNAME";
    private static final String cardAccount = "ACCOUNTNAME";
    private static final String cardType = "CARDTYPE";

    private SQLiteDatabase database;
    private static int DATABASE_VERSION = 14;


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
                accountHolder + " INT, " +
                accountName + " TEXT, " +
                accountType + " TEXT, " +
                accountBalance + " FLOAT, " +
                accountLimit + " FLOAT)";
        db.execSQL(createAccountTable);

        String createCardTable = "CREATE TABLE " + tableCards + " (" +
                cardId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                cardHolder + " INT, " +
                cardAccount + " INT, " +
                cardType + " TEXT)";
        db.execSQL(createCardTable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + tableUsers);
        db.execSQL("DROP TABLE IF EXISTS " + tableAdmins);
        db.execSQL("DROP TABLE IF EXISTS " + tableAccounts);
        db.execSQL("DROP TABLE IF EXISTS " + tableCards);
        onCreate(db);

    }

    //Trying to log the user in with the given information.
    public User tryLogging(String signingUsername, String signingPassword) throws SQLException {
        //Creating an empty User object first.
        User user = new User();
        Cursor cursor = database.rawQuery("SELECT * FROM " +
                tableUsers + " WHERE " +
                userUsername + "=? AND " +
                userPassword + "=?", new String[]{signingUsername,signingPassword});

        cursor.moveToFirst();
        if (signingUsername.equals(cursor.getString(cursor.getColumnIndexOrThrow(userUsername))) && signingPassword.equals(cursor.getString(cursor.getColumnIndexOrThrow(userPassword))))  {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(userId));
            String username = cursor.getString(cursor.getColumnIndexOrThrow(userUsername));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(userPassword));
            String firstname = cursor.getString(cursor.getColumnIndexOrThrow(userFirstName));
            String lastname = cursor.getString(cursor.getColumnIndexOrThrow(userLastName));
            String dob = cursor.getString(cursor.getColumnIndexOrThrow(userDOB));

            //Setting the data from the database to the object
            user.setUserId(id);
            user.setUserName(username);
            user.setPassword(password);
            user.setFirstName(firstname);
            user.setLastName(lastname);
            user.setDOB(dob);
            return user;
        }
        else {
            return null;
        }
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

    public User alterUser(User tempUser) {
        int id = tempUser.getUserId();
        ContentValues contentValues = new ContentValues();
        contentValues.put(userFirstName, tempUser.getFirstName());
        contentValues.put(userLastName, tempUser.getLastName());
        contentValues.put(userUsername, tempUser.getUserName());
        contentValues.put(userDOB, tempUser.getDOB());

        String selection = userId + " LIKE ?";
        String[] selectionArgs = { Integer.toString( id ) };

        int count = database.update(
                tableUsers,
                contentValues,
                selection,
                selectionArgs
        );

        if (count == 1) {
            return tempUser;
        }
        else {
            return null;
        }
    }

    public boolean isUniqueUser(String username) {
        //Figuring out the ID behind the username
        Cursor cursor = database.rawQuery("SELECT * FROM " +
                        tableUsers + " WHERE " +
                        userUsername + "=?",
                new String[]{ username });

        if (cursor.moveToFirst()) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean isUniqueAccount(String username) {
        //Figuring out the ID behind the username
        Cursor cursor = database.rawQuery("SELECT * FROM " +
                        tableUsers + " WHERE " +
                        userUsername + "=?",
                new String[]{ username });

        if (cursor.moveToFirst()) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean newAccount (Account account) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(accountHolder, account.getAccountHolder());
        contentValues.put(accountName, account.getAccountName());
        contentValues.put(accountType, account.getAccountType());
        contentValues.put(accountBalance, account.getAccountBalance());
        contentValues.put(accountLimit, account.getAccountLimit());

        long result = database.insert(tableAccounts, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public ArrayList<Account> fetchUserAccounts (User user) {
        int id = user.getUserId();
        ArrayList<Account> account_array = new ArrayList<Account>();
        Cursor cursor = database.rawQuery("SELECT * FROM " + tableAccounts + " WHERE " + accountHolder + "=?", new String[]{ Integer.toString(id) } );

        while( cursor.moveToNext() ) {
            int tempId = cursor.getInt(cursor.getColumnIndexOrThrow(accountId));
            int tempHolder = cursor.getInt(cursor.getColumnIndexOrThrow(accountHolder));
            String tempName = cursor.getString(cursor.getColumnIndexOrThrow(accountName));
            String tempType = cursor.getString(cursor.getColumnIndexOrThrow(accountType));
            float tempBalance = cursor.getFloat(cursor.getColumnIndexOrThrow(accountBalance));
            float tempLimit = cursor.getFloat(cursor.getColumnIndexOrThrow(accountLimit));

            Account account = new Account(tempId, tempHolder, tempName, tempType, tempBalance, tempLimit);
            account_array.add(account);
        }

        return account_array;

    }

    public boolean depositOrWithdraw(User user, Account account, String action, float amount) {
        int id = user.getUserId();

        if (action.equals("Withdraw")) {
            amount = amount * (-1);
        }


        float oldBalance = account.getAccountBalance();
        float newBalance = oldBalance + amount;

        ContentValues contentValues = new ContentValues();
        contentValues.put(accountBalance, newBalance);

        String selection = accountId + " LIKE ?";
        String[] selectionArgs = { Integer.toString( account.getAccountId() ) };

        int count = database.update(
                tableAccounts,
                contentValues,
                selection,
                selectionArgs
        );
        if (count == 1) {
            return true;
        } else {
            return false;
        }

    }

    public boolean receiverExists(String receiverAccount, String receiverUser) {

        int receiverId = 0;
        Cursor cursor = database.rawQuery("SELECT * FROM " +
                        tableUsers + " WHERE " +
                        userUsername + "=?",
                new String[]{ receiverUser });

        while( cursor.moveToNext() ) {
            receiverId = cursor.getInt(cursor.getColumnIndexOrThrow(userId));
        }

        //Checking if the user exists with the given account
        cursor = database.rawQuery("SELECT * FROM " +
                tableAccounts + " WHERE " +
                accountHolder + "=? AND " +
                accountName + "=?", new String[]{Integer.toString(receiverId), receiverAccount});

        if (cursor.moveToFirst()) {
            return true;
        }
        else {
            return false;
        }

    }

    public void transferMoney(Account account, String receiverAccount, String receiverUser, float amount) {

        float payerOldBalance = account.getAccountBalance();
        float payerNewBalance = payerOldBalance - amount;
        float receiverOldBalance = 0;
        float receiverNewBalance = 0;
        int receiverId = 0, receiverAccountId = 0;

        //Withdrawing the money from payer's account
        ContentValues contentValues = new ContentValues();
        contentValues.put(accountBalance, payerNewBalance);
        String selection = accountId + " LIKE ?";
        String[] selectionArgs = { Integer.toString( account.getAccountId() ) };
        int count = database.update(
                tableAccounts,
                contentValues,
                selection,
                selectionArgs
        );

        //Figuring out the ID behind the username
        Cursor cursor = database.rawQuery("SELECT * FROM " +
                        tableUsers + " WHERE " +
                        userUsername + "=?",
                new String[]{ receiverUser });

        while( cursor.moveToNext() ) {
            receiverId = cursor.getInt(cursor.getColumnIndexOrThrow(userId));
        }

        //Figuring out the receivers account's balance
        cursor = database.rawQuery("SELECT * FROM " +
                        tableAccounts + " WHERE " +
                        accountHolder + "=? AND " +
                        accountName + "=?",
                new String[]{Integer.toString(receiverId), receiverAccount});

        while( cursor.moveToNext() ) {
            receiverOldBalance = cursor.getFloat(cursor.getColumnIndexOrThrow(accountBalance));
            receiverAccountId = cursor.getInt(cursor.getColumnIndexOrThrow(accountId));
        }

        receiverNewBalance = receiverOldBalance + amount;


        //Depositing money to receivers account
        contentValues = new ContentValues();
        contentValues.put(accountBalance, receiverNewBalance);
        selection = accountId + " LIKE ?";
        String[] selectionArgsReceiver = { Integer.toString( receiverAccountId ) };
        count = database.update(
                tableAccounts,
                contentValues,
                selection,
                selectionArgsReceiver
        );



    }
}