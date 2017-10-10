package com.yusong.club.ui.shoppers.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.shoppers.mvp.implView.ImplShangpinPinlunView;
import com.yusong.club.ui.shoppers.mvp.presenter.ImplShangpinPinlunPresenter;
import com.yusong.club.utils.CacheUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_Peng on 2017/3/2.
 * 添加商品评论
 */

public class ImplShangpinPinlunPresenterImpl extends BasePresenterImpl<ImplShangpinPinlunView> implements ImplShangpinPinlunPresenter {
    @Override
    public void pinLun(String orderId, String content) {
        Subscription subscription = HttpUtil.getInstance().addPingLun(CacheUtils.getToken(mContext), orderId, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<String>>() {
                    @Override
                    protected void onFailure(String e) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<String> stringHttpResult) {
                        mView.pinLunSucced();
                    }
                });
        addSubcribe(subscription);
    }

    public ImplShangpinPinlunPresenterImpl(ImplShangpinPinlunView view, Context context) {
        super(view, context);
    }
}
