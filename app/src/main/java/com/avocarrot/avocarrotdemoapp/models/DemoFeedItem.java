package com.avocarrot.avocarrotdemoapp.models;

/**
 * Created by theokir on 13/04/16.
 */
public class DemoFeedItem {
    private String avatar;
    private String name;
    private String description;
    private String image;


    public DemoFeedItem(String avatar, String name, String description, String image) {
        this.avatar = avatar;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
