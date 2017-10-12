package com.yusong.community.ui.school.teacher.bean;

/**
 * Created by admin on 2017/1/13.
 */
public class Student {
    private int imgInt;
    private String name;

    public Student(int imgInt, String name) {
        this.imgInt = imgInt;
        this.name = name;
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
}
