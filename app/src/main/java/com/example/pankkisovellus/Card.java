package com.example.pankkisovellus;

public class Card {
    private int cardHolder;
    private String cardName;
    private String cardType;
    private String cardBalance;
    private String cardLimit;

    public Card() {
        cardName = "null";
        cardType = "null";
        cardBalance = "null";
        cardLimit = "null";
    }
    public Card(String name, String type, String balance, String limit) {
        cardName = name;
        cardType = type;
        cardBalance = balance;
        cardLimit = limit;
    }
    public void setCardName(String name) {
        cardName = name;
    }
    public void setCardType(String type) {
        cardType = type;
    }
    public void setCardBalance(String balance) {
        cardBalance = balance;
    }
    public void setCardLimit(String limit) {
        cardLimit = limit;
    }


    public String getAccountName() {
        return cardName;
    }
    public String getAccountType() {
        return cardType;
    }
    public String getAccountBalance() {
        return cardBalance;
    }
    public String getAccountLimit() {
        return cardLimit;
    }
}