package com.yusong.club.ui.express.mvp.entity;

import java.util.List;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class ScanOrder {
//
//    {
//        "EBusinessID": "1272333",
//            "Success": true,
//            "LogisticCode": "3967950525457",
//            "Shippers": [
//        {
//            "ShipperCode": "YD",
//                "ShipperName": "韵达递"
//        }
//        ]
//    }
    String EBusinessID;
    boolean Success;
    String LogisticCode;
    List<ShipperInfo> Shippers;

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    String Reason;

    public String getEBusinessID() {
        return EBusinessID;
    }

    public void setEBusinessID(String EBusinessID) {
        this.EBusinessID = EBusinessID;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public String getLogisticCode() {
        return LogisticCode;
    }

    public void setLogisticCode(String logisticCode) {
        LogisticCode = logisticCode;
    }

    public List<ShipperInfo> getShippers() {
        return Shippers;
    }

    public void setShippers(List<ShipperInfo> shippers) {
        Shippers = shippers;
    }


    public class ShipperInfo{
        String ShipperCode;
        String ShipperName;

        public String getShipperCode() {
            return ShipperCode;
        }

        public void setShipperCode(String shipperCode) {
            ShipperCode = shipperCode;
        }

        public String getShipperName() {
            return ShipperName;
        }

        public void setShipperName(String shipperName) {
            ShipperName = shipperName;
        }
    }
}
