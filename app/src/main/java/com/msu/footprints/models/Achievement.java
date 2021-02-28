package com.msu.footprints.models;

import java.io.Serializable;

public class Achievement implements Serializable{

    private String Title, Description, year, ImageURL;

    public Achievement(){}

    public Achievement(String title, String Year, String description, String imageURL){
        Title = title;
        year = Year;
        Description = description;
        ImageURL = imageURL;
    }

    public String getTitle(){
        return Title;
    }

    public void setTitle(String title){
        Title = title;
    }

    public String getYear(){
        return year;
    }

    public void setYear(String Year){
        year = Year;
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
