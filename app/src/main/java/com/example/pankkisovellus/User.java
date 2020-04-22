package com.example.pankkisovellus;

public class User {
    private String userName;
    private String userPassword;
    private String userFirstName;
    private String userLastName;
    private String userDOB;

    public User() {
        userName = "null";
        userPassword = "null";
        userFirstName = "null";
        userLastName = "null";
        userDOB = "null";
    }
    public User(String user, String password, String firstname, String lastname, String dob) {
        userName = user;
        userPassword = password;
        userFirstName = firstname;
        userLastName = lastname;
        userDOB = dob;
    }


    public void setUserName(String username) {
        userName = username;
    }
    public void setPassword(String password) {
        userPassword = password;
    }
    public void setFirstName(String firstname) {
        userFirstName = firstname;
    }
    public void setLastName(String lastname) {
        userLastName = lastname;
    }
    public void setDOB(String dob) {
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