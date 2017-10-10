package com.yusong.club.ui.tenement.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.pay.bean.WeiXinPayBean;
import com.yusong.club.pay.bean.ZhiFuBaoPayBean;
import com.yusong.club.ui.tenement.mvp.implview.TenementPayView;
import com.yusong.club.ui.tenement.mvp.presenter.TenementPayPresenter;
import com.yusong.club.utils.CacheUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 * @created at 2017-09-13.
 * @describe: 物业缴费
 */

public class TenementPayPresenterImpl extends BasePresenterImpl<TenementPayView> implements TenementPayPresenter {
    @Override
    public void zlTenementPay(String orderId) {
        Subscription subscription = HttpUtil.getInstance().tenementBalancePay(CacheUtils.getToken(mContext), orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.zhilianPaySucced();
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void wxTenementPay(String orderId) {
        Subscription subscription = HttpUtil.getInstance().tenementWeixinPay(CacheUtils.getToken(mContext), orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<WeiXinPayBean>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<WeiXinPayBean> weiXinPayBeanHttpResult) {
                        mView.WXPaySucced(weiXinPayBeanHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void zfbTenementPay(String orderId) {
        Subscription subscription = HttpUtil.getInstance().tenementZhifubaoPay(CacheUtils.getToken(mContext), orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<ZhiFuBaoPayBean>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<ZhiFuBaoPayBean> zhiFuBaoPayBeanHttpResult) {
                        mView.ZFBPaySucced(zhiFuBaoPayBeanHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    public TenementPayPresenterImpl(TenementPayView view, Context context) {
        super(view, context);
    }
}
