package com.yusong.community.ui.school.school.bean;

import java.io.Serializable;

/**
 * Created by ruanjian on 2017/3/7.
 */

public class HuoType implements Serializable{
 private int   id;//: int 名称,
 private String   categoryName;//: string 活动名称

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
