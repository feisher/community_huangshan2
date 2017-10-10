package com.yusong.club.ui.school.teacher.bean;


public class Teacher {
    private int imgInt;
    private String name;
    private String state;
    private String subject;

    public Teacher(int imgInt, String name, String state, String subject) {
        this.imgInt = imgInt;
        this.name = name;
        this.state = state;
        this.subject = subject;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
