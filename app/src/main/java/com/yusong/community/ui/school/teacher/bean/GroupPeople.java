package com.yusong.community.ui.school.teacher.bean;


public class GroupPeople {
    private int imgInt;
    private String name;
    private String count;

    public GroupPeople(int imgInt, String name, String count) {
        this.imgInt = imgInt;
        this.name = name;
        this.count = count;
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
