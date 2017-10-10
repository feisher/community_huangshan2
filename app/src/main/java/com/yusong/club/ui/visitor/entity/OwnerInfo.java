package com.yusong.club.ui.visitor.entity;


import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author Mr_Peng
 * @created at 2017-08-30.
 * @describe: null
 */

public class OwnerInfo implements Serializable {

    /**
     * communityId : 2
     * communityName : 社区名称
     * proprietorName : 业主姓名
     * mobile : 18121200
     * address : 地址
     */

    private int communityId;
    private String communityName;
    private String proprietorName;
    private String mobile;
    private String address;
    private int proprietorId;

    public int getProprietorId() {
        return proprietorId;
    }

    public void setProprietorId(int proprietorId) {
        this.proprietorId = proprietorId;
    }

    public int getCommunityId() {
        return communityId;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getProprietorName() {
        return proprietorName;
    }

    public void setProprietorName(String proprietorName) {
        this.proprietorName = proprietorName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
