package com.yusong.club.ui.visitor.entity;

import java.io.Serializable;

/**
 * @author Mr_Peng
 * @created at 2017-08-30.
 * @describe: null
 */

public class ThroughCardBean implements Serializable {

    private String permitCode;
    /**
     * visitorName : 姓名
     * visitorTime : 有效时间
     */

    private String visitorName;
    private String visitorTime;
    private int sex;

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPermitCode() {
        return permitCode;
    }

    public void setPermitCode(String permitCode) {
        this.permitCode = permitCode;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVisitorTime() {
        return visitorTime;
    }

    public void setVisitorTime(String visitorTime) {
        this.visitorTime = visitorTime;
    }
}
