package com.example.pankkisovellus;

import java.io.Serializable;


//Class for the accounts that the user can create
public class Account implements Serializable {
    private int accountId;
    private int accountHolder;
    private String accountName;
    private String accountType;
    private float accountBalance;
    private float accountLimit;

    public Account() { }

    public Account(int id, int accholder, String accname, String acctype, float accbalance, float acclimit) {
        accountId = id;
        accountHolder = accholder;
        accountName = accname;
        accountType = acctype;
        accountBalance = accbalance;
        accountLimit = acclimit;
    }

    public void setAccountId(int id) { accountId = id; }
    public void setAccountHolder(int holder) { accountHolder =  holder; }
    public void setAccountName(String name) { accountName = name; }
    public void setAccountType(String type) { accountType = type; }
    public void setAccountBalance(float balance) { accountBalance = balance; }
    public void setAccountLimit(float limit) { accountLimit = limit; }


    public int getAccountId() { return accountId; }
    public int getAccountHolder() { return accountHolder; }
    public String getAccountName() {
        return accountName;
    }
    public String toString() { return getAccountName(); }
    public String getAccountType() {
        return accountType;
    }
    public float getAccountBalance() {
        return accountBalance;
    }
    public float getAccountLimit() {
        return accountLimit;
    }
}
