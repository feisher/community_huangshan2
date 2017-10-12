package com.yusong.community.ui.school.teacher.bean;


public class Duty {
    private String name;
    private String job;
    private String mobile;

    public Duty(String name, String job, String mobile) {
        this.name = name;
        this.job = job;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
