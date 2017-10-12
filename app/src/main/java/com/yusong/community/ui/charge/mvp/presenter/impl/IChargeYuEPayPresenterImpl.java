package com.yusong.community.ui.charge.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.charge.mvp.implView.IChargeYuEPayView;
import com.yusong.community.ui.charge.mvp.presenter.IChargeYuEPayPresenter;
import com.yusong.community.utils.CacheUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_Peng on 2017/1/12.
 * 余额支付
 */

public class IChargeYuEPayPresenterImpl extends BasePresenterImpl<IChargeYuEPayView> implements IChargeYuEPayPresenter {

    public IChargeYuEPayPresenterImpl(IChargeYuEPayView view, Context context) {
        super(view, context);
    }

    @Override
    public void yuEPay(String orderId, float price, int volume) {
        Subscription subscription = HttpUtil.getInstance().yuEPay(CacheUtils.getToken(mContext), orderId, price, volume)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.yuEMessage("支付成功");
                    }
                });
        addSubcribe(subscription);
    }
}
