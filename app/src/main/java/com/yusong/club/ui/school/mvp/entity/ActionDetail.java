package com.yusong.club.ui.school.mvp.entity;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/25.
 */

public class ActionDetail {

    /**
     * membersName : null
     * supportAmount : 0
     * activityName : 刘娇娇
     * memo : 考虑考虑
     * createUserName : 18457148548
     * imagelist : ["http://122.224.164.50:8100//static/school/clazz_activity_portrait/29/39f66d1ea5662a4c0daaa63a608b035883f9.jpeg","http://122.224.164.50:8100//static/school/clazz_activity_portrait/29/c06ba9f6a7fffa40d6a96e4af58e21d7c3f1.jpeg","http://122.224.164.50:8100//static/school/clazz_activity_portrait/29/6aa64503a60baa46acaaadba1b3c8c31a222.jpeg",null]
     * createUserIconPath : http://122.224.164.50:8100//static/basic/customer_portrait/2017-03-10/464e60d9ae80fa4df9ab22eaa86ae1bc5f7d.jpeg
     * createTime : 2017-03-25 13:32:24
     * id : 29
     * beginTime : 2017-03-28 00:00:00
     * endTime : 2017-03-30 00:00:00
     * commentAmount : 2
     * status : 1
     */

    private Object membersName;
    private int supportAmount;
    private String activityName;
    private String memo;
    private String createUserName;
    private String createUserIconPath;
    private String createTime;
    private int id;
    private String beginTime;
    private String endTime;
    private int commentAmount;
    private int status;
    private List<String> imagelist;
    private int isJoined;

    public int getIsJoined() {
        return isJoined;
    }

    public void setIsJoined(int isJoined) {
        this.isJoined = isJoined;
    }

    public Object getMembersName() {
        return membersName;
    }

    public void setMembersName(Object membersName) {
        this.membersName = membersName;
    }

    public int getSupportAmount() {
        return supportAmount;
    }

    public void setSupportAmount(int supportAmount) {
        this.supportAmount = supportAmount;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getCreateUserIconPath() {
        return createUserIconPath;
    }

    public void setCreateUserIconPath(String createUserIconPath) {
        this.createUserIconPath = createUserIconPath;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getCommentAmount() {
        return commentAmount;
    }

    public void setCommentAmount(int commentAmount) {
        this.commentAmount = commentAmount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getImagelist() {
        return imagelist;
    }

    public void setImagelist(List<String> imagelist) {
        this.imagelist = imagelist;
    }
}
