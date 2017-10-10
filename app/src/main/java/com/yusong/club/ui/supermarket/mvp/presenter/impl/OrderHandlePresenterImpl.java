package com.yusong.club.ui.supermarket.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.supermarket.mvp.ImolView.OrderHandleView;
import com.yusong.club.ui.supermarket.mvp.presenter.OrderHandlePresenter;
import com.yusong.club.utils.CacheUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 * @created at 2017-09-08.
 * @describe: 超市  提交评论/确认收货/申请退货
 */

public class OrderHandlePresenterImpl extends BasePresenterImpl<OrderHandleView> implements OrderHandlePresenter {
    @Override
    public void commitSMComment(String orderId, String content) {
        Subscription subscription = HttpUtil.getInstance().commitSMComment(CacheUtils.getToken(mContext), orderId, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.commitCommentSucced(httpResult.message);
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void cancelSmOrder(String orderId) {
        Subscription subscription = HttpUtil.getInstance().smCancelOrder(CacheUtils.getToken(mContext), orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.cancelSmOrderSucced(httpResult.message);
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void confirmSMReceipt(String orderId) {
        Subscription subscription = HttpUtil.getInstance().confirmReceipt(CacheUtils.getToken(mContext), orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.confirmReceiptSucced(httpResult.message);
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void salesSMReturn(String orderId, String returnReason) {
        Subscription subscription = HttpUtil.getInstance().salesReturn(CacheUtils.getToken(mContext), orderId, returnReason)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.salesReturnSucced(httpResult.message);
                    }
                });
        addSubcribe(subscription);
    }

    public OrderHandlePresenterImpl(OrderHandleView view, Context context) {
        super(view, context);
    }
}
