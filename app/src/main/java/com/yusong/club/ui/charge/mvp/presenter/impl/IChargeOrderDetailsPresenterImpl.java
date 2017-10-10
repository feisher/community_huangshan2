package com.yusong.club.ui.charge.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.charge.bean.OrderDetailsBean;
import com.yusong.club.ui.charge.mvp.implView.IChargeOrderDetailsView;
import com.yusong.club.ui.charge.mvp.presenter.IChargeOrderDetailsPresenter;
import com.yusong.club.utils.CacheUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_Peng on 2017/1/12.
 * 查询订单明细
 */

public class IChargeOrderDetailsPresenterImpl extends BasePresenterImpl<IChargeOrderDetailsView> implements IChargeOrderDetailsPresenter {
    public IChargeOrderDetailsPresenterImpl(IChargeOrderDetailsView view, Context context) {
        super(view, context);
    }

    @Override
    public void queryOrderDetails(String orderId) {
        Subscription subscription = HttpUtil.getInstance().queryOrderDetails(CacheUtils.getToken(mContext), orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<OrderDetailsBean>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<OrderDetailsBean> orderDetailsBeanHttpResult) {

                    }
                });
        addSubcribe(subscription);
    }
}
