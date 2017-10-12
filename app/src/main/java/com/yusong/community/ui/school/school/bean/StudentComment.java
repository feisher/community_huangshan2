package com.yusong.community.ui.school.school.bean;

/**
 * Created by ruanjian on 2017/3/7.
 */

public class StudentComment {
    /**
     * period : 1
     * student : {"portrait":"http://122.224.164.50:8100//static/basic/customer_portrait/2017-03-04/525bb691ae85ba48afaab09aba6eedf355fb.jpg","username":"18170580598"}
     * star1 : 3
     * createTime : 2017-03-20 00:00:00
     * content : aa
     */

    private int period;
    private StudentBean student;
    private int star1;
    private String createTime;
    private String content;

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public StudentBean getStudent() {
        return student;
    }

    public void setStudent(StudentBean student) {
        this.student = student;
    }

    public int getStar1() {
        return star1;
    }

    public void setStar1(int star1) {
        this.star1 = star1;
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

    public static class StudentBean {
        /**
         * portrait : http://122.224.164.50:8100//static/basic/customer_portrait/2017-03-04/525bb691ae85ba48afaab09aba6eedf355fb.jpg
         * username : 18170580598
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

//    private int period;// int 学期 1 2
//    private float star;//: float 星
//    private String content;//：string 评语
//    private String createTime;//: string 创建日期
//    private StudentBean studentBean;
//
//    public int getPeriod() {
//        return period;
//    }
//
//    public void setPeriod(int period) {
//        this.period = period;
//    }
//
//    public float getStar() {
//        return star;
//    }
//
//    public void setStar(float star) {
//        this.star = star;
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
//    public String getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(String createTime) {
//        this.createTime = createTime;
//    }
//
//    public StudentBean getStudentBean() {
//        return studentBean;
//    }
//
//    public void setStudentBean(StudentBean studentBean) {
//        this.studentBean = studentBean;
//    }
//
//    public class StudentBean {
//        private String portrait;//：string 头像
//        private String username;//: string 学生姓名
//
//        public String getPortrait() {
//            return portrait;
//        }
//
//        public void setPortrait(String portrait) {
//            this.portrait = portrait;
//        }
//
//        public String getUsername() {
//            return username;
//        }
//
//        public void setUsername(String username) {
//            this.username = username;
//        }
//    }
}
