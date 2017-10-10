package com.yusong.club.utils;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.Nullable;

import com.yusong.club.ui.home.mvp.cache.TokenInfo;
import com.yusong.club.ui.me.mvp.entity.UserInfo;
import com.yusong.club.ui.visitor.entity.CommuntitySetingBean;
import com.yusong.club.ui.visitor.entity.OwnerInfo;

import java.io.File;
import java.math.BigDecimal;

public class CacheUtils {

    //保存社区设置
    public static void saveCommuntitySeting(Context context, CommuntitySetingBean info) {
        SPUtils.saveObject(context, "seting", info);
    }

    //获取社区设置
    public static CommuntitySetingBean getCommuntitySeting(Context context) {
        return (CommuntitySetingBean) SPUtils.readObject(context, "seting");
    }

    //获取物业电话
    public static String getTenementTel(Context context) {
        CommuntitySetingBean setingBean = (CommuntitySetingBean) SPUtils.readObject(context, "seting");
        if (setingBean != null) {
            return setingBean.getTel();
        }
        return null;
    }

    //获取服务时间
    public static String getServiceTime(Context context) {
        CommuntitySetingBean setingBean = (CommuntitySetingBean) SPUtils.readObject(context, "seting");
        if (setingBean != null) {
            return setingBean.getServiceTime();
        }
        return null;
    }

    /**
     * 业主信息存/取
     *
     * @param context
     * @param info
     */
    //保存业主信息
    public static void saveOwnerInfo(Context context, OwnerInfo info) {
        SPUtils.saveObject(context, "OwnerInfo", info);
    }

    //获取业主信息
    public static OwnerInfo getOwnerInfo(Context context) {
        return (OwnerInfo) SPUtils.readObject(context, "OwnerInfo");
    }

    //获取业主姓名
    public static String getProprietorName(Context context) {
        OwnerInfo ownerInfo = (OwnerInfo) SPUtils.readObject(context, "OwnerInfo");
        if (ownerInfo != null) {
            return ownerInfo.getProprietorName();
        }
        return null;
    }

    //获取社区名称
    public static String getCommunityName(Context context) {
        OwnerInfo ownerInfo = (OwnerInfo) SPUtils.readObject(context, "OwnerInfo");
        if (ownerInfo != null) {
            return ownerInfo.getCommunityName();
        }
        return null;
    }

    //获取社区id
    public static int getCommunityId(Context context) {
        OwnerInfo ownerInfo = (OwnerInfo) SPUtils.readObject(context, "OwnerInfo");
        if (ownerInfo != null) {
            return ownerInfo.getCommunityId();
        }
        return 0;
    }

    //获取业主id
    public static int getProprietorId(Context context) {
        OwnerInfo ownerInfo = (OwnerInfo) SPUtils.readObject(context, "OwnerInfo");
        if (ownerInfo != null) {
            return ownerInfo.getProprietorId();
        }
        return 0;
    }

    //获取业主手机
    public static String getMobile(Context context) {
        OwnerInfo ownerInfo = (OwnerInfo) SPUtils.readObject(context, "OwnerInfo");
        if (ownerInfo != null) {
            return ownerInfo.getMobile();
        }
        return null;
    }

    //获取业主地址
    public static String getAddress(Context context) {
        OwnerInfo ownerInfo = (OwnerInfo) SPUtils.readObject(context, "OwnerInfo");
        if (ownerInfo != null) {
            return ownerInfo.getAddress();
        }
        return null;
    }


    /**
     * 登录信息存/取
     *
     * @param context
     * @return
     */

    //保存TokenInfo
    public static void saveTokenInfo(Context context, TokenInfo info) {
        SPUtils.saveObject(context, "tokeninfo", info);
    }

    //获取TokenInfo
    public static TokenInfo getTokenInfo(Context context) {
        return (TokenInfo) SPUtils.readObject(context, "tokeninfo");
    }

    //获取Token
    @Nullable
    public static String getToken(Context context) {
        TokenInfo tokeninfo = (TokenInfo) SPUtils.readObject(context, "tokeninfo");
        if (tokeninfo != null) {
            return tokeninfo.getToken();
        }
        return null;
    }

    //获取用户Id
    public static int getId(Context context) {
        TokenInfo tokeninfo = (TokenInfo) SPUtils.readObject(context, "tokeninfo");
        if (tokeninfo != null) {
            return tokeninfo.getId();
        }
        return 0;
    }

    /**
     * 保存倒计时
     *
     * @param context
     * @param time
     */
    public static void savaCountdown(Context context, long time) {
        SPUtils.put(context, "time", time);
    }

    /**
     * 获取倒计时
     *
     * @param context
     */
    public static long getCountdown(Context context) {
        return (long) SPUtils.get(context, "time", 0L);
    }

    /**
     * 保存当前创建数量
     *
     * @param context
     */
    public static void savaCreateGroups(Context context, int groups) {
        SPUtils.put(context, "groups", groups);
    }

    /**
     * 获取当前创建数量
     *
     * @param context
     */
    public static int getCreateGroups(Context context) {
        int groups = (int) SPUtils.get(context, "groups", 0);
        return groups;
    }

    /**
     * 清除保存的账号信息退出登录
     *
     * @param context
     */
    public static void clearSP(Context context) {
        SPUtils.clear(context);
    }

    /**
     * 获取用户对象
     *
     * @param context
     * @return
     */
    public static UserInfo getUserInfo(Context context) {
        return (UserInfo) SPUtils.readObject(context, "UserInfo");
    }

    /**
     * 获取应用缓存大小
     *
     * @param context
     * @return
     * @throws Exception
     */
    public static String getTotalCacheSize(Context context) throws Exception {
        long cacheSize = getFolderSize(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheSize += getFolderSize(context.getExternalCacheDir());
        }
        return getFormatSize(cacheSize);
    }

    /**
     * 清除应用缓存
     *
     * @param context
     */
    public static void clearAllCache(Context context) {
        deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && context.getExternalCacheDir() != null) {
            deleteDir(context.getExternalCacheDir());
        }
    }

    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            if (EmptyUtils.isNotEmpty(children)) {
                for (int i = 0; i < children.length; i++) {
                    boolean success = deleteDir(new File(dir, children[i]));
                    if (!success) {
                        return false;
                    }
                }
            }

            return dir.delete();
        } else {
            return false;
        }
    }

    /**
     * 格式化缓存文件大小
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
//            return size + "Byte";
            return "0K";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }

    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            if (EmptyUtils.isNotEmpty(fileList)) {
                for (int i = 0; i < fileList.length; i++) {
                    // 如果下面还有文件
                    if (fileList[i].isDirectory()) {
                        size = size + getFolderSize(fileList[i]);
                    } else {
                        size = size + fileList[i].length();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }
}