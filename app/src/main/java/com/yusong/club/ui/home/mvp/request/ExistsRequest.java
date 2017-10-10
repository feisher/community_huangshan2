package com.yusong.club.ui.home.mvp.request;

/**
 * Created by quaner on 17/1/3.
 */

public class ExistsRequest {

    String mobile;
    int agentId;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }
}
