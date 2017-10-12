package com.yusong.community.ui.school.user;

import java.io.Serializable;

/**
 * create by feisher on 2017/3/10
 * Email：458079442@qq.com
 */
public class AuditStatusEvent implements Serializable{
//    auditMemo = data.getAuditMemo();
//    auditStatus = data.getAuditStatus();
//    auditTime = data.getAuditTime();
//    applyId = data.getApplyId();

    public int auditStatus;
    public String auditMemo;
    public String auditTime;
    public int applyId;
    public AuditStatusEvent(int auditStatus,int applyId,String auditMemo,String auditTime) {
        this.auditStatus = auditStatus;
        this.auditMemo = auditMemo;
        this.auditTime = auditTime;
        this.applyId = applyId;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

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
}
