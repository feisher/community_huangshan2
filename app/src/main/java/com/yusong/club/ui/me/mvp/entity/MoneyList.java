package com.yusong.club.ui.me.mvp.entity;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * <li>时间：17/3/9 09:36 </li>
 * </ul>
 */
public class MoneyList {


    /**
     * money : -200
     * createTime : 2017-03-08 17:08:28
     * bizTypeName : 存件支付
     * id : 135
     */

    private int money;
    private String createTime;
    private String bizTypeName;
    private int id;
    private int balance;
    private int gift;

    public int getGift() {
        return gift;
    }

    public void setGift(int gift) {
        this.gift = gift;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getBizTypeName() {
        return bizTypeName;
    }

    public void setBizTypeName(String bizTypeName) {
        this.bizTypeName = bizTypeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
