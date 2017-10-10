package com.yusong.club.ui.express.mvp.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Order implements Parcelable {

    String id;
    String orderNo;
    String boxNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(String boxNum) {
        this.boxNum = boxNum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.orderNo);
        dest.writeString(this.boxNum);
    }

    public Order() {
    }

    protected Order(Parcel in) {
        this.id = in.readString();
        this.orderNo = in.readString();
        this.boxNum = in.readString();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel source) {
            return new Order(source);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };
}
