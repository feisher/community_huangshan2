package com.yusong.community.ui.charge.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.charge.mvp.implView.ICharStartChargeView;
import com.yusong.community.ui.charge.mvp.presenter.IChargeStartChargePresenter;
import com.yusong.community.utils.CacheUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_Peng on 2017/1/12.
 * 开始充电
 */

public class IChargeStartChargePresenterImpl extends BasePresenterImpl<ICharStartChargeView> implements IChargeStartChargePresenter {
    public IChargeStartChargePresenterImpl(ICharStartChargeView view, Context context) {
        super(view, context);
    }

    @Override
    public void startCharge(String chargerId, String orderId) {
        Subscription subscription = HttpUtil.getInstance().startCharge(CacheUtils.getToken(mContext), chargerId, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.startChargeSucced();
                    }
                });
        addSubcribe(subscription);
    }
}
