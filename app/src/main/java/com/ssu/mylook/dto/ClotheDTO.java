package com.ssu.mylook.dto;
//DB의 데이터를 저장할 클래스

import android.graphics.Bitmap;

import java.util.List;

public class ClotheDTO {
    private String id;



    private String image;
    private String title;
    private String sort;
    private List<String> seasons; //중복선택
    private String color;
    private String memo;
    private String regDate;



    public ClotheDTO() {
        super();
    }

    public ClotheDTO(String id, String image, String title, String sort,  List<String> seasons, String color, String memo, String regDate)
    {
        super();
        this.id=id;
        this.image=image;
        this.title=title;
        this.sort=sort;
        this.seasons=seasons;
        this.memo=memo;
        this.color=color;
        this.regDate=regDate;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSort() {
        return sort;
    }

    public List<String> getSeasons() {
        return seasons;
    }

    public String getColor() {
        return color;
    }

    public String getMemo() {
        return memo;
    }

    public String getRegDate() {
        return regDate;
    }

    public String getImage() {
        return image;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setSeasons(List<String> seasons) {
        this.seasons = seasons;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString(){
        return "ClotheDTO[id="+id+",title="+title+",image="+image+",sort="+sort+",season="+seasons+",memo="+memo+",color="+color+",date="+regDate+"]";
    }
}
