package com.ssu.mylook.dto;

import android.graphics.Bitmap;

public class ClotheItem {
    private String title;
    private String image;
    private String id;
    private String getBitmap;

    public ClotheItem()
    {
        super();
    }

    public ClotheItem(String title, String image, String id, String getBitmap){
        this.title=title;
        this.image=image;
        this.id=id;
        this.getBitmap=getBitmap;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public String getImage(){
        return image;
    }

    public void setImage(String image){
        this.image=image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBitmap() {
        return getBitmap;
    }

    public void setBitmap(String bitmap) {
        this.getBitmap = bitmap;
    }


}
