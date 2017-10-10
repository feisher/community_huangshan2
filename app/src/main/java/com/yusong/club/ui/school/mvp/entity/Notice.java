package com.yusong.club.ui.school.mvp.entity;

import java.util.List;

/**
 * Created by feisher on 2017/2/28.
 */

public class Notice {

    /**
     * content : neirong
     * createTime : 2017-01-01 00:00:00
     * id : 1
     * imageList : ["http://www.afdaf.com/1.jpg","http://www.afdaf.com/1.jpg"]
     * noticeType : 2
     * publishRange : 2
     * readCount : 80
     * title : 标题
     */

    private String content;
    private String createTime;
    private int id;
    private int noticeType;
    private int publishRange;
    private int readCount;
    private String title;
    private List<String> imageList;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(int noticeType) {
        this.noticeType = noticeType;
    }

    public int getPublishRange() {
        return publishRange;
    }

    public void setPublishRange(int publishRange) {
        this.publishRange = publishRange;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }
}
