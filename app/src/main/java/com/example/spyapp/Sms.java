package com.example.spyapp;

public class Sms {
    public Sms(String head, String body) {
        this.head = head;
        this.body = body;
    }

    private String head;
    private String body;

    public Sms() {

    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
