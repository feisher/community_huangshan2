package com.yusong.community.ui.school.school.bean;

/**
 * Created by ruanjian on 2017/3/7.
 */

public class Comment {
    /**
     * passport : {"portrait":"/static/basic/customer_portrait/2017-03-10/464e60d9ae80fa4df9ab22eaa86ae1bc5f7d.jpeg","username":"18457148548"}
     * createTime : 2017-03-16 16:04:03
     * content : 考虑考虑
     */

    private PassportBean passport;
    private String createTime;
    private String content;

    public PassportBean getPassport() {
        return passport;
    }

    public void setPassport(PassportBean passport) {
        this.passport = passport;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static class PassportBean {
        /**
         * portrait : /static/basic/customer_portrait/2017-03-10/464e60d9ae80fa4df9ab22eaa86ae1bc5f7d.jpeg
         * username : 18457148548
         */

        private String portrait;
        private String username;

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
   /* private String   content;//: string 评论内容
    private PassportBean passportBean;
    private String    createTime;//: string 创建日期
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PassportBean getPassportBean() {
        return passportBean;
    }

    public void setPassportBean(PassportBean passportBean) {
        this.passportBean = passportBean;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public class PassportBean{
        private String  portrait;//：string 头像
        private String username;//: string 用户名称
        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }*/



}
