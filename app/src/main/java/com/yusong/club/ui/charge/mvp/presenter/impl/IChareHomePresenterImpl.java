package com.yusong.club.ui.charge.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.charge.mvp.implView.IChargeHomeView;
import com.yusong.club.ui.charge.mvp.presenter.IChargeHomePresenter;
import com.yusong.club.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_Peng on 2017/1/12.
 * 主页轮播图
 */

public class IChareHomePresenterImpl extends BasePresenterImpl<IChargeHomeView> implements IChargeHomePresenter {
    public IChareHomePresenterImpl(IChargeHomeView view, Context context) {
        super(view, context);
    }

    @Override
    public void requestChargeHomeBanner() {
        Subscription subscription = HttpUtil.getInstance().queryBanner(CacheUtils.getToken(mContext))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<String>>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<String>> listHttpResult) {
                        if (listHttpResult.data != null && listHttpResult.data.size() != 0) {
                            mView.refreshView(listHttpResult.data);
                        }
                    }
                });
        addSubcribe(subscription);
    }
}
