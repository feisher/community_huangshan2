package com.yusong.club.ui.shoppers.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr_Peng on 2017/2/28.
 * 推荐商品
 */

public class TuiJianBean implements Serializable {
    private int id;
    private String categoryName;
    private String introduction;
    private String iconPath;
    private List<Commodity> itemList = new ArrayList<Commodity>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public List<Commodity> getItemList() {
        return itemList;
    }

    public void setItemList(List<Commodity> itemList) {
        this.itemList = itemList;
    }

    public class Commodity implements Serializable {
        private int id;
        private String itemName;
        private int shopId;
        private String introduction;
        private String[] imageList;
        private float price;
        private float showPrice;
        private int soldAmount;

        public int getSoldAmount() {
            return soldAmount;
        }

        public void setSoldAmount(int soldAmount) {
            this.soldAmount = soldAmount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String[] getImageList() {
            return imageList;
        }

        public void setImageList(String[] imageList) {
            this.imageList = imageList;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public float getShowPrice() {
            return showPrice;
        }

        public void setShowPrice(float showPrice) {
            this.showPrice = showPrice;
        }
    }
}
