package com.yusong.community.ui.school.teacher.bean;

/**
 * Created by admin on 2017/1/13.
 */
public class Evaluate {
    private int imgInt;
    private String name;
    private int ratingNum;//评分
    private String evaContent;
    private String time;

    public Evaluate(int imgInt, String name, int ratingNum, String evaContent, String time) {
        this.imgInt = imgInt;
        this.name = name;
        this.ratingNum = ratingNum;
        this.evaContent = evaContent;
        this.time = time;
    }

    public int getImgInt() {
        return imgInt;
    }

    public void setImgInt(int imgInt) {
        this.imgInt = imgInt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRatingNum() {
        return ratingNum;
    }

    public void setRatingNum(int ratingNum) {
        this.ratingNum = ratingNum;
    }

    public String getEvaContent() {
        return evaContent;
    }

    public void setEvaContent(String evaContent) {
        this.evaContent = evaContent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
