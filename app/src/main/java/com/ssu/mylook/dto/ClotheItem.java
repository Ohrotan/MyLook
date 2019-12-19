package com.ssu.mylook.dto;


public class ClotheItem {
    private String title;
    private String image;
    private String id;


    public ClotheItem()
    {
        super();
    }

    public ClotheItem(String title, String image, String id){
        super();
        this.title=title;
        this.image=image;
        this.id=id;
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


    @Override
    public String toString(){
        return "ClotheDTO[id="+id+",title="+title+",image="+image+"]";
    }
}
