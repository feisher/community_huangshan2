package com.yusong.community.api;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.community.MyApplication;
import com.yusong.community.ui.home.mvp.cache.TokenInfo;
import com.yusong.community.utils.AppUtils;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.Constants;
import com.yusong.community.utils.LogUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;
import rx.Subscriber;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * <li>时间：17/3/2 10:30 </li>
 * </ul>
 */
public abstract class BaseSubscriber<T> extends Subscriber<T> {


    private Context context;
    private ProgressDialogHandler mHandler;

    public BaseSubscriber(Context context) {
        this.context = context;
    }

    public BaseSubscriber() {

    }

    @Override
    public void onStart() {
        super.onStart();
        if (context != null) {
            mHandler = new ProgressDialogHandler(context);
            showProgressDialog();
        }
    }

    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ConnectException) {
            MyApplication.showMessage("当前网络不可用，请检查网络后重试");
        } else if (e instanceof SocketTimeoutException) {
            MyApplication.showMessage("请求超时，请稍后重试");
        } else if (e instanceof HttpException) {
            MyApplication.showMessage("网络繁忙");
        } else {
            MyApplication.showMessage("网络失败");
        }
        LogUtils.e("出错了大兄弟 -------------- " + e.toString());
        onFailure(e.getMessage());
        dismissProgressDialog();
    }

    protected abstract void onFailure(String err);

    @Override
    public void onNext(T t) {

        HttpResult result = (HttpResult) t;
        if (result.code == 0) {//成功
            onSuccess(t);
        } else if (result.code == 3) {//会话超时 刷新token
            Context context = MyApplication.getContext();
            TokenInfo info = CacheUtils.getTokenInfo(context);
            AppUtils.loginAsyc(info.getName(), info.getPwd(), Constants.AGENTID);
            onFailure(result.message);
        } else {
            if (!TextUtils.isEmpty(result.message)) {
                MyApplication.showMessage(result.message);
            }
            onFailure(result.message);
        }

    }

    protected abstract void onSuccess(T t);


    private void showProgressDialog() {
        if (mHandler != null) {
            mHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mHandler != null) {
            mHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mHandler = null;
        }
    }
}
