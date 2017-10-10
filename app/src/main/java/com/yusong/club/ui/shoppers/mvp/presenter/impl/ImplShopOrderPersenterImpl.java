package com.yusong.club.ui.shoppers.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.shoppers.bean.OrderShopBean;
import com.yusong.club.ui.shoppers.mvp.implView.ImplShopOrderView;
import com.yusong.club.ui.shoppers.mvp.presenter.ImplShopOrderPersenter;
import com.yusong.club.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 商城订单
 */

public class ImplShopOrderPersenterImpl extends BasePresenterImpl<ImplShopOrderView> implements ImplShopOrderPersenter {
    @Override
    public void queryShopOrder(String type, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance().MyShopOrder(CacheUtils.getToken(mContext), type, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<OrderShopBean>>>() {
                    @Override
                    protected void onFailure(String e) {
                        mView.orderClose();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<OrderShopBean>> listHttpResult) {
                        mView.orderSucced(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    public ImplShopOrderPersenterImpl(ImplShopOrderView view, Context context) {
        super(view, context);
    }
}
