package com.ssu.mylook;

public class Closet_ListViewItem {
    private int image;
    private String name;

    public int closet_getImage() {return image; }
    public String closet_getName() {return name; }

    public Closet_ListViewItem(int image, String name){
        this.image=image;
        this.name=name;
    }
}
