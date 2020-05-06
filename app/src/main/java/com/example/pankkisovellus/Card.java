package com.example.pankkisovellus;


//Class for the cards that can be created for the account
public class Card {
    private int cardHolder;
    private String cardName;
    private String cardType;
    private String cardBalance;
    private String cardLimit;

    public Card() { }
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