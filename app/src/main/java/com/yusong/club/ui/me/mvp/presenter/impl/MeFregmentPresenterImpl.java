package com.yusong.club.ui.me.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.me.mvp.entity.UserInfo;
import com.yusong.club.ui.me.mvp.implView.IMeFregmentView;
import com.yusong.club.ui.me.mvp.presenter.IMeFragmentPresenter;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.SPUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by feisher on 2017/1/21.
 */
public class MeFregmentPresenterImpl extends BasePresenterImpl<IMeFregmentView> implements IMeFragmentPresenter {

    public MeFregmentPresenterImpl(IMeFregmentView v, Context mContext) {
        super(v,mContext);
    }


    @Override
    public void queryUserInfo(String token) {
        Subscription subscription = HttpUtil.getInstance()
                .queryUserInfo(token)
                .doOnNext(new Action1<HttpResult<UserInfo>>() {
                    @Override
                    public void call(HttpResult<UserInfo> userInfoHttpResult) {
                        if (userInfoHttpResult.code==0&&userInfoHttpResult.data!=null) {
                            SPUtils.saveObject(mContext,"UserInfo",(UserInfo) userInfoHttpResult.data);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<UserInfo>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.closeLoading();
                    }

                    @Override
                    protected void onSuccess(HttpResult<UserInfo> userInfoHttpResult) {
                        mView.closeLoading();
                        if (userInfoHttpResult.code==0&&userInfoHttpResult.data!=null) {
                            mView.getUserInfoCallback((UserInfo) userInfoHttpResult.data);
                        }else {
                            if (!TextUtils.isEmpty(userInfoHttpResult.message)) {
                                MyApplication.showMessage(userInfoHttpResult.message);
                            }
                        }
                    }
                });

        addSubcribe(subscription);

    }

    @Override
    public void signIn() {
        Subscription subscription = HttpUtil.getInstance()
                .signIn(CacheUtils.getToken(mContext))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<String>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<String> stringHttpResult) {
                        if (stringHttpResult.code==0) {
                            mView.signInCallback();
                            queryUserInfo(CacheUtils.getToken(mContext));
                        }
                        if (!TextUtils.isEmpty(stringHttpResult.message)) {
                            MyApplication.showMessage(stringHttpResult.message);
                        }
                    }
                });
        addSubcribe(subscription);
    }
}
