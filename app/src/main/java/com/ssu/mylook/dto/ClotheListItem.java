package com.ssu.mylook.dto;

public class ClotheListItem {

    private String name;
    private String image;
    private String id;


    private String ttl;

    public ClotheListItem() {
        super();
    }

    public ClotheListItem(String name, String image, String id) {
        this.name = name;
        this.image = image;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }
}
