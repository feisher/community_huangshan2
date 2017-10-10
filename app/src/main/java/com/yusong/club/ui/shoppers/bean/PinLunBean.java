package com.yusong.club.ui.shoppers.bean;

import java.io.Serializable;

/**
 * Created by Mr_Peng on 2017/2/28.
 */

public class PinLunBean implements Serializable {
    private String mobile;
    private String content;
    private String createTime;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
