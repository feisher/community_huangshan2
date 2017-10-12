package com.yusong.community.ui.visitor.entity;

import java.io.Serializable;

/**
 * @author Mr_Peng
 * @created at 2017-09-01.
 * @describe: null
 */

public class CommuntitySetingBean implements Serializable {
    private String tel;
    private String serviceTime;

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
