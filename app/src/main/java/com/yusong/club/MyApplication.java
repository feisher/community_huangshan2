package com.yusong.club;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Environment;
import android.support.multidex.MultiDexApplication;

import com.baidu.mapapi.SDKInitializer;
import com.mob.MobSDK;
import com.qihoo360.replugin.RePlugin;
import com.yusong.club.map.LocationService;
import com.yusong.club.ui.im.IMHelper;
import com.yusong.club.utils.GreenDaoManager;
import com.yusong.club.utils.LogUtils;
import com.yusong.club.utils.ToastUtils;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;


public class MyApplication extends MultiDexApplication {

    public LocationService locationService;
    private static Context mContext;
    private static ProgressDialog mProgressDialog;
    private static MyApplication mInstance;
    public static List<WeakReference<Activity>> activityList = new LinkedList<WeakReference<Activity>>();


    @Override
    public void onCreate() {
        super.onCreate();
        if (mInstance == null) {
            mInstance = this;
        }
        if (BuildConfig.Server_Url_Index == 1) {
            LogUtils.isLog = false;
        }
        if (RePlugin.getPluginContext() != null) {
            mContext = RePlugin.getPluginContext();
        }else {
            mContext = getApplicationContext();
        }
        GreenDaoManager.getInstance();
        InitializeService.start(this);
        SDKInitializer.initialize(this);//百度地图
        locationService = new LocationService(mContext);
        MobSDK.init(mContext, "21238236eeb10", "f5075c8476ef2e8184b6e0d2909e2a5e");
        IMHelper.getInstance().init(mContext);
    }

    /**
     * 加载中
     */
    public static void showProgressDialog(Context activity) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(activity);
            mProgressDialog.setMessage("请稍候...");
            mProgressDialog.setOnCancelListener(null);
            mProgressDialog.show();
        } else if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    /**
     * 加载结束
     */
    public static void closeProgressDialog() {
        if (mProgressDialog != null
                && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }


    // 添加Activity到容器中
    public static void addActivity(WeakReference<Activity> activity) {
        activityList.add(activity);
    }

    // 删除Activity
    public static void removeActivity(WeakReference<Activity> activity) {
        activityList.remove(activity);
    }


    // 遍历所有Activity并finish
    public static void exit() {
        if (activityList != null && activityList.size() > 0) {
            for (WeakReference<Activity> activity : activityList) {
                if (activity.get() != null && !activity.get().isFinishing()) {
                    activity.get().finish();
                }
            }
        }
    }

    public static void showMessage(String message) {
        ToastUtils.showShort(getContext(), message);
    }

    public static MyApplication getInstance() {
        return mInstance;
    }

    @Override
    public File getCacheDir() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File cacheDir = getExternalCacheDir();
            if (cacheDir != null && (cacheDir.exists() || cacheDir.mkdirs())) {
                return cacheDir;
            }
        }
        return super.getCacheDir();
    }

    public static Context getContext() {
        return mContext;
    }

}
