package com.yusong.community.ui.me.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.me.mvp.entity.MoneyList;
import com.yusong.community.ui.me.mvp.implView.IBalanceView;
import com.yusong.community.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * <li>时间：17/3/7 16:26 </li>
 * </ul>
 */
public class BalancePresenterImpl extends BasePresenterImpl<IBalanceView> {

    public BalancePresenterImpl(IBalanceView v, Context mContext) {
        super(v, mContext);
    }

    public void queryBalance() {
        Subscription subscription = HttpUtil.getInstance()
                .queryBalance(CacheUtils.getToken(mContext), CacheUtils.getId(mContext) + "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<MoneyList>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.closeLoading();
                    }

                    @Override
                    protected void onSuccess(HttpResult<MoneyList> result) {
                        mView.closeLoading();
                        mView.updateBalance((result.data.getBalance() / 100f) + "");
                    }
                });

        addSubcribe(subscription);
    }


    public void queryMoneyList(String token, int id, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance()
                .queryMoneyList(token, id, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<MoneyList>>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.closeLoading();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<MoneyList>> result) {
                        mView.closeLoading();
                        mView.setMoneyAdapter(result.data);


                    }
                });
        addSubcribe(subscription);
    }
}