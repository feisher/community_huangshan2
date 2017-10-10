package com.yusong.club.ui.school.mvp.entity;

/**
 * Created by ruanjian on 2017/3/25.
 */

public class DetailComment {
    /**
     * createUserIconPath : http://122.224.164.50:8100//static/basic/customer_portrait/2017-03-10/464e60d9ae80fa4df9ab22eaa86ae1bc5f7d.jpeg
     * createTime : 2017-03-23 20:39:07
     * commentId : 9
     * createUserName : 18457148548
     * content : 哦哦
     */

    private String createUserIconPath;
    private String createTime;
    private int commentId;
    private String createUserName;
    private String content;
    private int isJoined;

    public int getIsJoined() {
        return isJoined;
    }

    public void setIsJoined(int isJoined) {
        this.isJoined = isJoined;
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

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
