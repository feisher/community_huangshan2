package com.yusong.club.ui.charge.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.charge.bean.SettingBean;
import com.yusong.club.ui.charge.mvp.implView.IChargeSettingView;
import com.yusong.club.ui.charge.mvp.presenter.ICharegeSettingPresenter;
import com.yusong.club.utils.CacheUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_Peng on 2017/2/9.
 */

public class ICharegeSettingPresenterImpl extends BasePresenterImpl<IChargeSettingView> implements ICharegeSettingPresenter {

    public ICharegeSettingPresenterImpl(IChargeSettingView view, Context context) {
        super(view, context);
    }

    @Override
    public void querySettingPresenter() {
        Subscription subscription = HttpUtil.getInstance().querySeting(CacheUtils.getToken(mContext))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<SettingBean>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<SettingBean> settingBeanHttpResult) {
                        mView.SuccedNext(settingBeanHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }


}
