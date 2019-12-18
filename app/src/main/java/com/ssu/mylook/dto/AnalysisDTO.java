package com.ssu.mylook.dto;

public class AnalysisDTO {
    private String name;
    private String img;

    public AnalysisDTO(){super();}
    public AnalysisDTO(String name, String img){this.name=name;this.img=img;}

    public void setName(String name){this.name=name;}
    public String getName(){return name;}
    public void setImg(String img){this.img=img;}
    public String getImg(){return img;}

}
