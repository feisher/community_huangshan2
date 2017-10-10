package com.yusong.club.ui.express.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.express.mvp.entity.Order;
import com.yusong.club.ui.express.mvp.implView.IOpenBoxListView;
import com.yusong.club.ui.express.mvp.presenter.IOpenBoxListPresenter;
import com.yusong.club.utils.AppUtils;
import com.yusong.club.utils.CacheUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class IOpenBoxListPresenterImpl extends BasePresenterImpl<IOpenBoxListView> implements IOpenBoxListPresenter {

    private List<Order> orderAll = new ArrayList<>();


    public IOpenBoxListPresenterImpl(IOpenBoxListView view, Context context) {
        super(view, context);
    }

    @Override
    public void isEmpty(List<Order> mDeliverList, List<Order> mSendOrderList, List<Order> mStoreOrderList) {


        if (!AppUtils.listIsEmpty(mDeliverList)) {
            orderAll.addAll(mDeliverList);
        }
        if (!AppUtils.listIsEmpty(mSendOrderList)) {
            orderAll.addAll(mSendOrderList);
        }
        if (!AppUtils.listIsEmpty(mStoreOrderList)) {
            orderAll.addAll(mStoreOrderList);
        }

        if (!AppUtils.listIsEmpty(orderAll)) {
            mView.setOrderAdapter(orderAll);
        } else {
            mView.OrderEmpty();
        }
    }

    @Override
    public void openBox(List<Order> mDeliverList, List<Order> mSendOrderList, List<Order> mStoreOrderList, Order order) {

        if (!AppUtils.listIsEmpty(mDeliverList)) {

            if (mDeliverList.contains(order)) {

                openBox(CacheUtils.getToken(mContext), 2, order.getId());
            }
        }

        if (!AppUtils.listIsEmpty(mSendOrderList)) {

            if (mSendOrderList.contains(order)) {

                openBox(CacheUtils.getToken(mContext), 1, order.getId());
            }
        }

        if (!AppUtils.listIsEmpty(mStoreOrderList)) {

            if (mStoreOrderList.contains(order)) {

                openBox(CacheUtils.getToken(mContext), 3, order.getId());
            }
        }


    }

    private void openBox(String token, int type, String id) {

        Subscription subscription = HttpUtil.getInstance().openBox(token, type, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult result) {
                        mView.close();
                    }
                });
        addSubcribe(subscription);
    }
}
