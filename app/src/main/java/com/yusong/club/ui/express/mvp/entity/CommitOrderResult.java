package com.yusong.club.ui.express.mvp.entity;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class CommitOrderResult {

    String id;
    String putAuthCode;
    int charge;

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthCode() {
        return putAuthCode;
    }

    public void setAuthCode(String authCode) {
        this.putAuthCode = authCode;
    }
}
