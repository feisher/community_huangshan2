package com.yusong.club.utils;

import com.yusong.club.BuildConfig;

/**
 * Created by quaner on 16/12/30.
 */
public class Constants {
    //    public static String YS_RELEASE = "https://114.215.192.89:8080"; //正式
//    public static String YS_DEBUG1 = "https://122.224.164.50:9080"; //内网
//    public static String YS_RELEASE = "https://122.224.164.50:9080"; //正式
public static String YS_DEBUG1 = "http://106.14.221.106:8080"; //内网
    public static String YS_RELEASE = "http://106.14.221.106:8080"; //正式
//    public static String YS_DEBUG1 = "http://192.9.198.181:8090"; //内网
//    public static String YS_RELEASE = "http://192.9.198.181:8090"; //正式
    public static String KDG_URL = "http://api.kdniao.cc";
    public static String LOCAL_URL = "http://192.9.196.152:8080";
//    public static final int AGENTID = 1;//运营商id
    public static final int AGENTID = 11;//运营商id  宇松社区标准版
    public static final String VIDEO_URL = "VIDEO_URL";
    private static final String[] Server_Urls = new String[]{
            YS_DEBUG1, YS_RELEASE
    };
    public static int Server_Url_Index = BuildConfig.Server_Url_Index;

    public static String getNetUrl() {
        return Server_Urls[Server_Url_Index];
    }

}
