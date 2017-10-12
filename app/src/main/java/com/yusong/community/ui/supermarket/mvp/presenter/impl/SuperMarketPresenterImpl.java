package com.yusong.community.ui.supermarket.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.shoppers.bean.CreataOrderBean;
import com.yusong.community.ui.shoppers.bean.OrderShopBean;
import com.yusong.community.ui.supermarket.mvp.ImolView.SuperMarketOrderView;
import com.yusong.community.ui.supermarket.mvp.presenter.SuperMarketOrderPresenter;
import com.yusong.community.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 * @created at 2017-09-06.
 * @describe: 超市 下单/查单
 */

public class SuperMarketPresenterImpl extends BasePresenterImpl<SuperMarketOrderView> implements SuperMarketOrderPresenter {
    public SuperMarketPresenterImpl(SuperMarketOrderView view, Context context) {
        super(view, context);
    }

    @Override
    public void querySMOrder(String type, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance().querySuperMarketOrder(CacheUtils.getToken(mContext), type, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<OrderShopBean>>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.querySMOrderError();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<OrderShopBean>> listHttpResult) {
                        mView.querySMOrderSucced(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void createSMOrder(Integer deliverType, int itemId, int amount, double price, String province,
                              String city, String district, String street, String receiverMobile,
                              String reciever, String distributionTime, String leaveMessage,
                              String specitication) {
        Subscription subscription = HttpUtil.getInstance().smCreateOrder(CacheUtils.getToken(mContext),
                deliverType, itemId, amount, price, province, city, district, street, receiverMobile,
                reciever, distributionTime, leaveMessage,specitication)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<CreataOrderBean>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<CreataOrderBean> creataOrderBeanHttpResult) {
                        mView.createSucced(creataOrderBeanHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

}
