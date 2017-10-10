package com.yusong.club.ui.me.mvp.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by feisher on 2017/1/21.
 */

public class UserInfo implements Serializable{

    /**
     * agentId : 1
     * agentName :
     * balance : 2.45
     * gender : 1
     * imAppKey : 00
     * imToken :
     * mail :
     * moduleList : ["xxx","xxxx","xxx"]
     * portrait :
     * qrCode :
     * realName :
     */

    private int agentId;
    private String agentName;
    private String balance;
    private int gender;
    private String imAppKey;
    private String imToken;
    private String mail;
    private String portrait;
    private String qrCode;
    private int communityId;
    private String communityName;
    private String aboutUrl;
    private String realName;
    private String nickname;
    private String mobile;
    private int score;
    private int level;
    private int signIn;
    private int redPacketCount;
    private List<String> moduleList;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSignIn() {
        return signIn;
    }

    public void setSignIn(int signIn) {
        this.signIn = signIn;
    }

    public int getRedPacketCount() {
        return redPacketCount;
    }

    public void setRedPacketCount(int redPacketCount) {
        this.redPacketCount = redPacketCount;
    }

    public String getAboutUrl() {
        return aboutUrl;
    }

    public void setAboutUrl(String aboutUrl) {
        this.aboutUrl = aboutUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public int getCommunityId() {
        return communityId;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getImAppKey() {
        return imAppKey;
    }

    public void setImAppKey(String imAppKey) {
        this.imAppKey = imAppKey;
    }

    public String getImToken() {
        return imToken;
    }

    public void setImToken(String imToken) {
        this.imToken = imToken;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public List<String> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<String> moduleList) {
        this.moduleList = moduleList;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "agentId=" + agentId +
                ", agentName='" + agentName + '\'' +
                ", balance='" + balance + '\'' +
                ", gender=" + gender +
                ", imAppKey='" + imAppKey + '\'' +
                ", imToken='" + imToken + '\'' +
                ", mail='" + mail + '\'' +
                ", portrait='" + portrait + '\'' +
                ", qrCode='" + qrCode + '\'' +
                ", communityId=" + communityId +
                ", communityName='" + communityName + '\'' +
                ", aboutUrl='" + aboutUrl + '\'' +
                ", realName='" + realName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", mobile='" + mobile + '\'' +
                ", score=" + score +
                ", level=" + level +
                ", signIn=" + signIn +
                ", redPacketCount=" + redPacketCount +
                ", moduleList=" + moduleList +
                '}';
    }
}
