package com.yusong.club.ui.visitor.mvp.prsenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.visitor.entity.ThroughCardBean;
import com.yusong.club.ui.visitor.mvp.ImplView.CreateThroughView;
import com.yusong.club.ui.visitor.mvp.prsenter.CreateThroughPrsenter;
import com.yusong.club.utils.CacheUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 * @created at 2017-08-30.
 * @describe: 创建通信证
 */

public class CreateThroughCardPrsenterImpl extends BasePresenterImpl<CreateThroughView> implements CreateThroughPrsenter {
    public CreateThroughCardPrsenterImpl(CreateThroughView view, Context context) {
        super(view, context);
    }


    @Override
    public void createThroughCard(String visitorName, int sex, String visitorTime, int isDrive, String plateNumber) {
        Subscription subscription = HttpUtil.getInstance().createThroughCrad(CacheUtils.getToken(mContext),  visitorName,  sex,  visitorTime,  isDrive,  plateNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<ThroughCardBean>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<ThroughCardBean> throughCardBeanHttpResult) {
                        mView.createSucces(throughCardBeanHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }
}
