package com.yusong.club.ui.community_notice.entity;

import java.io.Serializable;

/**
 * @author Mr_Peng
 * @created at 2017-09-06.
 * @describe: null
 */

public class NoticeBean implements Serializable{
    private int id;
    private String title;
    private String content;
    private String createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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
}
