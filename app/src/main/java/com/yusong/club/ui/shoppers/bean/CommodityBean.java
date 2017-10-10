package com.yusong.club.ui.shoppers.bean;

import java.io.Serializable;

/**
 * Created by Mr_Peng on 2017/2/28.
 * 抢购商品列表
 */

public class CommodityBean implements Serializable {
    private int id;
    private int itemId;
    private String itemName;
    private String shopName;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    private int shopId;
    private String introduction;
    private String[] imageList;
    private double price;
    private double showPrice;
    private int soldAmount;
    private int leftAmount;
    private int amount;
    private int limitAmount;
    private int limitRestAmount;
    private String []detailsImageList;

    public int getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(int leftAmount) {
        this.leftAmount = leftAmount;
    }

    public String[] getDetailsImageList() {
        return detailsImageList;
    }

    public void setDetailsImageList(String[] detailsImageList) {
        this.detailsImageList = detailsImageList;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getLimitRestAmount() {
        return limitRestAmount;
    }

    public void setLimitRestAmount(int limitRestAmount) {
        this.limitRestAmount = limitRestAmount;
    }

    public int getSoldAmount() {
        return soldAmount;
    }

    public void setSoldAmount(int soldAmount) {
        this.soldAmount = soldAmount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(int limitAmount) {
        this.limitAmount = limitAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String[] getImageList() {
        return imageList;
    }

    public void setImageList(String[] imageList) {
        this.imageList = imageList;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(double showPrice) {
        this.showPrice = showPrice;
    }
}
