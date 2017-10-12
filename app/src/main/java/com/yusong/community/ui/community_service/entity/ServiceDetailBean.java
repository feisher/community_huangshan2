package com.yusong.community.ui.community_service.entity;

/**
 * @author Mr_Peng
 * @created at 2017-09-22.
 * @describe: null
 */

public class ServiceDetailBean {

    /**
     * communityId : 1
     * shopId : 1
     * shopName :
     * officeTime :
     * address :
     * tel :
     * introduction : xxxxx
     * memo : xxxxx
     * shopLogo : xxxxx
     * shopImage : xxxxx
     */

    private int communityId;
    private int shopId;
    private String shopName;
    private String officeTime;
    private String address;
    private String tel;
    private String introduction;
    private String memo;
    private String shopLogo;
    private String shopImage;

    public int getCommunityId() {
        return communityId;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public String getShopImage() {
        return shopImage;
    }

    public void setShopImage(String shopImage) {
        this.shopImage = shopImage;
    }
}
