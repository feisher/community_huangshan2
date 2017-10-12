package com.yusong.community.ui.school.school.bean;

/**
 * 通知类
 */
public class Notice {
    private int imgStyle;//1 公告  2 通知
    private String title;
    private String time;
    private String noticeContent;
    private int count;

    public Notice(int imgStyle, String title, String time, String noticeContent, int count) {
        this.imgStyle = imgStyle;
        this.title = title;
        this.time = time;
        this.noticeContent = noticeContent;
        this.count = count;
    }

    public int getImgStyle() {
        return imgStyle;
    }

    public void setImgStyle(int imgStyle) {
        this.imgStyle = imgStyle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
