package com.yusong.community.ui.visitor.entity;

/**
 * @author Mr_Peng
 * @created at 2017-08-30.
 * @describe: null
 */

public class CardDetailsBean {
    /**
     * activeFlag : 1
     * proprietorName : 业主姓名
     * mobile : 123154654
     * address : 业主地址
     * visitorName : 访客姓名
     * sex : 1
     * date : 12131
     * isDrive : 1
     * plateNumber : 浙a
     */

    private int activeFlag;
    private String proprietorName;
    private String mobile;
    private String address;
    private String visitorName;
    private int sex;
    private String date;
    private int isDrive;
    private String plateNumber;

    public int getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(int activeFlag) {
        this.activeFlag = activeFlag;
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

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIsDrive() {
        return isDrive;
    }

    public void setIsDrive(int isDrive) {
        this.isDrive = isDrive;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

}
