package com.yusong.community.ui.charge.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.charge.bean.MoveSosBean;
import com.yusong.community.ui.charge.mvp.implView.IChareMoveSosView;
import com.yusong.community.ui.charge.mvp.presenter.IChargeMoveSosPresenter;
import com.yusong.community.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_Peng on 2017/1/12.
 * 移动救援
 */

public class IChargeMoveSosPresenterImpl extends BasePresenterImpl<IChareMoveSosView> implements IChargeMoveSosPresenter {
    public IChargeMoveSosPresenterImpl(IChareMoveSosView view, Context context) {
        super(view, context);
    }

    @Override
    public void queryMoveSos(String type) {
        Subscription subscription = HttpUtil.getInstance().queryMoveSos(CacheUtils.getToken(mContext), type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<MoveSosBean>>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<MoveSosBean>> listHttpResult) {
                        mView.refreshView(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

}
