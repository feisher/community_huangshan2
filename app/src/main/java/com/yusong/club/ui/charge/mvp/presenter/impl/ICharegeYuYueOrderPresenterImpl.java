package com.yusong.club.ui.charge.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.charge.mvp.implView.ICharegeYuYueOrderView;
import com.yusong.club.ui.charge.mvp.presenter.ICharegeYuYueOrderPresenter;
import com.yusong.club.utils.CacheUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_Peng on 2017/1/12.
 * 预约订单
 */

public class ICharegeYuYueOrderPresenterImpl extends BasePresenterImpl<ICharegeYuYueOrderView> implements ICharegeYuYueOrderPresenter {
    public ICharegeYuYueOrderPresenterImpl(ICharegeYuYueOrderView view, Context context) {
        super(view, context);
    }

    @Override
    public void queryBespeak(String chargerId, int duration, float price ,int volume) {
        Subscription subscription = HttpUtil.getInstance().queryBespeak(
                CacheUtils.getToken(mContext),chargerId, duration, price,volume)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<String>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<String> stringHttpResult) {

                    }
                });
        addSubcribe(subscription);
    }
}
