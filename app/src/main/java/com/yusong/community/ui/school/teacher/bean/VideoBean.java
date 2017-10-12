package com.yusong.community.ui.school.teacher.bean;

/**
 * Created by admin on 2017/1/11.
 */
public class VideoBean  {
    private int imgInt;
    private String name;
    private String teacher;
    private String score;

    public VideoBean(int imgInt, String name, String teacher, String score) {
        this.imgInt = imgInt;
        this.name = name;
        this.teacher = teacher;
        this.score = score;
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

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
