package com.hyphenate.easeui.model;

import java.io.Serializable;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * <li>时间：2017/4/27 11:22 </li>
 * </ul>
 */

public class Notify implements Serializable {
    /**
     * sourceType : 10
     * title : 订单将过期
     * eventTime : 2017-06-05 08:41:01
     * ext : {}
     * content : 订单IT2017060500000000000000000072需要付款61.00元。
     */
    private boolean flag = false;
    private int sourceType;
    private String title;
    private String eventTime;
    private ExtBean ext;
    private String content;

    public Notify(boolean flag, int sourceType, String title, String eventTime, ExtBean ext, String content) {
        this.flag = flag;
        this.sourceType = sourceType;
        this.title = title;
        this.eventTime = eventTime;
        this.ext = ext;
        this.content = content;
    }

    public int getSourceType() {
        return sourceType;
    }

    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public ExtBean getExt() {
        return ext;
    }

    public void setExt(ExtBean ext) {
        this.ext = ext;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public static class ExtBean {
    }

    @Override
    public String toString() {
        return "Notify{" +
                "ext=" + ext +
                ", title='" + title + '\'' +
                ", type=" + sourceType +
                ", content='" + content + '\'' +
                ", flag=" + flag +
                ", eventTime='" + eventTime + '\'' +
                '}';
    }

//    private boolean flag = false;
//    private String ext;
//    private String title;
//    private int sourceType;
//    private String content;
//    private String eventTime;
//
//    public Notify(String ext, String title, int sourceType, String content, boolean flag, String eventTime) {
//        this.ext = ext;
//        this.title = title;
//        this.sourceType = sourceType;
//        this.content = content;
//        this.flag = flag;
//        this.eventTime = eventTime;
//    }
//
//    public String getExt() {
//        return ext;
//    }
//
//    public void setExt(String ext) {
//        this.ext = ext;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public int getType() {
//        return sourceType;
//    }
//
//    public void setType(int sourceType) {
//        this.sourceType = sourceType;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public boolean isFlag() {
//        return flag;
//    }
//
//    public void setFlag(boolean flag) {
//        this.flag = flag;
//    }
//
//    public String getEventTime() {
//        return eventTime;
//    }
//
//    public void setEventTime(String eventTime) {
//        this.eventTime = eventTime;
//    }
//
//    @Override
//    public String toString() {
//        return "Notify{" +
//                "ext=" + ext +
//                ", title='" + title + '\'' +
//                ", type=" + sourceType +
//                ", content='" + content + '\'' +
//                ", flag=" + flag +
//                ", eventTime='" + eventTime + '\'' +
//                '}';
//    }
}
