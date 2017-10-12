package com.yusong.community.ui.express.mvp.entity;

import java.io.Serializable;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class ContactGroup implements Serializable{

    int id;
    String contactName;
    String province;
    String provinceName;
    String city;
    String cityName;
    String district;
    String districtName;
    String mobile;
    String street;
    int favoriteSenderFlag;
    int favoriteReceiverFlag;
    int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getFavoriteSenderFlag() {
        return favoriteSenderFlag;
    }

    public void setFavoriteSenderFlag(int favoriteSenderFlag) {
        this.favoriteSenderFlag = favoriteSenderFlag;
    }

    public int getFavoriteReceiverFlag() {
        return favoriteReceiverFlag;
    }

    public void setFavoriteReceiverFlag(int favoriteReceiverFlag) {
        this.favoriteReceiverFlag = favoriteReceiverFlag;
    }
}
