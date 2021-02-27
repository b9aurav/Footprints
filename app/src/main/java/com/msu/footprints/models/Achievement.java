package com.msu.footprints.models;

import java.io.Serializable;

public class Achievement implements Serializable{

    private String Title, Description, ImageURL;

    public Achievement(){}

    public Achievement(String title, String description, String imageURL){
        Title = title;
        Description = description;
        ImageURL = imageURL;
    }

    public String getTitle(){
        return Title;
    }

    public void setTitle(String title){
        Title = title;
    }

    public String getDescription(){
        return Description;
    }

    public void setDescription(String description){
        Description = description;
    }

    public String getImageURL(){
        return ImageURL;
    }

    public void setImageURL(String imageURL){
        ImageURL = imageURL;
    }
}
