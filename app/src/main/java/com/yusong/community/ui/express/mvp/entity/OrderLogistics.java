package com.yusong.community.ui.express.mvp.entity;

import java.util.List;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class OrderLogistics {

    String EBusinessID;
    String ShipperCode;
    boolean Success;
    String Reason;
    String LogisticCode;
    String State;
    public List<LogisticsInfo> Traces;

    public List<LogisticsInfo> getTraces() {
        return Traces;
    }

    public void setTraces(List<LogisticsInfo> traces) {
        Traces = traces;
    }

    public String getEBusinessID() {
        return EBusinessID;
    }

    public void setEBusinessID(String EBusinessID) {
        this.EBusinessID = EBusinessID;
    }

    public String getShipperCode() {
        return ShipperCode;
    }

    public void setShipperCode(String shipperCode) {
        ShipperCode = shipperCode;
    }

    public String getLogisticCode() {
        return LogisticCode;
    }

    public void setLogisticCode(String logisticCode) {
        LogisticCode = logisticCode;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }
    public class LogisticsInfo{
        String AcceptTime;
        String AcceptStation;
        String Remark;

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String remark) {
            Remark = remark;
        }

        public String getAcceptTime() {
            return AcceptTime;
        }

        public void setAcceptTime(String acceptTime) {
            AcceptTime = acceptTime;
        }

        public String getAcceptStation() {
            return AcceptStation;
        }

        public void setAcceptStation(String acceptStation) {
            AcceptStation = acceptStation;
        }

        @Override
        public String toString() {
            return "LogisticsInfo{" +
                    "AcceptTime='" + AcceptTime + '\'' +
                    ", AcceptStation='" + AcceptStation + '\'' +
                    ", Remark='" + Remark + '\'' +
                    '}';
        }
    }
}
