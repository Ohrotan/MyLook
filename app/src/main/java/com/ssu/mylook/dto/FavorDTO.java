package com.ssu.mylook.dto;

public class FavorDTO {
    String id;
    String image;
    String title;
    int count;

    public FavorDTO() {
    }

    public FavorDTO(String id, String image, String title, int count) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
