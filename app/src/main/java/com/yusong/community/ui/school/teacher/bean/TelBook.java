package com.yusong.community.ui.school.teacher.bean;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/7.
 */

public class TelBook {
    private List<StudentListBean> studentList;
    private List<TeacherListBean> teacherList;

    public List<StudentListBean> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<StudentListBean> studentList) {
        this.studentList = studentList;
    }

    public List<TeacherListBean> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<TeacherListBean> teacherList) {
        this.teacherList = teacherList;
    }

    public static class StudentListBean {
        private String studentName;//：string 学生姓名
        private int roleId;
        private String imAccountId;

        public String getImAccountId() {
            return imAccountId;
        }

        public void setImAccountId(String imAccountId) {
            this.imAccountId = imAccountId;
        }

        public int getRoleId() {
            return roleId;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }
    }

    public static class TeacherListBean {
        /**
         * courseName : 绘画,语文,物理
         * isDuty : 1
         * teacherName : 看见了
         * isManager : 0
         * portrait : http://122.224.164.50:8100//static/basic/customer_portrait/2017-03-10/464e60d9ae80fa4df9ab22eaa86ae1bc5f7d.jpeg
         */
        private int roleId;
        private String courseName;
        private int isDuty;
        private String teacherName;
        private int isManager;
        private String portrait;
        private String imAccountId;

        public String getImAccountId() {
            return imAccountId;
        }

        public void setImAccountId(String imAccountId) {
            this.imAccountId = imAccountId;
        }

        public int getRoleId() {
            return roleId;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public int getIsDuty() {
            return isDuty;
        }

        public void setIsDuty(int isDuty) {
            this.isDuty = isDuty;
        }

        public String getTeacherName() {
            return teacherName;
        }

        public void setTeacherName(String teacherName) {
            this.teacherName = teacherName;
        }

        public int getIsManager() {
            return isManager;
        }

        public void setIsManager(int isManager) {
            this.isManager = isManager;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }
    }
}
