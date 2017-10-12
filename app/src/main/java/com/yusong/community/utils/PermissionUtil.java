package com.yusong.community.utils;

import android.Manifest;
import android.app.Activity;

import com.yanzhenjie.permission.AndPermission;


/**
 * Created by quaner on 16/12/24.
 *
 * 6.0动态权限工具类
 */
public class PermissionUtil {
    public static final int RESULT_CAPTURE_IMAGE = 100;// 照相的requestCode
    public static final int REQUEST_CODE_TAKE_VIDEO = 101;// 摄像的照相的requestCode
    public static final int RESULT_CAPTURE_RECORDER_SOUND = 102;// 录音的requestCode
    public static final int RESULT_LOCATION_CODE = 103;// 定位的requestCode
    private static Activity mActivity;

    /**
     * 获取定位权限
     *
     * @param activity
     * @param requestCode
     */
    public static void getLocationPermission(Activity activity, int requestCode) {
        AndPermission.with(activity)
                .requestCode(requestCode)
                .permission(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.CHANGE_WIFI_STATE,
                        Manifest.permission.ACCESS_WIFI_STATE)
                .send();
    }

    /**
     * 获取相机相册权限,摄像权限
     *
     * @param activity
     * @param requestCode
     */
    public static void getCameraAndPhotoPermission(Activity activity, int requestCode) {
        mActivity = activity;
        AndPermission.with(activity)
                .requestCode(requestCode)
                .permission(
                        //Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.READ_EXTERNAL_STORAGE,
//                        Manifest.permission.READ_PHONE_STATE,
//                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA)
                .send();


    }


    /**
     * 获取读写内存卡权限
     *
     * @param activity
     * @param requestCode
     */
    public static void getSDcard(Activity activity, int requestCode) {
        AndPermission.with(activity)
                .requestCode(requestCode)
                .permission(
                        Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .send();
    }


    /**
     * 获取录音权限
     *
     * @param activity
     * @param requestCode
     */
    public static void getSoundRecordingPermission(Activity activity, int requestCode) {
        AndPermission.with(activity)
                .requestCode(requestCode)
                .permission(
                        //  Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .send();
    }



}

