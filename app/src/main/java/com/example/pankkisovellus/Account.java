package com.example.pankkisovellus;

public class Account {
    private String userName;
    private String userPassword;
    private String userFirstName;
    private String userLastName;
    private String userDOB;

    public Account() {
        userName = "null";
        userPassword = "null";
        userFirstName = "null";
        userLastName = "null";
        userDOB = "null";
    }
    public Account(String user, String password, String firstname, String lastname, String dob) {
        userName = user;
        userPassword = password;
        userFirstName = firstname;
        userLastName = lastname;
        userDOB = dob;
    }

    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return userPassword;
    }
    public String getFirstName() {
        return userFirstName;
    }
    public String getLastName() {
        return userLastName;
    }
    public String getDOB() {
        return userDOB;
    }

}
