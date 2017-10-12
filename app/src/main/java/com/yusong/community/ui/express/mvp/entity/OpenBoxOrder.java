package com.yusong.community.ui.express.mvp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class OpenBoxOrder implements Parcelable {

    List<Order> sendOrderList;
    List<Order> deliverOrderList;
    List<Order> storeOrderList;

    public List<Order> getSendOrderList() {
        return sendOrderList;
    }

    public void setSendOrderList(List<Order> sendOrderList) {
        this.sendOrderList = sendOrderList;
    }

    public List<Order> getDeliverOrderList() {
        return deliverOrderList;
    }

    public void setDeliverOrderList(List<Order> deliverOrderList) {
        this.deliverOrderList = deliverOrderList;
    }

    public List<Order> getStoreOrderList() {
        return storeOrderList;
    }

    public void setStoreOrderList(List<Order> storeOrderList) {
        this.storeOrderList = storeOrderList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.sendOrderList);
        dest.writeList(this.deliverOrderList);
        dest.writeList(this.storeOrderList);
    }

    public OpenBoxOrder() {
    }

    protected OpenBoxOrder(Parcel in) {
        this.sendOrderList = new ArrayList<Order>();
        in.readList(this.sendOrderList, Order.class.getClassLoader());
        this.deliverOrderList = new ArrayList<Order>();
        in.readList(this.deliverOrderList, Order.class.getClassLoader());
        this.storeOrderList = new ArrayList<Order>();
        in.readList(this.storeOrderList, Order.class.getClassLoader());
    }

    public static final Parcelable.Creator<OpenBoxOrder> CREATOR = new Parcelable.Creator<OpenBoxOrder>() {
        @Override
        public OpenBoxOrder createFromParcel(Parcel source) {
            return new OpenBoxOrder(source);
        }

        @Override
        public OpenBoxOrder[] newArray(int size) {
            return new OpenBoxOrder[size];
        }
    };
}
