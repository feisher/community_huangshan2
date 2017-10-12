package com.yusong.community.ui.charge.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.charge.bean.MyOrderBean;
import com.yusong.community.ui.charge.mvp.implView.ICharMyOrderView;
import com.yusong.community.ui.charge.mvp.presenter.ICharMyOrderPresenter;
import com.yusong.community.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_Peng on 2017/1/12.
 * 我的订单
 */

public class ICharMyOrderPresenterImpl extends BasePresenterImpl<ICharMyOrderView> implements ICharMyOrderPresenter {
    public ICharMyOrderPresenterImpl(ICharMyOrderView view, Context context) {
        super(view, context);
    }

    @Override
    public void queryMyOrder(String type, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance().queryMyOrder(CacheUtils.getToken(mContext), type, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<MyOrderBean>>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<MyOrderBean>> result) {
                        mView.refreshView(result.data);
                    }
                });
        addSubcribe(subscription);
    }
}
