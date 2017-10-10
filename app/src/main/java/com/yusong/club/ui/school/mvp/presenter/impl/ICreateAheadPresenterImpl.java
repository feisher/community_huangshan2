package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.mvp.implView.ICreateAheadView;
import com.yusong.club.ui.school.mvp.presenter.ICreateAheadPresenter;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.ToastUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 *         crated at on 2017/3/31.
 *         描述: 创建提前放学
 */

public class ICreateAheadPresenterImpl extends BasePresenterImpl<ICreateAheadView> implements ICreateAheadPresenter {
    @Override
    public void createAhead(String overTime, int[] weekArray) {

        Subscription subscription = HttpUtil.getInstance()
                .CreateAheadAfterSchool(CacheUtils.getToken(mContext), overTime, weekArray[0], weekArray[1],
                        weekArray[2], weekArray[3], weekArray[4], weekArray[5], weekArray[6])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<String>>() {
                    @Override
                    protected void onFailure(String err) {
                    }

                    @Override
                    protected void onSuccess(HttpResult<String> stringHttpResult) {
                        if (stringHttpResult.code == 0) {
                            mView.createSucced();
                        } else {
                            ToastUtils.showShort(mContext, "新建失败");
                        }
                    }
                });
        addSubcribe(subscription);
    }

    public ICreateAheadPresenterImpl(ICreateAheadView view, Context context) {
        super(view, context);
    }
}
