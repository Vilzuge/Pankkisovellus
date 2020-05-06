package com.example.pankkisovellus;

import java.io.Serializable;


//Class for the users, has the same information that the user table in the database has.
public class User implements Serializable {
    private int userId;
    private String userName;
    private String userPassword;
    private String userFirstName;
    private String userLastName;
    private String userDOB;

    public User() { }

    public User(int id, String user, String password, String firstname, String lastname, String dob) {
        userId = id;
        userName = user;
        userPassword = password;
        userFirstName = firstname;
        userLastName = lastname;
        userDOB = dob;
    }


    //Setters
    public void setUserId(int id) { userId = id; }
    public void setUserName(String username) {
        userName = username;
    }
    public void setPassword(String password) {
        userPassword = password;
    }
    public void setFirstName(String firstname) { userFirstName = firstname; }
    public void setLastName(String lastname) { userLastName = lastname; }
    public void setDOB(String dob) { userDOB = dob; }

    //Getters
    public int getUserId() { return userId; }
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