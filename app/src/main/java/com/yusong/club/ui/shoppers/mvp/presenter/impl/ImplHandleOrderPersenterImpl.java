package com.yusong.club.ui.shoppers.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.shoppers.mvp.implView.ImplHandleOrderView;
import com.yusong.club.ui.shoppers.mvp.presenter.ImplHandleOrderPersenter;
import com.yusong.club.utils.CacheUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_Peng on 2017/3/2.
 * 订单操作
 */

public class ImplHandleOrderPersenterImpl extends BasePresenterImpl<ImplHandleOrderView> implements ImplHandleOrderPersenter {
    @Override
    public void cancelOreder(String orderId) {//取消订单
        Subscription subscription = HttpUtil.getInstance().shopCancelOrder(CacheUtils.getToken(mContext), orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<String>>() {
                    @Override
                    protected void onFailure(String e) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<String> stringHttpResult) {
                        mView.cancelSucced();
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void affirmOrder(String orderId) {
        Subscription subscription = HttpUtil.getInstance().affirmReceive(CacheUtils.getToken(mContext), orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {

                    }
                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.affirmSucced();

                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void reimburseOrder(String orderId,String returnReason) {
        Subscription subscription = HttpUtil.getInstance().reimburse(CacheUtils.getToken(mContext), orderId,returnReason)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.reimburseSucced();
                    }
                });
        addSubcribe(subscription);

    }

    public ImplHandleOrderPersenterImpl(ImplHandleOrderView view, Context context) {
        super(view, context);
    }
}
