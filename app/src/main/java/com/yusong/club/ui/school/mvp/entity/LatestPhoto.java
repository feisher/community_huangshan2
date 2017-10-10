package com.yusong.club.ui.school.mvp.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ruanjian on 2017/3/29.
 */

public class LatestPhoto {
    private String createTime;//: string 创建时间
    private String address;//: string 位置
    private List<ImageListBean> imageList;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ImageListBean> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageListBean> imageList) {
        this.imageList = imageList;
    }

    public static class ImageListBean implements Serializable {

        private String imagePath;

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }
    }
}
