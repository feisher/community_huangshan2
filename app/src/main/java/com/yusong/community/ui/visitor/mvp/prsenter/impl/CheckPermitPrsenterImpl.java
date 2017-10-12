package com.yusong.community.ui.visitor.mvp.prsenter.impl;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.visitor.entity.CardDetailsBean;
import com.yusong.community.ui.visitor.mvp.ImplView.ICheckPermitView;
import com.yusong.community.ui.visitor.mvp.prsenter.CheckPermitPrsenter;
import com.yusong.community.utils.CacheUtils;

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
