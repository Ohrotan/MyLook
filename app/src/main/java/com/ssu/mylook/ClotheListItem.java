package com.ssu.mylook;

import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class ClotheListItem  {
    private int clothe_img;
    private String clothe_name;
    private String clothe_id;

    public ClotheListItem() {
        super();
    }

    public ClotheListItem(int clothe_img, String clothe_name, String clothe_id) {
        this.clothe_img = clothe_img;
        this.clothe_name = clothe_name;
        this.clothe_id = clothe_id;
    }

    public int getClothe_img() {
        return clothe_img;
    }

    public void setClothe_img(int clothe_img) {
        this.clothe_img = clothe_img;
    }

    public String getClothe_name() {
        return clothe_name;
    }

    public void setClothe_name(String clothe_name) {
        this.clothe_name = clothe_name;
    }

    public String getClothe_id() {
        return clothe_id;
    }

    public void setClothe_id(String clothe_id) {
        this.clothe_id = clothe_id;
    }
}
