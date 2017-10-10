package com.yusong.club.ui.community.mvp.entity;

import java.io.Serializable;

/**
 * Created by feisher on 2017/2/17.
 */

public class PostComment implements Serializable{

    /**
     * content : 评论内容
     * createTime : 2015-01-01 01:01:01
     * customer : {"gender":1,"id":5,"portrait":"string 头像","realName":"string"}
     * id : 2
     * thankCount : 10
     */

    private String content;
    private String createTime;
    /**
     * gender : 1
     * id : 5
     * portrait : string 头像
     * realName : string
     */

    private CustomerBean customer;
    private int id;
    private int thankCount;

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

    public CustomerBean getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerBean customer) {
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getThankCount() {
        return thankCount;
    }

    public void setThankCount(int thankCount) {
        this.thankCount = thankCount;
    }

    public static class CustomerBean implements Serializable{
        private int gender;
        private int id;
        private String portrait;
        private String realName;

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }
    }

    @Override
    public String toString() {
        return "PostComment{" +
                "content='" + content + '\'' +
                ", createTime='" + createTime + '\'' +
                ", customer=" + customer +
                ", id=" + id +
                ", thankCount=" + thankCount +
                '}';
    }
}
