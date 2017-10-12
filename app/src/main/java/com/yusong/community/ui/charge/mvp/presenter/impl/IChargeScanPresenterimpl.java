package com.yusong.community.ui.charge.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.charge.bean.SancContentBean;
import com.yusong.community.ui.charge.mvp.implView.IChargeScanView;
import com.yusong.community.ui.charge.mvp.presenter.IChargeScanPresenter;
import com.yusong.community.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_Peng on 2017/1/12.
 * 扫描充电桩
 */

public class IChargeScanPresenterimpl extends BasePresenterImpl<IChargeScanView> implements IChargeScanPresenter {
    @Override
    public void scanCharge(String code) {
        Subscription subscription = HttpUtil.getInstance().querSancContent(CacheUtils.getToken(mContext), code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<SancContentBean>>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<SancContentBean>> listHttpResult) {
                        if (listHttpResult.data.size() > 0) {
                            mView.jumpActivity(listHttpResult.data);
                        } else {
                            mView.queryNewCharge();
                        }
                    }
                });
        addSubcribe(subscription);
    }

    public IChargeScanPresenterimpl(IChargeScanView view, Context context) {
        super(view, context);
    }
}
