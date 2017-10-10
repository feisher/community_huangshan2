package com.yusong.club.ui.home.mvp.entity;

import java.io.Serializable;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * <li>时间：17/2/20 15:27 </li>
 * </ul>
 */
public class Protocol implements Serializable {

    String customerProtocolUrl;//用户协议地址
    String ticketUrl;//票务地址
    String peccancyUrl;//违章地址
    String paymentUrl;//缴费地址
    String publicAccumulationFundsUrl;//公积金查询
    String scenicTicketUrl;//门票地址
    String hotelUrl;//酒店地址
    String wisdomCommunityUrl;//智慧社区地址
    String governmentAnnouncement;//政府公告

    public String getGovernmentAnnouncement() {
        return governmentAnnouncement;
    }

    public void setGovernmentAnnouncement(String governmentAnnouncement) {
        this.governmentAnnouncement = governmentAnnouncement;
    }

    public String getScenicTicketUrl() {
        return scenicTicketUrl;
    }

    public void setScenicTicketUrl(String scenicTicketUrl) {
        this.scenicTicketUrl = scenicTicketUrl;
    }

    public String getHotelUrl() {
        return hotelUrl;
    }

    public void setHotelUrl(String hotelUrl) {
        this.hotelUrl = hotelUrl;
    }

    public String getWisdomCommunityUrl() {
        return wisdomCommunityUrl;
    }

    public void setWisdomCommunityUrl(String wisdomCommunityUrl) {
        this.wisdomCommunityUrl = wisdomCommunityUrl;
    }

    public String getTicketUrl() {
        return ticketUrl;
    }

    public void setTicketUrl(String ticketUrl) {
        this.ticketUrl = ticketUrl;
    }

    public String getPeccancyUrl() {
        return peccancyUrl;
    }

    public void setPeccancyUrl(String peccancyUrl) {
        this.peccancyUrl = peccancyUrl;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }

    public String getPublicAccumulationFundsUrl() {
        return publicAccumulationFundsUrl;
    }

    public void setPublicAccumulationFundsUrl(String publicAccumulationFundsUrl) {
        this.publicAccumulationFundsUrl = publicAccumulationFundsUrl;
    }

    public String getCustomerProtocolUrl() {
        return customerProtocolUrl;
    }

    public void setCustomerProtocolUrl(String customerProtocolUrl) {
        this.customerProtocolUrl = customerProtocolUrl;
    }
}
