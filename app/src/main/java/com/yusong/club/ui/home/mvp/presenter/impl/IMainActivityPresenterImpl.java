package com.yusong.club.ui.home.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.home.mvp.implView.IMainActivityView;
import com.yusong.club.ui.home.mvp.presenter.IMainActivityPresenter;
import com.yusong.club.ui.me.mvp.entity.UserInfo;
import com.yusong.club.utils.SPUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by feisher on 2017/1/21.
 */
public class IMainActivityPresenterImpl extends BasePresenterImpl<IMainActivityView> implements IMainActivityPresenter {

    public IMainActivityPresenterImpl(IMainActivityView v, Context mContext) {
        super(v, mContext);
    }


    @Override
    public void queryUserInfo(String token) {
        Subscription subscription = HttpUtil.getInstance()
                .queryUserInfo(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<UserInfo>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<UserInfo> result) {

                        mView.getUserInfo((UserInfo) result.data);
                        UserInfo mUserInfo = (UserInfo) result.data;
                        if (mUserInfo != null) {
                            SPUtils.saveObject(mContext, "UserInfo", mUserInfo);
                        }

                    }
                });

        addSubcribe(subscription);
    }
}
