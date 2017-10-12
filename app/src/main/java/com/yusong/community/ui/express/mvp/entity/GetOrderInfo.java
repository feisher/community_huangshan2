package com.yusong.community.ui.express.mvp.entity;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class GetOrderInfo {
    /**
     * address : 中华人民共和国
     * authCode : null
     * payTime : 2017-01-18 00:00:00
     * takeTime : null
     * boxType : 3
     * terminalId : 1
     * orderStatusName : 已付款
     * companyId : 14
     * boxNum : 1-1
     * createTime : 2017-01-18 00:00:00
     * payStatusName : 未付
     * putTime : 2017-01-18 00:00:00
     * id : DE17000000000005
     * healthStatusName : 派取超时
     * "orderNo":"123456789"
     */

    private String orderNo;
    private String address;
    private Object authCode;
    private String payTime;
    private Object takeTime;
    private int boxType;
    private int terminalId;
    private String orderStatusName;
    private int companyId;
    private String boxNum;
    private String createTime;
    private String payStatusName;
    private String putTime;
    private String id;
    private String healthStatusName;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getAuthCode() {
        return authCode;
    }

    public void setAuthCode(Object authCode) {
        this.authCode = authCode;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public Object getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(Object takeTime) {
        this.takeTime = takeTime;
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

    public String getPayStatusName() {
        return payStatusName;
    }

    public void setPayStatusName(String payStatusName) {
        this.payStatusName = payStatusName;
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
