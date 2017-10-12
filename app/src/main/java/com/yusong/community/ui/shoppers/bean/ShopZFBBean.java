package com.yusong.community.ui.shoppers.bean;

import java.io.Serializable;

/**
 * Created by ruanjian4 on 2017/2/28.
 */

public class ShopZFBBean implements Serializable {
    private String orderId;
    private String alipayPartner;
    private String alipaySeller;
    private String alipayAppRsaPublic;
    private String alipayAppRsaPrivate;
    private String notifyUrl;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAlipayPartner() {
        return alipayPartner;
    }

    public void setAlipayPartner(String alipayPartner) {
        this.alipayPartner = alipayPartner;
    }

    public String getAlipaySeller() {
        return alipaySeller;
    }

    public void setAlipaySeller(String alipaySeller) {
        this.alipaySeller = alipaySeller;
    }

    public String getAlipayAppRsaPublic() {
        return alipayAppRsaPublic;
    }

    public void setAlipayAppRsaPublic(String alipayAppRsaPublic) {
        this.alipayAppRsaPublic = alipayAppRsaPublic;
    }

    public String getAlipayAppRsaPrivate() {
        return alipayAppRsaPrivate;
    }

    public void setAlipayAppRsaPrivate(String alipayAppRsaPrivate) {
        this.alipayAppRsaPrivate = alipayAppRsaPrivate;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
