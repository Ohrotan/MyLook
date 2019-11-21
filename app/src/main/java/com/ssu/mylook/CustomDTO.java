package com.ssu.mylook;

/*계층간 데이터 교환을 위한 클래스 dto(data transfer object)*/

public class CustomDTO {
    private String rank;
    private int resId;
    private String title;
    private String content;

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRank(){
        return rank;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

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
}