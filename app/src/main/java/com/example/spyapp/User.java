package com.example.spyapp;

public class User {
    private String fullName;
    private String phone;

    public User(String fullName, String phone) {
        this.fullName = fullName;
        this.phone = phone;
    }

    public User() {

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return phone;
    }

    public void setEmail(String email) {
        this.phone = email;
    }
}

