package com.hyphenate.easeui.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

@Entity
public class DataBean implements Serializable {
    /**
     * ext : null
     * title : 推送消息
     * type : 1
     * content : 你好啊，
     */
    @Id(autoincrement = true)
    private Long id;
    private String ext;
    private String title;
    private int type;
    private String content;
    private boolean flag = false;
    private String eventTime;
    private String userName;


    @Generated(hash = 70288782)
    public DataBean(Long id, String ext, String title, int type, String content,
                    boolean flag, String eventTime, String userName) {
        this.id = id;
        this.ext = ext;
        this.title = title;
        this.type = type;
        this.content = content;
        this.flag = flag;
        this.eventTime = eventTime;
        this.userName = userName;
    }

    @Generated(hash = 908697775)
    public DataBean() {
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "ext=" + ext +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", content='" + content + '\'' +
                '}';
    }

    public boolean getFlag() {
        return this.flag;
    }
}