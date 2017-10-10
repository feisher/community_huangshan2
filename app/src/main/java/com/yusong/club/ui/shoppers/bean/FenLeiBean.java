package com.yusong.club.ui.shoppers.bean;

import java.io.Serializable;

/**
 * Created by Mr_Peng on 2017/2/28.
 */

public class FenLeiBean implements Serializable {
    private int id;
    private String categoryName;

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
}
