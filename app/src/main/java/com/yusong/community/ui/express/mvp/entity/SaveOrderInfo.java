package com.yusong.community.ui.express.mvp.entity;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class SaveOrderInfo {
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
     * orderStatus 1 初始 2 入箱 3 客户取出 4 维护取出 5 客户取消 6 线下取出
     * putAuthCode: '存包码
     * takeAuthCode: '取件码'
     */

    private String address;
    private String payTime;
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
    private String putAuthCode;
    private String takeAuthCode;
    private int orderStatus;


    public String getPutAuthCode() {
        return putAuthCode;
    }

    public void setPutAuthCode(String putAuthCode) {
        this.putAuthCode = putAuthCode;
    }

    public String getTakeAuthCode() {
        return takeAuthCode;
    }

    public void setTakeAuthCode(String takeAuthCode) {
        this.takeAuthCode = takeAuthCode;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
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


    public String getHealthStatusName() {
        return healthStatusName;
    }

    public void setHealthStatusName(String healthStatusName) {
        this.healthStatusName = healthStatusName;
    }
}
