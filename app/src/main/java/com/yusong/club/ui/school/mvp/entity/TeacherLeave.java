package com.yusong.club.ui.school.mvp.entity;

/**
 * Created by ruanjian on 2017/3/27.
 */

public class TeacherLeave {
   private int id;//: int 名称,
   private String createUserName;// 家长姓名
   private String createUserIconPath;// 家长头像
   private String auditName;//: String 审核老师姓名
   private String auditUserIconPath;// String 审核老师头像
   private String studentName;//: string 学生姓名
   private String beginTime;//: string 开始时间
   private String endTime;//: string 截至时间
   private int status;//: int 状态  1 待审核 2审核通过 3 审核不通过
   private String createTime;//: string 创建时间
    private String reason ;//：string 申请理由
    private String auditMemo ;//：string 审核描述

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAuditMemo() {
        return auditMemo;
    }

    public void setAuditMemo(String auditMemo) {
        this.auditMemo = auditMemo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public String getAuditUserIconPath() {
        return auditUserIconPath;
    }

    public void setAuditUserIconPath(String auditUserIconPath) {
        this.auditUserIconPath = auditUserIconPath;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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
