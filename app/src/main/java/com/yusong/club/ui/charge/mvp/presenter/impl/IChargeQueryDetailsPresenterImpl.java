package com.yusong.club.ui.charge.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.charge.bean.NearbyBean;
import com.yusong.club.ui.charge.mvp.implView.IChargeQueryDetailsView;
import com.yusong.club.ui.charge.mvp.presenter.IChargeQueryDetailsPresenter;
import com.yusong.club.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian4 on 2017/1/17.
 * 扫码查询充电桩信息
 */

public class IChargeQueryDetailsPresenterImpl extends BasePresenterImpl<IChargeQueryDetailsView> implements IChargeQueryDetailsPresenter {
    public IChargeQueryDetailsPresenterImpl(IChargeQueryDetailsView view, Context context) {
        super(view, context);
    }

    @Override
    public void queryChargeDetalis(String code) {
        Subscription subscription = HttpUtil.getInstance().scanQueryChargeDetails(CacheUtils.getToken(mContext), code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<NearbyBean>>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<NearbyBean>> listHttpResult) {
                        if (listHttpResult.data.size() > 0) {
                            mView.toChareDetails(listHttpResult.data);
                        } else {
                            MyApplication.showMessage(listHttpResult.message);
                        }
                    }
                });
        addSubcribe(subscription);
    }

}
