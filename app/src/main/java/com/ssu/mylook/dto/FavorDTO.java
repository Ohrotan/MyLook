package com.ssu.mylook.dto;

public class FavorDTO {
    String id;
    String img;
    String name;
    int count;

    public FavorDTO() {
        super();
    }
    public FavorDTO(String id, String img, String name, int count){
        this.id=id;this.img=img;this.name=name;this.count=count;
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
