package com.yusong.club.ui.charge.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.charge.bean.FetchMoneyBean;
import com.yusong.club.ui.charge.mvp.implView.IChargeYuyueDetalisView;
import com.yusong.club.ui.charge.mvp.presenter.IChargeYuYueDetailsPresenter;
import com.yusong.club.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_Peng on 2017/1/12.
 * 充电收费标准
 */

public class IChargeYuyueDetailsPresenterImpl extends BasePresenterImpl<IChargeYuyueDetalisView> implements IChargeYuYueDetailsPresenter {
    public IChargeYuyueDetailsPresenterImpl(IChargeYuyueDetalisView view, Context context) {
        super(view, context);
    }

    @Override
    public void queryFetchMoney(int type) {
        Subscription subscription = HttpUtil.getInstance().queryFetchMoney(CacheUtils.getToken(mContext), type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<FetchMoneyBean>>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<FetchMoneyBean>> listHttpResult) {
                        mView.refreshView(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }
}
