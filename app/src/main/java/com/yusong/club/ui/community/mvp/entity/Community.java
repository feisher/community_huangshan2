package com.yusong.club.ui.community.mvp.entity;

/**
 * Created by feisher on 2017/2/13.
 */

public class Community {

    /**
     * cityName : 杭州
     * communityName : 宇松科技
     * distance : 1515.23256
     * districtName : 余杭
     * id : 11
     * lat : 1.5555555
     * lng : 1.0333333
     * provinceName : 浙江
     * street : 良渚街道
     * tel : 15855555
     */

    private String cityName;
    private String communityName;
    private double distance;
    private String districtName;
    private int id;
    private double lat;
    private double lng;
    private String provinceName;
    private String street;
    private String tel;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
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

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
