package com.yusong.club.ui.charge.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.charge.bean.NearbyBean;
import com.yusong.club.ui.charge.mvp.implView.IChargeQueryChargeDetailsView;
import com.yusong.club.ui.charge.mvp.presenter.IChargeQueryChargeDetailsPresenter;
import com.yusong.club.utils.CacheUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_Peng on 2017/2/20.
 */

public class IChargeQueryChargeDetailsPresenterImpl extends BasePresenterImpl<IChargeQueryChargeDetailsView> implements IChargeQueryChargeDetailsPresenter {
    @Override
    public void queryChargeDetails(String chargerId) {
        Subscription subscription = HttpUtil.getInstance().queryChargeDetails(CacheUtils.getToken(mContext), chargerId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<NearbyBean>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<NearbyBean> nearbyBeanHttpResult) {
                        mView.refreshCharge(nearbyBeanHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    public IChargeQueryChargeDetailsPresenterImpl(IChargeQueryChargeDetailsView view, Context context) {
        super(view, context);
    }
}
