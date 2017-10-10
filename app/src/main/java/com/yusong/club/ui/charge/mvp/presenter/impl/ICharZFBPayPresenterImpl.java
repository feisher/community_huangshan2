package com.yusong.club.ui.charge.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.pay.bean.ZhiFuBaoPayBean;
import com.yusong.club.ui.charge.mvp.implView.ICharZFBPayView;
import com.yusong.club.ui.charge.mvp.presenter.ICharZFBPayPresenter;
import com.yusong.club.utils.CacheUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_Peng on 2017/1/12.
 */

public class ICharZFBPayPresenterImpl extends BasePresenterImpl<ICharZFBPayView> implements ICharZFBPayPresenter {
    public ICharZFBPayPresenterImpl(ICharZFBPayView view, Context context) {
        super(view, context);
    }

    @Override
    public void ZFBPay(String orderId, float price, int volume) {
        Subscription subscription = HttpUtil.getInstance().zhiFuBaoPay(CacheUtils.getToken(mContext), orderId, price, volume)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<ZhiFuBaoPayBean>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<ZhiFuBaoPayBean> zhiFuBaoPayBeanHttpResult) {
                        mView.zhifubaoSucced(zhiFuBaoPayBeanHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }
}
