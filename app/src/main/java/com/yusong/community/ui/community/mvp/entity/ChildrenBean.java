package com.yusong.community.ui.community.mvp.entity;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/13.
 */

public class ChildrenBean {
    private String iconPath;
    private String orgName;
    private String tel;
    private List<ChildrenBean> childrenBeen;

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public List<ChildrenBean> getChildrenBeen() {
        return childrenBeen;
    }

    public void setChildrenBeen(List<ChildrenBean> childrenBeen) {
        this.childrenBeen = childrenBeen;
    }
}
