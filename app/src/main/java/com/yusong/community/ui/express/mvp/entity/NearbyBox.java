package com.yusong.community.ui.express.mvp.entity;

import java.io.Serializable;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * <li>时间：17/2/13 11:39 </li>
 * </ul>
 */
public class NearbyBox implements Serializable{
    /**
     * emptyCount : 1
     * terminalName : 222
     * lng : 120.02172088623047
     * distance : 648.0992797873998
     * imagePath1 :
     * street : 七贤桥
     * imagePath3 :
     * imagePath2 :
     * id : 4
     * lat : 30.353710174560547
     * fullCount : 0
     * status : 1
     */

    private int emptyCount;
    private String terminalName;
    private double lng;
    private double distance;
    private String imagePath1;
    private String street;
    private String imagePath3;
    private String imagePath2;
    private int id;
    private double lat;
    private int fullCount;
    private int status;

    public int getEmptyCount() {
        return emptyCount;
    }

    public void setEmptyCount(int emptyCount) {
        this.emptyCount = emptyCount;
    }

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getImagePath1() {
        return imagePath1;
    }

    public void setImagePath1(String imagePath1) {
        this.imagePath1 = imagePath1;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getImagePath3() {
        return imagePath3;
    }

    public void setImagePath3(String imagePath3) {
        this.imagePath3 = imagePath3;
    }

    public String getImagePath2() {
        return imagePath2;
    }

    public void setImagePath2(String imagePath2) {
        this.imagePath2 = imagePath2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public int getFullCount() {
        return fullCount;
    }

    public void setFullCount(int fullCount) {
        this.fullCount = fullCount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
