package com.yusong.community.ui.school.school.bean;

/**
 * Created by ruanjian on 2017/3/7.
 */

public class ActivityBean {
  private int  id;//: int 名称,
  private String  activityName;//: string 活动名称
    private String createUserName;// 活动创建者姓名
  private String  beginTime;//: string 开始时间
  private String  endTime;//: string 截至时间
  private int  status;//: int 状态
   private String createTime;//: string 创建时间
    private String  createUserIconPath;//活动创建者头像
    private String imagePath;// 活动展示图片
    private String memo ;//活动简介
    private String supportAmount;// 点赞数
    private String commentAmount;// 评论数

    public String getSupportAmount() {
        return supportAmount;
    }

    public void setSupportAmount(String supportAmount) {
        this.supportAmount = supportAmount;
    }

    public String getCommentAmount() {
        return commentAmount;
    }

    public void setCommentAmount(String commentAmount) {
        this.commentAmount = commentAmount;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCreateUserIconPath() {
        return createUserIconPath;
    }

    public void setCreateUserIconPath(String createUserIconPath) {
        this.createUserIconPath = createUserIconPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
