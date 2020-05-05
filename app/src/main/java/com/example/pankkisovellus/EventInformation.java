package com.example.pankkisovellus;

public class EventInformation {

    String type, receiver;
    float amount;

    public EventInformation() {}

    public EventInformation(String event_type, String event_receiver, float event_amount) {
        type = event_type;
        receiver = event_receiver;
        amount = event_amount;
    }

    public String getEventType() { return type; }
    public String getEventReceiver() { return receiver; }
    public float getEventAmount() {
        return amount;
    }
}
