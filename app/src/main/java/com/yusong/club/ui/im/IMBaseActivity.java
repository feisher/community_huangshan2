package com.yusong.club.ui.im;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.ui.EaseBaseActivity;
import com.yusong.club.MyApplication;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.ui.home.activity.LoginActivity;
import com.yusong.club.ui.home.mvp.cache.TokenInfo;
import com.yusong.club.ui.home.mvp.entity.LoginResult;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.Constants;
import com.yusong.club.utils.LogUtils;
import com.yusong.club.utils.ToastUtils;

import java.io.File;
import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * <li>时间：17/2/23 09:05 </li>
 * </ul>
 */
public abstract class IMBaseActivity extends EaseBaseActivity {

    public boolean LOGIN_TIMEOUT = true;
    //当前Activity的弱引用，防止内存泄露
    private WeakReference<Activity> context = null;
    protected CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //将当前Activity压入栈
        context = new WeakReference<Activity>(this);
        MyApplication.addActivity(context);
        ButterKnife.inject(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        TokenInfo info = CacheUtils.getTokenInfo(this);
        if (info != null) {
            long expireIn = info.getExpireIn() - 60000;
            long saveTime = info.getSaveTime();
            long millis = System.currentTimeMillis();
            if ((millis - saveTime) > expireIn) {
                LogUtils.e("expireIn = " + expireIn + "  saveTime = " + saveTime + "  millis =" + millis);
                loginAsyc(this, info.getName(), info.getPwd(), Constants.AGENTID);
            }
            return;
        } else {
            if (LOGIN_TIMEOUT) {
                ToastUtils.showShort(this, "登陆超时，请重新登录！");
                EMClient.getInstance().logout(true);
                MyApplication.exit();
                startActivity(new Intent(this, LoginActivity.class));
            }
        }
    }

    /**
     * 用于登录 刷新token
     *
     * @param mContext
     * @param username
     * @param pswd
     * @param agentId
     */
    public void loginAsyc(final Context mContext, final String username, final String pswd, int agentId) {

        Subscription subscription = HttpUtil.getInstance().
                login(username, pswd, agentId)
                .doOnNext(new Action1<HttpResult<LoginResult>>() {
                    @Override
                    public void call(HttpResult<LoginResult> result) {
                        if (result.code == 0) {
                            saveLoginInfo(result, username, pswd);
                            loginIm(username, pswd);
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
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HttpResult<LoginResult> result) {
                        if (result.code == 0) {
                            LogUtils.e("-------------------刷新token成功-------------------");
                        }
                    }
                });
        addSubcribe(subscription);
    }



    private void loginIm(String username, String pswd) {
        EMClient.getInstance().login(username, pswd, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                LogUtils.e("登录环信成功！");
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                LogUtils.e("登录环信失败！" + message);
            }
        });
    }

    private void saveLoginInfo(HttpResult<LoginResult> result, String username
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
        CacheUtils.saveTokenInfo(this, tokenInfo);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unSubscribe();
        MyApplication.removeActivity(context);

    }

    protected void addSubcribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);//将所有subscription放入,集中处理
    }

    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();//保证activity结束时取消所有正在执行的订阅
        }
    }

    public Uri getImageContentUri(File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }



}
