package com.ssu.mylook;

public class Closet_ListViewItem {
    private int clothe_image;
    private String clothe_name;
    private String clothe_id;

    public int closet_getImage() {return clothe_image; }
    public String closet_getName() {return clothe_name; }
    public String closet_getId(){return clothe_id;}

    public Closet_ListViewItem( String clothe_name,int clothe_image, String clothe_id) {
        this.clothe_image = clothe_image;
        this.clothe_name = clothe_name;
        this.clothe_id = clothe_id;
    }

}
