package com.yusong.club.ui.shoppers.used.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr_Peng
 *         crated at on 2017/3/18.
 *         描述: 二手商品
 */

public class UsedBean implements Serializable {
    private int id;
    private int ownerId;
    private String passport;
    private String nickname;
    private String createTime;
    private String auditTime;

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    private String ownerMobile;
    private String introduction;
    private List<String> imageList = new ArrayList<String>();
    private float price;
    private float showPrice;
    private String address;
    private double distance;
    private int replayCount;

    public int getReplayCount() {
        return replayCount;
    }

    public void setReplayCount(int replayCount) {
        this.replayCount = replayCount;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickName() {
        return nickname;
    }

    public void setNickName(String nickname) {
        this.nickname = nickname;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerMobile() {
        return ownerMobile;
    }

    public void setOwnerMobile(String ownerMobile) {
        this.ownerMobile = ownerMobile;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(float showPrice) {
        this.showPrice = showPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistance() {
        return String.format("%.2f", distance / 1000);
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
