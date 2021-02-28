package com.msu.footprints.models;

import java.io.Serializable;

public class Sponsor implements Serializable{
    private String ImageURL="";

    public  Sponsor() {
    }

    public Sponsor(String imageURL){
        ImageURL = imageURL;
    }

    public String getImageURL(){
        return ImageURL;
    }

    public void setImageURL(String imageURL){
        ImageURL = imageURL;
    }
}
