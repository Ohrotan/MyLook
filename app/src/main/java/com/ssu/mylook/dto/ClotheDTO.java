package com.ssu.mylook.dto;
//DB의 데이터를 저장할 클래스

import java.util.List;

public class ClotheDTO {
    private String id;
    private String image;
    private String title;
    private String sort;
    private List<String> season; //중복선택
    private String color;
    private String memo;
    private String regDate;

    public ClotheDTO()
    {
        super();
    }
    public ClotheDTO(String id, String image, String title, String sort,  List<String> season, String color, String memo, String regDate)
    {
        super();
        this.id=id;
        this.image=image;
        this.title=title;
        this.sort=sort;
        this.season=season;
        this.memo=memo;
        this.color=color;
        this.regDate=regDate;
    }

    public String getID()
    {
        return id;
    }

    public void setID(String id)
    {
        this.id=id;
    }

    public String getIMAGE()
    {
        return image;
    }

    public void setIMAGE(String image)
    {
        this.image=image;
    }

    public String getTTL()
    {
        return title;
    }

    public void setSORT(String sort)
    {
        this.sort=sort;
    }

    public String getSORT()
    {
        return sort;
    }

    public void setTTL(String title)
    {
        this.title=title;
    }

    public List<String> getSEASON()
    {
        return season;
    }

    public void setSEASON(List<String> season)
    {
        this.season=season;
    }

    public String getCOLOR()
    {
        return color;
    }

    public void setCOLOR(String color)
    {
        this.color=color;
    }

    public String getMEMO()
    {
        return memo;
    }

    public void setMEMO(String memo)
    {
        this.memo=memo;
    }


    public String getREGDATE()
    {
        return regDate;
    }

    public void setREGDATE(String memo)
    {
        this.regDate=regDate;
    }

    @Override
    public String toString(){
        return "ClotheDTO[id="+id+",title="+title+",image="+image+",sort="+sort+",season="+season+",memo="+memo+",color="+color+",date="+regDate+"]";
    }
}
