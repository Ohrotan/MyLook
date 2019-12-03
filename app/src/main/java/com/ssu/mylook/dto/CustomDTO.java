package com.ssu.mylook.dto;

/*계층간 데이터 교환을 위한 클래스 dto(data transfer object)*/

import java.util.List;
import java.util.Map;

public class CustomDTO {
    private String name;
    private String img;
    private String regDate;
    private List<String> seasons;
    //private String[] seasons;
    private String tag;
    private double rating;
    private int count;
    private String Id;



    //아직 수정중
    private String title;
    private String content;
    private int rank;

    public CustomDTO() { }
    public CustomDTO(String name, String img, String regDate, List<String> seasons, String tag, double rating, int count){
        this.name=name;
        this.img=img;
        this.regDate=regDate;
        this.seasons=seasons;
        this.tag=tag;
        this.rating=rating;
        this.count=count;
    }

    public static CustomDTO mapToDTO(Map<String,Object> data){
        CustomDTO customDTO  = new CustomDTO();
        customDTO.setImg((String)data.get("img"));
        customDTO.setName((String)data.get("name"));
        customDTO.setRating((double)data.get("rating"));
        customDTO.setDate((String)data.get("regDate"));
        customDTO.setTag((String)data.get("tag"));
        customDTO.setCount((int)data.get("count"));
        return customDTO;
    }


    //배열 써서 사용했던 Rank
    public void setRank(int rank) {
        this.rank = rank;
    }
    public int getRank(){
        return rank;
    }

    //여기서부터는 실제 사용할 것들
    public void setRating(double rating){
        this.rating=rating;
    }
    public double getRating(){
        return rating;
    }


    public String getImg(){
        return img;
    }
    public void setImg(String img){
        this.img=img;
    }
    public void setName(String name){this.name=name;}
    public String getName(){return name;}

    public List<String> getSeasons(){
        return seasons;
    }
//    public void setSeason(int index, String seasons){
//        this.seasons[index]=seasons;
//    }

    public int getCount(){
        return count;
    }
    public void setCount(int count){
        this.count=count;
    }

    public String getDate() {
        return regDate;
    }
    public void setDate(String regDate) {
        this.regDate = regDate;
    }
    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getId() {
        return Id;
    }
    public void setId(String Id) {
        this.Id = Id;
    }




    //얜 아직 모르겠는것
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



}
//CustomDTO seasonDTo = new CustomDTO("계절", new String[]{
//        "봄","여름","가을"
//});
