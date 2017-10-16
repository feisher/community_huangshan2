package com.yusong.community.map;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.baidu.location.BDLocation;
import com.yusong.community.MyApplication;
import com.yusong.community.utils.ToastUtils;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr_Peng on 2017/2/25.
 * 导航
 */

public class NavigationUtil {
    private String BAIDU_MAP_PAK_NAME = "com.baidu.BaiduMap";
    private String BAIDU_MAP_DOWNLOAD = "http://shouji.baidu.com/software/10028007.html";
    private Context context;
    private double beginLng;
    private double beginLat;
    private double endlng;
    private double endLat;

    public NavigationUtil(Context context, double endlng, double endLat) {
        this.context = context;
        this.endlng = endlng;
        this.endLat = endLat;
        initNavi();
    }

    public NavigationUtil(Context context, double beginLng, double beginLat, double endlng, double endLat) {
        this.context = context;
        this.beginLng = beginLng;
        this.beginLat = beginLat;
        this.endlng = endlng;
        this.endLat = endLat;
        initNavi();
    }

    private void initNavi() {
        if (isAvilible(MyApplication.getContext(), BAIDU_MAP_PAK_NAME)) {
            startNavagation();
        } else {
            ToastUtils.showShort(context, "请下载百度地图，再启动导航");
            jumpDownload();
        }
    }

    /**
     * 定位当前位置
     */
    public static String getAdress() {
        BDLocation mLocation = LocationService.mLocation;
        return mLocation.getAddress().city + mLocation.getAddress().district;

    }

    /**
     * 跳转百度app导航
     */
    private void startNavagation() {
        Intent intent = null;
        BDLocation mLocation = LocationService.mLocation;
        try {// 如果有安装百度地图 就启动百度地图
            StringBuffer sbs = new StringBuffer();
            sbs.append("intent://map/direction?origin=latlng:")
                    // 我的位置
                    .append(mLocation.getLatitude())
                    .append(",")
                    .append(mLocation.getLongitude())
                    .append("|name:")
                    .append(mLocation.getAddress())
                    // 去的位置
                    .append("&destination=latlng:")
                    .append(endLat) // 经度
                    .append(",")
                    .append(endlng)// 纬度
                    .append("|name:")
                    .append("")
                    // 城市
                    .append("&mode=driving®ion=")
                    .append(mLocation.getCity())
                    .append("&referer=com.menu|menu#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
            try {
                intent = Intent.getIntent(sbs.toString());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            context.startActivity(intent);
        } catch (Exception e) {
            ToastUtils.showShort(context, "导航异常");
        }
    }

    /**
     * 提示下载
     */
    private void jumpDownload() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(BAIDU_MAP_DOWNLOAD));
        context.startActivity(intent);
    }

    /**
     * 检查手机上是否安装了指定的软件
     */
    public boolean isAvilible(Context context, String packageName) {
        // 获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);

        packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        // 用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        // 从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        // 判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }
}
