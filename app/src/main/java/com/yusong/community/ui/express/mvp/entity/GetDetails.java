package com.yusong.community.ui.express.mvp.entity;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */
public class GetDetails {

    /**
     * orderNo : 5555
     * authCode : 123456
     * courierMobile : 17052709683
     * takeTime : 2017-02-14 13:02:26
     * companyName : 顺丰快递
     * boxType : 1
     * terminalId : 11
     * orderStatusName : 初始
     * courierName : liu
     * companyId : 18
     * boxNum : 1-2
     * createTime : 2017-02-14 13:03:10
     * putTime : 2017-02-14 13:02:21
     * id : SE17000000000001
     * healthStatusName : 正常
     */

    private String orderNo;
    private String authCode;
    private String courierMobile;
    private String takeTime;
    private String companyName;
    private int boxType;
    private int terminalId;
    private String orderStatusName;
    private String courierName;
    private int companyId;
    private String boxNum;
    private String createTime;
    private String putTime;
    private String id;
    private String healthStatusName;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getCourierMobile() {
        return courierMobile;
    }

    public void setCourierMobile(String courierMobile) {
        this.courierMobile = courierMobile;
    }

    public String getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(String takeTime) {
        this.takeTime = takeTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getBoxType() {
        return boxType;
    }

    public void setBoxType(int boxType) {
        this.boxType = boxType;
    }

    public int getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(int terminalId) {
        this.terminalId = terminalId;
    }

    public String getOrderStatusName() {
        return orderStatusName;
    }

    public void setOrderStatusName(String orderStatusName) {
        this.orderStatusName = orderStatusName;
    }

    public String getCourierName() {
        return courierName;
    }

    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(String boxNum) {
        this.boxNum = boxNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPutTime() {
        return putTime;
    }

    public void setPutTime(String putTime) {
        this.putTime = putTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHealthStatusName() {
        return healthStatusName;
    }

    public void setHealthStatusName(String healthStatusName) {
        this.healthStatusName = healthStatusName;
    }
}
