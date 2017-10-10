package com.yusong.club.ui.me.mvp.entity;

/**
 * Created by feisher on 2017/1/17.
 */

public class MyToolsFunModuleDatas {
    int imageId;
    String name;

    public MyToolsFunModuleDatas(int imageId, String name) {
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
