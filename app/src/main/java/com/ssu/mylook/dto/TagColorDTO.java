package com.ssu.mylook.dto;

public class TagColorDTO {
    private String field;
    private int count;

    public TagColorDTO() { super(); }
    public TagColorDTO(String field, int count){
        this.field=field;
        this.count=count;
    }

    //private Map<String,Boolean> tags;

    public void setField(String field){this.field=field;}
    public String getField(){return field;}
    public void setCount(int count){this.count=count;}
    public int getCount(){return count;}
}
