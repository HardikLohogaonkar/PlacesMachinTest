package com.example.hardik.placesmachintest;

import java.io.Serializable;

/**
 * Created by hardik on 15/11/17.
 */

public class Places implements Serializable {

    private String name,address,ratings,icon;

    private double mLat,mLng;

    public Places(String name, String address, String ratings, String icon,double mLat, double mLng) {
        this.name = name;
        this.address = address;
        this.ratings = ratings;
        this.icon = icon;
        this.mLat = mLat;
        this.mLng = mLng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getIcon() {
        return icon;
    }

    public double getLat() {
        return mLat;
    }

    public void setLat(double Lat) {
        this.mLat = Lat;
    }

    public double getLng() {
        return mLng;
    }

    public void setLng(double Lng) {
        this.mLng = Lng;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
