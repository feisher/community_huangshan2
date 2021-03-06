package com.yusong.community.ui.me.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.community.MyApplication;
import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.me.mvp.implView.IMySettingActivityView;
import com.yusong.community.ui.me.mvp.presenter.IMySettingActivityPresenter;
import com.yusong.community.utils.CacheUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by feisher on 2017/1/21.
 */
public class MySettingActivityPresenterImpl extends BasePresenterImpl<IMySettingActivityView> implements IMySettingActivityPresenter {

    public MySettingActivityPresenterImpl(IMySettingActivityView v, Context mContext) {
        super(v,mContext);
    }

    @Override
    public void feedback(String content) {
        String token =CacheUtils.getToken(mContext);
        Subscription subscription = HttpUtil.getInstance()
                .feedbackCreate(token,content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<String>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<String> stringHttpResult) {
                        if (stringHttpResult.code==0) {
                            MyApplication.showMessage("反馈成功,感谢您的参与！");
                        }else {
                            if (!TextUtils.isEmpty(stringHttpResult.message)) {
                                MyApplication.showMessage(stringHttpResult.message);
                            }
                        }
                    }
                });

        addSubcribe(subscription);
    }

    @Override
    public void scoring(float grade) {
        String token =CacheUtils.getToken(mContext);
        Subscription subscription = HttpUtil.getInstance()
                .app_gradeCreate(token,grade)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<String>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<String> stringHttpResult) {
                        if (stringHttpResult.code==0) {
                            MyApplication.showMessage("评分成功,感谢您的参与！");
                        }else {
                            if (!TextUtils.isEmpty(stringHttpResult.message)) {
                                MyApplication.showMessage(stringHttpResult.message);
                            }
                        }
                    }
                });

        addSubcribe(subscription);

    }
}
