package com.yusong.community.ui.home.mvp.cache;

import java.io.Serializable;

/**
 * Created by quaner on 16/12/30.
 *
 * 用于缓存账号、密码、token
 */
public class TokenInfo implements Serializable{

    String name;
    String pwd;
    String token;
    int id;//用户id
    int expireIn;//token失效时间
    long saveTime;
    public String imAccount;

    public String getImAccount() {
        return imAccount;
    }

    public void setImAccount(String imAccount) {
        this.imAccount = imAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public long getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(long saveTime) {
        this.saveTime = saveTime;
    }


}
