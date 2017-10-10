package com.yusong.club.ui.charge.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.pay.bean.WeiXinPayBean;
import com.yusong.club.ui.charge.mvp.implView.ICharWXPayView;
import com.yusong.club.ui.charge.mvp.presenter.ICharWXPayPresenter;
import com.yusong.club.utils.CacheUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_Peng on 2017/1/12.
 */

public class ICharWXPayPresenterImpl extends BasePresenterImpl<ICharWXPayView> implements ICharWXPayPresenter {
    public ICharWXPayPresenterImpl(ICharWXPayView view, Context context) {
        super(view, context);
    }

    @Override
    public void WXPay(String orderId, float price, int volume) {
        Subscription subscription = HttpUtil.getInstance().weiXinPay(CacheUtils.getToken(mContext), orderId, price, volume)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<WeiXinPayBean>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<WeiXinPayBean> weiXinPayBeanHttpResult) {
                        mView.weixinSucced(weiXinPayBeanHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }
}
