package com.yusong.community.ui.school.teacher.bean;

/**
 * Created by admin on 2017/1/13.
 */
public class ActionOne {
    private int imgInt;
    private String name;
    private String state;
    private String time;
    private String surplus;
    private String title;
    private String action_time;
    private String actionContent;

    public ActionOne(int imgInt, String name, String state, String time, String surplus, String title, String action_time, String actionContent) {
        this.imgInt = imgInt;
        this.name = name;
        this.state = state;
        this.time = time;
        this.surplus = surplus;
        this.title = title;
        this.action_time = action_time;
        this.actionContent = actionContent;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSurplus() {
        return surplus;
    }

    public void setSurplus(String surplus) {
        this.surplus = surplus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAction_time() {
        return action_time;
    }

    public void setAction_time(String action_time) {
        this.action_time = action_time;
    }

    public String getActionContent() {
        return actionContent;
    }

    public void setActionContent(String actionContent) {
        this.actionContent = actionContent;
    }
}
