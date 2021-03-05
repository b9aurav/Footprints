package com.msu.footprints.models;

import android.widget.ImageView;

import java.io.Serializable;

public class Contact implements Serializable {

    private String ImageURL;
    private String Email;
    private String Address;
    private String Mob;

    public Contact(){}

    public Contact(String imageURL, String email, String address, String mob) {
        this.ImageURL = imageURL;
        this.Email = email;
        this.Address = address;
        this.Mob = mob;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getMob() {
        return Mob;
    }

    public void setMob(String mob) {
        Mob = mob;
    }
}
