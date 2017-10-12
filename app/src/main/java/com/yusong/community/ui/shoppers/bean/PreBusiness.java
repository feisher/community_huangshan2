package com.yusong.community.ui.shoppers.bean;

/**
 * 特惠商品
 */
public class PreBusiness {
    private String name;
    private int imageInt;
    private String newPrice;
    private String oldPrice;
    private String intro;
    private String talk;

    public PreBusiness(String name, int imageInt, String newPrice, String oldPrice, String intro, String talk) {
        this.name = name;
        this.imageInt = imageInt;
        this.newPrice = newPrice;
        this.oldPrice = oldPrice;
        this.intro = intro;
        this.talk = talk;
    }


    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getTalk() {
        return talk;
    }

    public void setTalk(String talk) {
        this.talk = talk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageInt() {
        return imageInt;
    }

    public void setImageInt(int imageInt) {
        this.imageInt = imageInt;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }
}
