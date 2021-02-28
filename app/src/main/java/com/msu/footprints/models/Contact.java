package com.msu.footprints.models;

import android.content.res.Configuration;

import java.io.Serializable;

public class Contact implements Serializable {

    private String Name;
    private String Email;
    private String Designation;
    private String ContactNo;

    public Contact(){}

    public Contact(String name, String designation, String email, String contactNo) {
        this.Name = name;
        this.Designation = designation;
        this.Email = email;
        this.ContactNo = contactNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }
}
