package com.yusong.club.ui.charge.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.charge.bean.CreatOrderBean;
import com.yusong.club.ui.charge.mvp.implView.IChargeCreateOrderView;
import com.yusong.club.ui.charge.mvp.presenter.IChargeCreateOrderPresenter;
import com.yusong.club.utils.CacheUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_Peng on 2017/1/12.
 * 创建订单
 */

public class IChargeCreateOrderPresenterImpl extends BasePresenterImpl<IChargeCreateOrderView> implements IChargeCreateOrderPresenter {
    public IChargeCreateOrderPresenterImpl(IChargeCreateOrderView view, Context context) {
        super(view, context);
    }


    @Override
    public void createOrder(String chargerId, int duration, float price,int volume) {
        Subscription subscription = HttpUtil.getInstance().createOrder(CacheUtils.getToken(mContext),chargerId, duration, price,volume)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<CreatOrderBean>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<CreatOrderBean> creatOrderBeanHttpResult) {
                        mView.notify(creatOrderBeanHttpResult.data.getId());
                    }
                });
        addSubcribe(subscription);
    }
}
