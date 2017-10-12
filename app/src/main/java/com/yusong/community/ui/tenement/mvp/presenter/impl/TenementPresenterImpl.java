package com.yusong.community.ui.tenement.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.tenement.entity.TenementBean;
import com.yusong.community.ui.tenement.mvp.presenter.TenementPresenter;
import com.yusong.community.ui.tenement.mvp.implview.TenementView;
import com.yusong.community.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 * @created at 2017-09-04.
 * @describe: null
 */

public class TenementPresenterImpl extends BasePresenterImpl<TenementView> implements TenementPresenter {
    @Override
    public void queryTenementPay(int communityId, int proprietorId, int isPay) {
        Subscription subscription = HttpUtil.getInstance().queryTenementPay(CacheUtils.getToken(mContext), communityId, proprietorId, isPay, 0, 200)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<TenementBean>>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<TenementBean>> listHttpResult) {
                        mView.queryTenementSucces(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    public TenementPresenterImpl(TenementView view, Context context) {
        super(view, context);
    }
}
