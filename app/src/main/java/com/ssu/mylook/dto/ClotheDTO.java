package com.ssu.mylook.dto;
//DB의 데이터를 저장할 클래스

import java.util.List;

public class ClotheDTO {
    private String id;
    private String image;
    private String title;
    private List<String> season;
    private String memo;

    public ClotheDTO()
    {
        super();
    }
    public ClotheDTO(String id, String image, String title, List<String> seasons, String memo)
    {
        super();
        this.id=id;
        this.image=image;
        this.title=title;
        this.season=season;
        this.memo=memo;
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

    public void setIMAGE()
    {
        this.image=image;
    }

    public String getTTL()
    {
        return title;
    }

    public void setTTL()
    {
        this.title=title;
    }

    public List<String> getSEASON()
    {
        return season;
    }

    public void setSEASON()
    {
        this.season=season;
    }

    public String getMEMO()
    {
        return memo;
    }

    public void setMEMO()
    {
        this.memo=memo;
    }

    @Override
    public String toString(){
        return "ClotheDTO[id="+id+",title="+title+",image="+image+",season="+season+",memo="+memo+"]";
    }
}
