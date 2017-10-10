package com.yusong.club.ui.charge.bean;

/**
 * Created by Mr_Peng on 2017/2/9.
 */

public class SettingBean {
    private String tel;
    private String helpUrl;
    private int lockExpireTime;
    private int bespeakExpireTime;

    public String getHelpUrl() {
        return helpUrl;
    }

    public void setHelpUrl(String helpUrl) {
        this.helpUrl = helpUrl;
    }

    public int getLockExpireTime() {
        return lockExpireTime;
    }

    public void setLockExpireTime(int lockExpireTime) {
        this.lockExpireTime = lockExpireTime;
    }

    public int getBespeakExpireTime() {
        return bespeakExpireTime;
    }

    public void setBespeakExpireTime(int bespeakExpireTime) {
        this.bespeakExpireTime = bespeakExpireTime;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUrl() {
        return helpUrl;
    }

    public void setUrl(String helpUrl) {
        this.helpUrl = helpUrl;
    }
}
