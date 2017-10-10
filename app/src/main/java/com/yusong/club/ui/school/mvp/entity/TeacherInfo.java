package com.yusong.club.ui.school.mvp.entity;

/**
 * Created by ruanjian on 2017/3/28.
 */

public class TeacherInfo {
  private String  portrait;//: string 头像
    private String mobile;//: string 手机号
    private String teacherName;//: string 老师姓名
    private String courerName;//: string 课程
    private String job;//: string 职务名称

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getCourerName() {
        return courerName;
    }

    public void setCourerName(String courerName) {
        this.courerName = courerName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
