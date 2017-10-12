package com.yusong.community.ui.charge.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.charge.bean.NearbyBean;
import com.yusong.community.ui.charge.mvp.implView.IChargeYuYueView;
import com.yusong.community.ui.charge.mvp.presenter.IChargeYuYuePresenter;
import com.yusong.community.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_Peng on 2017/1/12.
 * 查询附近桩点
 */

public class IChargeYuYuePresenterImpl extends BasePresenterImpl<IChargeYuYueView> implements IChargeYuYuePresenter {
    @Override
    public void qureyFuJinChage(float lng, float lat, String type,String keyword) {
        Subscription subscription = HttpUtil.getInstance().queryNearby(lng, lat, type, keyword, CacheUtils.getToken(mContext))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<NearbyBean>>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<NearbyBean>> listHttpResult) {
                        mView.reshreView(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);

    }

    public IChargeYuYuePresenterImpl(IChargeYuYueView view, Context context) {
        super(view, context);
    }
}
