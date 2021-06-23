package com.tdt.model;

public class Album {
    private String title;
    private String imageUrl;
    private String time;
    private int commentCount;
    private String author;

    public Album(String title, String imageUrl, String time, int commentCount, String author) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.time = time;
        this.commentCount = commentCount;
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
