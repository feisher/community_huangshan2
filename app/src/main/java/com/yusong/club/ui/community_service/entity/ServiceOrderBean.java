package com.yusong.club.ui.community_service.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author Mr_Peng
 * @created at 2017-09-23.
 * @describe: null
 */

public class ServiceOrderBean implements Serializable {

    /**
     * address : 浙江省西湖区杭州市宇松
     * payTime : 1506132111000
     * receiverMobile : 18170580598
     * orderStatus : 2
     * shopName : 爱家家政
     * serviceTime : 2017-09-23
     * shopMobile : 011-123456
     * orderStatusName : 已付款
     * itemName : 康师傅桶装水
     * itemImageList : ["http://192.9.198.181:8080/static/community/service_item/image1/32da6c5ba22a2a4428aa9eda9b9e1a79a4de.png","http://192.9.198.181:8080/static/community/service_item/image2/4c1dfbe2a1f7da4916abc26acdc3d71d7b9b.jpg","http://192.9.198.181:8080/static/community/service_item/image3/db93268da75a4a4a67aa5c4afb08d250fcd4.jpg"]
     * reciever : 米
     * createTime : 2017-09-23
     * price : 11
     * id : IT2017092300000000000000000245
     */

    private String address;
    private String payTime;
    private String receiverMobile;
    private int orderStatus;
    private String shopName;
    private String serviceTime;
    private String shopMobile;
    private String orderStatusName;
    private String itemName;
    private String reciever;
    private String createTime;
    private double price;
    private String id;
    private List<String> itemImageList;
    private int deliverType;

    public int getDeliverType() {
        return deliverType;
    }

    public void setDeliverType(int deliverType) {
        this.deliverType = deliverType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getShopMobile() {
        return shopMobile;
    }

    public void setShopMobile(String shopMobile) {
        this.shopMobile = shopMobile;
    }

    public String getOrderStatusName() {
        return orderStatusName;
    }

    public void setOrderStatusName(String orderStatusName) {
        this.orderStatusName = orderStatusName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getItemImageList() {
        return itemImageList;
    }

    public void setItemImageList(List<String> itemImageList) {
        this.itemImageList = itemImageList;
    }
}
