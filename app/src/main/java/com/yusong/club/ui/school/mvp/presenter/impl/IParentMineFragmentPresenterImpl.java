package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.mvp.entity.UserInfoDetails;
import com.yusong.club.ui.school.mvp.implView.IParentMineFragmentView;
import com.yusong.club.ui.school.mvp.presenter.IParentMineFragmentPresenter;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian on 2017/3/28.
 */

public class IParentMineFragmentPresenterImpl extends BasePresenterImpl<IParentMineFragmentView> implements IParentMineFragmentPresenter {
    public IParentMineFragmentPresenterImpl(IParentMineFragmentView view, Context context) {
        super(view, context);
    }

    @Override
    public void queryParentInfo(String token, Integer id) {
        Subscription subscription = HttpUtil.getInstance().queryParentInfo(token, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<UserInfoDetails>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<UserInfoDetails> result) {

                        mView.getParentInfo(result.data);

                    }
                });
        addSubcribe(subscription);

    }
}
