package com.yusong.community.ui.shoppers.bean;

/**
 * 商圈类
 */
public class Business {
    public String name;
    public String price;
    public int imageInt;
    private String talk;
    private String intro;

    public Business(String name, String price, int imageInt, String intro,String talk) {
        this.name = name;
        this.price = price;
        this.imageInt = imageInt;
        this.talk = talk;
        this.intro = intro;
    }

    public String getTalk() {
        return talk;
    }

    public void setTalk(String talk) {
        this.talk = talk;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImageInt() {
        return imageInt;
    }

    public void setImageInt(int imageInt) {
        this.imageInt = imageInt;
    }
}
