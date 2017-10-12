package com.yusong.community.ui.me.mvp.entity;

/**
 * Created by feisher on 2017/1/17.
 */

public class MyOrderFunModuleDatas {
    int imageId;
    String name;

    public MyOrderFunModuleDatas(int imageId, String name) {
        this.imageId = imageId;
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyOrderFunModuleDatas{" +
                "imageId=" + imageId +
                ", name='" + name + '\'' +
                '}';
    }
}
