package com.yusong.community.ui.school.school.bean;

/**
 * 消息类
 */
public class Messages {
    private String title;
    private String time;
    private String msgContent;
    private String count;

    public Messages(String title, String time, String msgContent, String count) {
        this.title = title;
        this.time = time;
        this.msgContent = msgContent;
        this.count = count;
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

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
