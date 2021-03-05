package com.msu.footprints.models;

import java.io.Serializable;

public class About implements Serializable {

    private String Name;
    private String Email;
    private String Mob;

    public About(){}

    public About(String name, String email, String mob) {
        this.Name = name;
        this.Email = email;
        this.Mob = mob;
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

    public String getMob() {
        return Mob;
    }

    public void setMob(String mob) {
        Mob = mob;
    }

}
