package com.ssu.mylook;

/*계층간 데이터 교환을 위한 클래스 dto(data transfer object)*/

import java.util.Map;

public class CustomDTO {
    private String rank;
    private int resId;
    private String title;
    private String content;
    private String img;
    private double rating;
    private String date;
    private int count;

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRank(){
        return rank;
    }
    public void setRating(double rating){
        this.rating=rating;
    }
    public double getRating(){
        return rating;
    }

//    public int getResId() {
//        return resId;
//    }
//    public String getImg(){
//        return img;
//    }
//    public void setImg(String img){
//        this.img=img;
//    }
//
//    public void setResId(int resId) {
//        this.resId = resId;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date=date;
    }

    public int getCount(){
        return count;
    }
    public void setCount(int count){
        this.count=count;
    }

    public static CustomDTO mapToDTO(Map<String,Object> data){
        CustomDTO arrCoordi  = new CustomDTO();
        arrCoordi.setTitle((String)data.get("title"));
        arrCoordi.setRating((double)data.get("rating"));
        arrCoordi.setDate((String)data.get("reg_date"));
        //arrCoordi.setCount((int)data.get("count"));
        return arrCoordi;
    }


}
