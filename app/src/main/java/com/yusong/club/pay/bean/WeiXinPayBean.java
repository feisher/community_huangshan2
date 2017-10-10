package com.yusong.club.pay.bean;

/**
 * Created by Mr_Peng on 2017/1/11.
 */

public class WeiXinPayBean {
   private String appId;
   private String partnerId;
   private String prepayId;
   private String nonceStr;
   private String timeStamp;
   private String weixinPackage;
   private String sign;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getWeixinPackage() {
        return weixinPackage;
    }

    public void setWeixinPackage(String weixinPackage) {
        this.weixinPackage = weixinPackage;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
