package com.ssu.mylook.dto;

public class ClotheTitleDTO {
    private String clotheTitle;

    public ClotheTitleDTO(String clotheTitle)
    {
        this.clotheTitle=clotheTitle;
    }

    public String getClotheTitle(){
        return this.clotheTitle;
    }
}
