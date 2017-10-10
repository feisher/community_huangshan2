package com.yusong.club.ui.school.teacher.bean;

/**
 * Created by ruanjian on 2017/3/7.
 */

public class MemberGroup {
    private int memberName;
    private PassportBean passportBean;

    public int getMemberName() {
        return memberName;
    }

    public void setMemberName(int memberName) {
        this.memberName = memberName;
    }

    public PassportBean getPassportBean() {
        return passportBean;
    }

    public void setPassportBean(PassportBean passportBean) {
        this.passportBean = passportBean;
    }

    public class PassportBean {
        private int id;//: int 成员id
        private String nickname;//: string 昵称
        private String realName;//: string 姓名
        private String portrait;//: string 头像 http://www.asdfasf.com/xxx.jpg

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }
    }



}
