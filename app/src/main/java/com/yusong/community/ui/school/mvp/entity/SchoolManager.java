package com.yusong.community.ui.school.mvp.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * create by feisher on 2017/3/16
 * Email：458079442@qq.com
 */
public class SchoolManager  implements Serializable{


    /**
     * portrait : http:1213.jpg
     * realName : 姓名
     * mobile : 15888888888
     * schoolName : 浙江大学
     */

    @SerializedName("portrait")
    private String portrait;
    @SerializedName("realName")
    private String realName;
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("schoolName")
    private String schoolName;

    private String imAccountId;

    public String getImAccountId() {

        return imAccountId;
    }

    public void setImAccountId(String imAccountId) {
        this.imAccountId = imAccountId;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public String toString() {
        return "SchoolManager{" +
                "portrait='" + portrait + '\'' +
                ", realName='" + realName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", schoolName='" + schoolName + '\'' +
                '}';
    }
}
