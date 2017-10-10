package com.yusong.club.ui.shoppers.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.shoppers.bean.CanQGBean;
import com.yusong.club.ui.shoppers.mvp.implView.ImplCanQGView;
import com.yusong.club.ui.shoppers.mvp.presenter.ImplCanQGPresenter;
import com.yusong.club.utils.CacheUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 *         created at 2017/3/10 19:27.
 *         查询可抢购商品数量
 */

public class ImplCanQGPresenterImpl extends BasePresenterImpl<ImplCanQGView> implements ImplCanQGPresenter {
    public ImplCanQGPresenterImpl(ImplCanQGView view, Context context) {
        super(view, context);
    }

    @Override
    public void queryCanQG(int grabItemId) {
        Subscription subscription = HttpUtil.getInstance().canQGSum(CacheUtils.getToken(mContext), grabItemId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<CanQGBean>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<CanQGBean> integerHttpResult) {
                        mView.canQG(((CanQGBean) integerHttpResult.data).getLimitRestAmount());
                    }
                });
        addSubcribe(subscription);
    }
}
