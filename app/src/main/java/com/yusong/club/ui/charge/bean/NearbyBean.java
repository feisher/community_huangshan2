package com.yusong.club.ui.charge.bean;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Mr_Peng on 2017/1/11.
 */

public class NearbyBean implements Serializable {
    String id;
    String chargerName;
    double distance;
    int freePoint;
    int busyPoint;
    double lng;
    double lat;
    String openTime;
    String typeName;
    String tel;
    String agentName;
    List<String> imageList;
    int status;

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChargerName() {
        return chargerName;
    }

    public void setChargerName(String chargerName) {
        this.chargerName = chargerName;
    }

    public double getDistance() {
        return new BigDecimal(distance / 1000).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getFreePoint() {
        return freePoint;
    }

    public void setFreePoint(int freePoint) {
        this.freePoint = freePoint;
    }

    public int getBusyPoint() {
        return busyPoint;
    }

    public void setBusyPoint(int busyPoint) {
        this.busyPoint = busyPoint;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

}
