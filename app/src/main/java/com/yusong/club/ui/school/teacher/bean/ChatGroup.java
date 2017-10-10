package com.yusong.club.ui.school.teacher.bean;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/7.
 */

public class ChatGroup {
private List<CreatedBean>createdBeanList;
    private List<JoinedBean>joinedBeanList;

    public List<CreatedBean> getCreatedBeanList() {
        return createdBeanList;
    }

    public void setCreatedBeanList(List<CreatedBean> createdBeanList) {
        this.createdBeanList = createdBeanList;
    }

    public List<JoinedBean> getJoinedBeanList() {
        return joinedBeanList;
    }

    public void setJoinedBeanList(List<JoinedBean> joinedBeanList) {
        this.joinedBeanList = joinedBeanList;
    }

    public class CreatedBean{
   private String groupName;// string 名称,
   private int memberCount;// int 成员数量

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }
}
    public class JoinedBean{
        private String groupName;// string 名称,
        private int memberCount;// int 成员数量

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public int getMemberCount() {
            return memberCount;
        }

        public void setMemberCount(int memberCount) {
            this.memberCount = memberCount;
        }
    }

}
