package com.yusong.club.ui.school.teacher.bean;


public class Homework {
    private int subject;//1 语文 2 数学  3 英语  4 地理
    private String time;
    private String workContent;

    public Homework(int subject, String time, String workContent) {
        this.subject = subject;
        this.time = time;
        this.workContent = workContent;
    }

    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }
}
