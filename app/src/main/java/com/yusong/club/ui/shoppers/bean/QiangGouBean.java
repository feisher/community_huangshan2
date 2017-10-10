package com.yusong.club.ui.shoppers.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


 /**
  * @author Mr_Peng
  * created at 2017/3/3 13:47.
  */
public class QiangGouBean implements Serializable {
    private int id;
    private String categoryName;
    private String introduction;
    private String iconPath;
    private List<Categorys> categoryList = new ArrayList<Categorys>();

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

    public List<Categorys> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Categorys> categoryList) {
        this.categoryList = categoryList;
    }

    public class Categorys {
        private int id;
        private String categoryName;
        private String introduction;
        private String iconPath;

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
    }
}
