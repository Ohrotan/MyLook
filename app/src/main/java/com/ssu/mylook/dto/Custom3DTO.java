package com.ssu.mylook.dto;

import java.util.List;

public class Custom3DTO {
    String id;
    String img;
    String name;
    List<String> used;
    int count;

    public Custom3DTO() {
        super();
    }
    public Custom3DTO(String id, String img, String name, int count,List<String> used){
        this.id=id;this.img=img;this.name=name;this.count=count; this.used=used;
    }

    public String getImg(){
        return img;
    }
    public void setImg(String img){
        this.img=img;
    }
    public void setName(String name){this.name=name;}
    public String getName(){return name;}
    public int getCount(){
        return count;
    }
    public void setCount(int count){
        this.count=count;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
