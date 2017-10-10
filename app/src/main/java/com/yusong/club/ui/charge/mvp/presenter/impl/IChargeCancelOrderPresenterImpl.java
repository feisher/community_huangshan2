package com.yusong.club.ui.charge.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.charge.mvp.implView.IChargeCancelOrderView;
import com.yusong.club.ui.charge.mvp.presenter.IChargeCancelOrderPresenter;
import com.yusong.club.utils.CacheUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian4 on 2017/1/17.
 * 取消订单
 */

public class IChargeCancelOrderPresenterImpl extends BasePresenterImpl<IChargeCancelOrderView> implements IChargeCancelOrderPresenter {
    @Override
    public void cancelOrder(String orderId) {
        Subscription subscription = HttpUtil.getInstance().cancelOrder(CacheUtils.getToken(mContext), orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.cancelMessage("取消成功");
                    }
                });
        addSubcribe(subscription);
    }

    public IChargeCancelOrderPresenterImpl(IChargeCancelOrderView view, Context context) {
        super(view, context);
    }
}
