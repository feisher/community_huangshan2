package com.yusong.club.pay.bean;

/**
 * Created by Mr_Peng on 2017/1/11.
 */

public class ZhiFuBaoPayBean {
    String orderId;
    String alipayPartner;
    String alipaySeller;
    String alipayAccountName;
    String alipayAppRsaPublic;
    String alipayAppRsaPrivate;
    String notifyUrl;

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

    public String getAlipayAccountName() {
        return alipayAccountName;
    }

    public void setAlipayAccountName(String alipayAccountName) {
        this.alipayAccountName = alipayAccountName;
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
