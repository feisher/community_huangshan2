package com.yusong.community.utils;

import android.text.TextUtils;
import android.util.Log;
/**
 * Created by quaner on 16/12/24.
 *
 * log统一
 */
public class LogUtils {

    public static boolean isLog = true;
    public static final String DEFAULT_TAG = "hszl";
    public static final String KDG_TAG = "kdg";

    public static void debugInfo(String tag, String msg) {
        if (!isLog || TextUtils.isEmpty(msg)) return;
        Log.d(tag, msg);

    }

    /**
     * author  hhj
     * TODO
     *
     * @param msg void
     */
    public static void debugInfo(String msg) {
        if (!isLog || TextUtils.isEmpty(msg)) return;
        debugInfo(DEFAULT_TAG, msg);
    }

    public static void warnInfo(String tag, String msg) {
        if (!isLog || TextUtils.isEmpty(msg)) return;
        Log.w(tag, msg);

    }

    public static void kdg_log(String msg) {
        if (!isLog || TextUtils.isEmpty(msg)) return;
        Log.e(KDG_TAG, msg);
    }

    public static void e(String tag, String msg) {
        if (!isLog || TextUtils.isEmpty(msg)) return;
        Log.e(tag, msg);
    }


    public static void e(String msg) {
        if (!isLog || TextUtils.isEmpty(msg)) return;
        Log.e(DEFAULT_TAG, msg);
    }

    public static void d(String tag, String msg) {
        if (!isLog || TextUtils.isEmpty(msg)) return;
        Log.d(tag, msg);
    }


    public static void d(String msg) {
        if (!isLog || TextUtils.isEmpty(msg)) return;
        Log.d(DEFAULT_TAG, msg);
    }

    /**
     * author  hhj
     * TODO
     *
     * @param msg void
     */
    public static void warnInfo(String msg) {
        warnInfo("zhibo", msg);
    }

    /**
     * author  hhj
     * TODO 使用Log来显示调试信息,因为log在实现上每个message有4k字符长度限制
     * 所以这里使用自己分节的方式来输出足够长度的message
     *
     * @param tag
     * @param str void
     */

    public static void debugLongInfo(String tag, String str) {
        if (!isLog) return;
        str = str.trim();
        int index = 0;
        int maxLength = 3500;
        String sub;
        while (index < str.length()) {
            // java的字符不允许指定超过总的长度end  
            if (str.length() <= index + maxLength) {
                sub = str.substring(index);
            } else {
                sub = str.substring(index, index + maxLength);
            }

            index += maxLength;
            Log.d(tag, sub.trim());
        }
    }

    /**
     * author  hhj
     * TODO
     *
     * @param str void
     */
    public static void debugLongInfo(String str) {
        debugLongInfo(DEFAULT_TAG, str);
    }

}
