package com.yusong.community.ui.shoppers.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.shoppers.bean.CreataOrderBean;
import com.yusong.community.ui.shoppers.mvp.implView.ImplCreateOrderView;
import com.yusong.community.ui.shoppers.mvp.presenter.ImplCreateOrderPersenter;
import com.yusong.community.utils.CacheUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 下单
 */

public class ImplCreateOrderPersenterImpl extends BasePresenterImpl<ImplCreateOrderView> implements ImplCreateOrderPersenter {
    public ImplCreateOrderPersenterImpl(ImplCreateOrderView view, Context context) {
        super(view, context);
    }

    @Override
    public void createOrder(Integer deliverType,Integer grabItemId, int itemId, int amount, double price, String province,
                            String city, String district, String street, String receiverMobile,
                            String receiver,String distributionTime, String leaveMessage,String specitication) {
        Subscription subscription = HttpUtil.getInstance().shopCreateOrder(CacheUtils.getToken(mContext),
                deliverType, grabItemId, itemId, amount, price, province,
                city, district, street, receiverMobile, receiver, distributionTime,leaveMessage,specitication)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<CreataOrderBean>>(mContext) {
                    @Override
                    protected void onFailure(String e) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<CreataOrderBean> stringHttpResult) {
                        mView.createOrderSucced(stringHttpResult.data.getId());
                    }
                });
        addSubcribe(subscription);
    }
}
