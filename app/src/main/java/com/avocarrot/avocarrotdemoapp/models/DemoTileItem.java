package com.avocarrot.avocarrotdemoapp.models;

/**
 * Created by theokir on 13/04/16.
 */
public class DemoTileItem {

    private String title;
    private String location;
    private String number;
    private String image;


    public DemoTileItem(String title,  String number,String location, String image) {
        this.title = title;
        this.location = location;
        this.number = number;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
