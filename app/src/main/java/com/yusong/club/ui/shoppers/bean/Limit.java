package com.yusong.club.ui.shoppers.bean;

/**
 * Created by admin on 2017/1/17.
 */
public class Limit {
    private int imgInt;
    private String oldPrice;
    private String newPrice;
    private String title;
    private String tip;
    private String count;
    private String intro;
    private String talk;

    public Limit(int imgInt, String oldPrice, String newPrice, String title, String tip, String count, String intro, String talk) {
        this.imgInt = imgInt;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.title = title;
        this.tip = tip;
        this.count = count;
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

    public int getImgInt() {
        return imgInt;
    }

    public void setImgInt(int imgInt) {
        this.imgInt = imgInt;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
