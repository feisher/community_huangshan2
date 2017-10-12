package com.yusong.community.ui.express.event;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * <li>时间：17/2/13 15:13 </li>
 * </ul>
 */
public class Navigation {

    private final double lat;
    private final double lng;

    public Navigation(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLng() {
        return lng;
    }

    public double getLat() {
        return lat;
    }

}
