package com.yusong.community.ui.school.teacher.bean;

/**
 * Created by admin on 2017/1/12.
 */
public class Schedule {
    private String subject;
    private String id="";
    private boolean tagId=false;

    public Schedule(String subject, String id, boolean tagId) {
        this.subject = subject;
        this.id = id;
        this.tagId = tagId;
    }

    public Schedule() {
    }

    public Schedule(String subject) {
        this.subject = subject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isTagId() {
        return tagId;
    }

    public void setTagId(boolean tagId) {
        this.tagId = tagId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
