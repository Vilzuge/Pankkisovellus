package com.example.pankkisovellus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateChecker {

    public DateChecker () { }

    public DateChecker (String a) {
        String date = a;
    }

    public boolean isValidDate(String date) {
        SimpleDateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date javaDate = dateformat.parse(date);
            System.out.println(date +" is a valid date");
            return true;
        } catch (ParseException e) {
            System.out.println(date +" is an invalid date");
            return false;
        }
    }
}
