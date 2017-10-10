package com.yusong.club.ui.shoppers.bean;

import java.util.List;

/**
 * @author Mr_Peng
 * @created at 2017/6/14 10:58.
 * @describe: null
 */

public class ShopDetailsBean {
    private String shopName;
    private String officeTime;
    private String shopLocation;
    private String tel;
    private List<String> imageList;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getOfficeTime() {
        return officeTime;
    }

    public void setOfficeTime(String officeTime) {
        this.officeTime = officeTime;
    }

    public String getShopLocation() {
        return shopLocation;
    }

    public void setShopLocation(String shopLocation) {
        this.shopLocation = shopLocation;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }
}
