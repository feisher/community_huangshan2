package com.yusong.community.ui.school.mvp.entity;

/**
 * Created by ruanjian on 2017/3/28.
 */

public class ParentInfo {
private String portrait;//: string 头像
    private String realName;//: string 姓名
    private String  mobile;//: string 手机号
    private String  studentName;//: string 学生姓名
    private String studentNo;//: string 学生学号
    private String  clazzName;//: string 班级名称
    private String  schoolName;//: string 学校名称

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
