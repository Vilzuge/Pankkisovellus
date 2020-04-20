package com.example.pankkisovellus;

public class Admin {
    private String adminName;
    private String adminPassword;

    public Admin () {
        adminName = "null";
        adminPassword = "null";
    }
    public Admin (String user, String password) {
        adminName = user;
        adminPassword = password;

    }

    public String getUserName() {
        return adminName;
    }
    public String getPassword() {
        return adminPassword;
    }
}