package com.yusong.club.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.widget.ImageView;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.ui.home.mvp.cache.TokenInfo;
import com.yusong.club.ui.home.mvp.entity.LoginResult;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.util.List;
import java.util.Locale;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static android.content.Context.ACTIVITY_SERVICE;


/**
 * 跟App相关的辅助类（获取权限，分享，获取sha1）
 */
public class AppUtils {


    private AppUtils() {
    }

    //获取当前栈顶activity名称
    public static String getTopActivity(Activity context) {

        ActivityManager manager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);

        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);


        if (runningTaskInfos != null)

            return (runningTaskInfos.get(0).topActivity).getClassName().toString();

        else

            return null;

    }

    /**
     * 用于判空集合
     */
    public static boolean listIsEmpty(List list) {

        if (list == null || list.size() == 0) {
            return true;
        } else {
            return false;
        }
    }


    public static final int MIN_CLICK_DELAY_TIME = 50000;
    private static long lastClickTime = 0;

    /**
     * 用于登录 刷新token
     *
     * @param username
     * @param pswd
     * @param agentId
     */
    public static void loginAsyc(final String username, final String pswd, int agentId) {
        final long currentTime = System.currentTimeMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            Subscription subscription = HttpUtil.getInstance().
                    login(username, pswd, agentId)
                    .doOnNext(new Action1<HttpResult<LoginResult>>() {
                        @Override
                        public void call(HttpResult<LoginResult> result) {
                            if (result.code == 0) {
                                saveLoginInfo(result, username, pswd);
                            }
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<HttpResult<LoginResult>>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable err) {
                            lastClickTime = 0;
                        }

                        @Override
                        public void onNext(HttpResult<LoginResult> result) {
                            if (result.code == 0) {

                                LogUtils.e("-------------------刷新token成功-------------------");

                            }
                        }
                    });
        }
    }


    public static void loginIm(final String username, final String pswd) {
        EMClient.getInstance().login(username, pswd, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                LogUtils.e("登录环信成功！" + "--当前线程 = " + Thread.currentThread().getName());
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                syncIM(username, pswd);
                LogUtils.e("登录环信失败！" + message + "--当前线程 = " + Thread.currentThread().getName());
            }
        });
    }


    //同步环信
    private static void syncIM(final String username, final String pswd) {
        HttpUtil.getInstance().syncIM(CacheUtils.getToken(MyApplication.getContext()),
                CacheUtils.getTokenInfo(MyApplication.getContext()).getPwd())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult result) {
                        loginIm(username, pswd);
                        LogUtils.e("同步环信成功");
                    }
                });
    }

    public static void saveLoginInfo(HttpResult<LoginResult> result, String username
            , String pswd) {
        long timeMillis = System.currentTimeMillis();
        //缓存
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setName(username);
        tokenInfo.setPwd(pswd);
        tokenInfo.setToken(result.data.token);
        tokenInfo.setId(result.data.id);
        tokenInfo.setExpireIn(result.data.expireIn);
        tokenInfo.setSaveTime(timeMillis);
        tokenInfo.setImAccount(result.data.imAccount);
        CacheUtils.saveTokenInfo(MyApplication.getContext(), tokenInfo);
    }


    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;

        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取缓存大小
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
     * 清除缓存
     *
     * @param context
     */
    public static void clearAllCache(Context context) {
        deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && context.getExternalCacheDir() != null) {
            deleteDir(context.getExternalCacheDir());
        }
    }

    /**
     * 删除文件
     *
     * @param dir
     * @return
     */
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

    // 获取文件
    //Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
    //Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 格式化单位
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

    /**
     * 获取SHa1值
     *
     * @param context
     * @return
     */
    public static String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 检测网络资源是否存在
     *
     * @param strUrl
     * @return
     */
    public static boolean isNetFileAvailable(final String strUrl) {

        final InputStream[] netFileInputStream = {null};
        try {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    URL url = null;
                    try {
                        url = new URL(strUrl);
                        URLConnection urlConn = url.openConnection();
                        netFileInputStream[0] = urlConn.getInputStream();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();

            if (null != netFileInputStream[0]) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (netFileInputStream[0] != null)
                    netFileInputStream[0].close();
            } catch (IOException e) {
            }
        }
    }

    public static void setIcon(ImageView imageView, String code) {
        if ("SF".equals(code)) {
            imageView.setImageResource(R.mipmap.shunfeng);
        } else if ("HTKY".equals(code)) {
            imageView.setImageResource(R.mipmap.baishihuitong);
        } else if ("ZTO".equals(code)) {
            imageView.setImageResource(R.mipmap.zhongtong);
        } else if ("STO".equals(code)) {
            imageView.setImageResource(R.mipmap.shentong);
        } else if ("YTO".equals(code)) {
            imageView.setImageResource(R.mipmap.yuantong);
        } else if ("YD".equals(code)) {
            imageView.setImageResource(R.mipmap.yunda);
        } else if ("EMS".equals(code)) {
            imageView.setImageResource(R.mipmap.ems);
        } else if ("HHTT".equals(code)) {
            imageView.setImageResource(R.mipmap.tiantian);
        } else if ("JD".equals(code)) {
            imageView.setImageResource(R.mipmap.jd);
        } else if ("ZJS".equals(code)) {
            imageView.setImageResource(R.mipmap.zaijisong);
        } else {
            imageView.setImageResource(R.mipmap.moren);
        }

    }

}
