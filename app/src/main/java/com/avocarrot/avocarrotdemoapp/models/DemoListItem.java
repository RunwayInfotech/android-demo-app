package com.avocarrot.avocarrotdemoapp.models;

/**
 * Created by theokir on 13/04/16.
 */
public class DemoListItem {
    private String title;
    private String likes;
    private String comments;
    private String icon;

    public DemoListItem(String title,  String comments, String likes,String icon) {
        this.title = title;
        this.likes = likes;
        this.comments = comments;
        this.icon = icon;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
