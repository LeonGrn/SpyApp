package com.example.spyapp;

public class Tracker {

    private String phoneNumber;
    private double	latitude = 0;
    private double longitude = 0;

    public Tracker(String phoneNumber) {
        this.phoneNumber = phoneNumber;

    }

    public Tracker() {

    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
