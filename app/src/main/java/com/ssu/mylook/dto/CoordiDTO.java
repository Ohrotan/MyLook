package com.ssu.mylook.dto;

import java.util.List;

public class CoordiDTO {
    String id;
    String img;
    String name;
    List<String> seasons;
    List<String> used;
    String tag;
    float rating;
    String regDate;
    int count;
    String userId;

    public CoordiDTO() {
        super();
    }

    public CoordiDTO(String id, String img, String name, List<String> seasons, String tag,
                     float rating, String regDate, int count, String userId) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.seasons = seasons;
        this.tag = tag;
        this.rating = rating;
        this.regDate = regDate;
        this.count = count;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<String> seasons) {
        this.seasons = seasons;
    }


    public List<String> getUsed() {
        return used;
    }

    public void setUsed(List<String> used) {
        this.used = used;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CoordiDTO{" +
                "id='" + id + '\'' +
                ", img='" + img + '\'' +
                ", name='" + name + '\'' +
                ", seasons=" + seasons.toString() +
                ", tag='" + tag + '\'' +
                ", rating=" + rating +
                ", regDate='" + regDate + '\'' +
                ", count=" + count +
                ", userId='" + userId + '\'' +
                '}';
    }


}
