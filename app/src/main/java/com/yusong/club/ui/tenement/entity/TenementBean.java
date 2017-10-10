package com.yusong.club.ui.tenement.entity;

import java.io.Serializable;

/**
 * @author Mr_Peng
 * @created at 2017-09-04.
 * @describe: null
 */

public class TenementBean implements Serializable {

    /**
     * proprietorId : 0
     * proprietorName : 业主
     * payName : 缴费名称
     * payType : 1
     * beginTime : 2017/01/01
     * endTime : 2017/01/01
     * amount : 1.5
     * isPay : 1
     * payTime : 缴费时间
     */

    private int orderId;
    private int proprietorId;
    private String proprietorName;
    private String payName;
    private int payType;
    private String beginTime;
    private String endTime;
    private double amount;
    private int isPay;
    private String payTime;
    private int isSelect;

    public int getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(int isSelect) {
        this.isSelect = isSelect;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProprietorId() {
        return proprietorId;
    }

    public void setProprietorId(int proprietorId) {
        this.proprietorId = proprietorId;
    }

    public String getProprietorName() {
        return proprietorName;
    }

    public void setProprietorName(String proprietorName) {
        this.proprietorName = proprietorName;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getIsPay() {
        return isPay;
    }

    public void setIsPay(int isPay) {
        this.isPay = isPay;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }
}
