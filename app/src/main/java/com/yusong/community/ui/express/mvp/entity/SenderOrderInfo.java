package com.yusong.community.ui.express.mvp.entity;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class SenderOrderInfo {


    /**
     * senderAddress : 浙江省杭州市西湖
     * orderNo : 12356
     * charge : 0.01
     * authCode : 123456
     * receiver : 升水
     * payTime : 2017-02-14 10:42:21
     * takeTime : 2017-02-14 10:42:17
     * receiverMobile : 15158008840
     * memo : 1
     * senderMobile : 13777351251
     * boxType : 3
     * terminalId : 11
     * thingAmount : 1
     * receiverAddress : 浙江省杭
     * orderStatusName : 初始
     * companyId : 20
     * sender : 光哥
     * boxNum : 1-1
     * createTime : 2017-02-14 10:42:45
     * payStatusName : 未付
     * putTime : 2017-02-14 10:42:14
     * id : SE17000000000015
     * healthStatusName : 正常
     * thing : 图书
     * orderStatus 1 初始 2 入箱 3 客户取出 4 维护取出 5 客户取消 6 线下取出
     */

    private String senderAddress;
    private String orderNo;
    private double charge;
    private String authCode;
    private String receiver;
    private String payTime;
    private String takeTime;
    private String receiverMobile;
    private String memo;
    private String senderMobile;
    private int boxType;
    private int terminalId;
    private int thingAmount;
    private String receiverAddress;
    private String orderStatusName;
    private int companyId;
    private String sender;
    private String boxNum;
    private String createTime;
    private String payStatusName;
    private String putTime;
    private String id;
    private String healthStatusName;
    private String thing;
    private int orderStatus;


    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(String takeTime) {
        this.takeTime = takeTime;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getSenderMobile() {
        return senderMobile;
    }

    public void setSenderMobile(String senderMobile) {
        this.senderMobile = senderMobile;
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

    public int getThingAmount() {
        return thingAmount;
    }

    public void setThingAmount(int thingAmount) {
        this.thingAmount = thingAmount;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
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

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
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

    public String getThing() {
        return thing;
    }

    public void setThing(String thing) {
        this.thing = thing;
    }
}
