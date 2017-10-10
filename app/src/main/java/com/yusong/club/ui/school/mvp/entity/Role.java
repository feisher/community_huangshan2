package com.yusong.club.ui.school.mvp.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by feisher on 2017/2/28.
 */

public class Role implements Serializable {

    /**
     * auditMemo : 申请不通过原因
     * auditStatus : 1
     * roleList : [{"guardianList":[{"id":18,"name":"家长"}],"id":1,"managerList":[{"id":12,"name":"管理员"}],"schoolName":"实验1中","teacherList":[{"id":14,"name":"班主任"}]}]
     */

    @SerializedName("auditTime")
    private String auditTime;
    @SerializedName("auditMemo")
    private String auditMemo;
    @SerializedName("auditStatus")
    private int auditStatus;
    @SerializedName("applyId")
    private int applyId;

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    /**
     * guardianList : [{"id":18,"name":"家长"}]
     * id : 1
     * managerList : [{"id":12,"name":"管理员"}]
     * schoolName : 实验1中
     * teacherList : [{"id":14,"name":"班主任"}]
     */

    @SerializedName("roleList")
    private List<RoleListBean> roleList;

    public String getAuditMemo() {
        return auditMemo;
    }

    public void setAuditMemo(String auditMemo) {
        this.auditMemo = auditMemo;
    }

    public int getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(int auditStatus) {
        this.auditStatus = auditStatus;
    }

    public List<RoleListBean> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleListBean> roleList) {
        this.roleList = roleList;
    }

    public static class RoleListBean implements Serializable{
        @SerializedName("id")
        private int id;
        @SerializedName("schoolName")
        private String schoolName;
        /**
         * id : 18
         * name : 家长
         */

        @SerializedName("guardianList")
        private List<GuardianListBean> guardianList;
        /**
         * id : 12
         * name : 管理员
         */

        @SerializedName("managerList")
        private List<ManagerListBean> managerList;
        /**
         * id : 14
         * name : 班主任
         */

        @SerializedName("teacherList")
        private List<TeacherListBean> teacherList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }

        public List<GuardianListBean> getGuardianList() {
            return guardianList;
        }

        public void setGuardianList(List<GuardianListBean> guardianList) {
            this.guardianList = guardianList;
        }

        public List<ManagerListBean> getManagerList() {
            return managerList;
        }

        public void setManagerList(List<ManagerListBean> managerList) {
            this.managerList = managerList;
        }

        public List<TeacherListBean> getTeacherList() {
            return teacherList;
        }

        public void setTeacherList(List<TeacherListBean> teacherList) {
            this.teacherList = teacherList;
        }

        public static class GuardianListBean implements Serializable{
            @SerializedName("id")
            private int id;
            @SerializedName("name")
            private String name;
            @SerializedName("clazzName")
            private String clazzName;

            public String getClazzName() {
                return clazzName;
            }

            public void setClazzName(String clazzName) {
                this.clazzName = clazzName;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class ManagerListBean implements Serializable{
            @SerializedName("id")
            private int id;
            @SerializedName("name")
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class TeacherListBean implements Serializable{
            @SerializedName("id")
            private int id;
            @SerializedName("name")
            private String name;
            @SerializedName("clazzName")
            private String clazzName;

            public String getClazzName() {
                return clazzName;
            }

            public void setClazzName(String clazzName) {
                this.clazzName = clazzName;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
