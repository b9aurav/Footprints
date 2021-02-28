package com.msu.footprints.models;

import java.io.Serializable;

public class About implements Serializable {

    private String Name;
    private String Email;

    public About(){}

    public About(String name, String email) {
        this.Name = name;
        this.Email = email;
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

}
