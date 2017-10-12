package com.yusong.community.ui.community.mvp.entity;

import java.io.Serializable;

/**
 * Created by feisher on 2017/2/14.
 */

public class SetCommunity implements Serializable{

    /**
     * communityId : 3
     * communityName : 宇松科技
     */

    private int communityId;
    private String communityName;

    public int getCommunityId() {
        return communityId;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    @Override
    public String toString() {
        return "SetCommunity{" +
                "communityId=" + communityId +
                ", communityName='" + communityName + '\'' +
                '}';
    }
}
