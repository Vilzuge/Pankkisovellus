package com.example.pankkisovellus;

public class Account {
    private String accountName;
    private String accountType;
    private String accountBalance;
    private String accountLimit;

    public Account() {
        accountName = "null";
        accountType = "null";
        accountBalance = "null";
        accountLimit = "null";
    }
    public Account(String accname, String acctype, String accbalance, String acclimit) {
        accountName = accname;
        accountType = acctype;
        accountBalance = accbalance;
        accountLimit = acclimit;
    }
    public void setAccountName(String name) {
        accountName = name;
    }
    public void setAccountType(String type) {
        accountType = type;
    }
    public void setAccountBalance(String balance) {
        accountBalance = balance;
    }
    public void setAccountLimit(String limit) {
        accountLimit = limit;
    }


    public String getAccountName() {
        return accountName;
    }
    public String getAccountType() {
        return accountType;
    }
    public String getAccountBalance() {
        return accountBalance;
    }
    public String getAccountLimit() {
        return accountLimit;
    }
}
