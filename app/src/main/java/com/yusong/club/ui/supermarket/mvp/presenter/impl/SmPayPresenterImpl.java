package com.yusong.club.ui.supermarket.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.pay.bean.WeiXinPayBean;
import com.yusong.club.pay.bean.ZhiFuBaoPayBean;
import com.yusong.club.ui.supermarket.mvp.ImolView.SmPayView;
import com.yusong.club.ui.supermarket.mvp.presenter.SmPayPresenter;
import com.yusong.club.utils.CacheUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 * @created at 2017-09-08.
 * @describe: null
 */

public class SmPayPresenterImpl extends BasePresenterImpl<SmPayView> implements SmPayPresenter {
    public SmPayPresenterImpl(SmPayView view, Context context) {
        super(view, context);
    }

    @Override
    public void balanceSmPay(String orderId) {
        Subscription subscription = HttpUtil.getInstance().smBalancePay(CacheUtils.getToken(mContext), orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.smBalancePayError();
                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.smBalancePaySucced(httpResult.message);
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void weixinSmPay(String orderId) {
        Subscription subscription = HttpUtil.getInstance().smWeixinPay(CacheUtils.getToken(mContext), orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<WeiXinPayBean>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.smWeixinPayError();
                    }

                    @Override
                    protected void onSuccess(HttpResult<WeiXinPayBean> weiXinPayBeanHttpResult) {
                        mView.smWeixinPaySucced(weiXinPayBeanHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void zfbSmPay(String orderId) {
        Subscription subscription = HttpUtil.getInstance().smZhifubaoPay(CacheUtils.getToken(mContext), orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<ZhiFuBaoPayBean>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.smZFBPayError();
                    }

                    @Override
                    protected void onSuccess(HttpResult<ZhiFuBaoPayBean> zhiFuBaoPayBeanHttpResult) {
                        mView.smZFBPaySucced(zhiFuBaoPayBeanHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }
}
