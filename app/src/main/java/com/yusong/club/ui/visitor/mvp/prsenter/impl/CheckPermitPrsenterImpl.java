package com.yusong.club.ui.visitor.mvp.prsenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.visitor.entity.CardDetailsBean;
import com.yusong.club.ui.visitor.mvp.ImplView.ICheckPermitView;
import com.yusong.club.ui.visitor.mvp.prsenter.CheckPermitPrsenter;
import com.yusong.club.utils.CacheUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 * @created at 2017-08-30.
 * @describe: 通行证验证
 */

public class CheckPermitPrsenterImpl extends BasePresenterImpl<ICheckPermitView> implements CheckPermitPrsenter {
    public CheckPermitPrsenterImpl(ICheckPermitView view, Context context) {
        super(view, context);
    }

    @Override
    public void CheckPermitPrsenter(String cardContent) {
        Subscription subscription = HttpUtil.getInstance().checkPermit(CacheUtils.getToken(mContext), cardContent)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<CardDetailsBean>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<CardDetailsBean> cardDetailsBeanHttpResult) {
                        mView.checkPermitSucces(cardDetailsBeanHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }
}
