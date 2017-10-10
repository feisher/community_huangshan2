package com.yusong.club.ui.school.teacher.bean;

/**
 * Created by ruanjian on 2017/3/6.
 */

public class AllTeacher {
    private int id;//: int 老师id
    private String mobile;//: string 手机号
    private String userName;//: string 老师名称
    private String jobName;//
    private int onDuty;//: 1 是 0 否

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getOnDuty() {
        return onDuty;
    }

    public void setOnDuty(int onDuty) {
        this.onDuty = onDuty;
    }
}
