package com.yusong.community.ui.express.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.express.mvp.entity.OpenBoxOrder;
import com.yusong.community.ui.express.mvp.implView.IScanOpenBoxView;
import com.yusong.community.ui.express.mvp.presenter.IScanOpenBoxPresenter;
import com.yusong.community.utils.CacheUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class IScanOpenBoxPresenterImpl extends BasePresenterImpl<IScanOpenBoxView>
        implements IScanOpenBoxPresenter {

    public IScanOpenBoxPresenterImpl(IScanOpenBoxView view, Context context) {
        super(view, context);
    }


    @Override
    public void queryTerminalCode(String result) {
        Subscription subscription = HttpUtil.getInstance().queryTerminalCode(CacheUtils.getToken(mContext),result)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<OpenBoxOrder>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<OpenBoxOrder> result) {
                        mView.jumpActivity(result);
                    }
                });

        addSubcribe(subscription);
    }
}
