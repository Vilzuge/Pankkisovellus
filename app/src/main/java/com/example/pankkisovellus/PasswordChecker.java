package com.example.pankkisovellus;

public class PasswordChecker {

    String password;

    public PasswordChecker() {}

    public PasswordChecker(String psw) {
        password = psw;
    }

    public boolean passwordLength(String psw) {
        if (psw.length() < 12) {
            return false;
        } else {
            return true;
        }
    }

    public boolean passwordNumbers(String psw) {
        int count = 0;
        for (int i = 0; i < psw.length(); i++) {
            char ch = psw.charAt(i);
            if (Character.isDigit(ch)) {
                count++;
            }
        }
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean hasUppercase(String psw) {
        int count = 0;
        for (int i = 0; i < psw.length(); i++) {
            char ch = psw.charAt(i);
            if (Character.isUpperCase(ch)) {
                count++;
            }
        }
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidPassword(String psw) {
        if(!passwordNumbers(psw)) { return false; }
        if(!passwordLength(psw)) { return false; }
        if(!hasUppercase(psw)){ return false; }
        return true;
    }
}
