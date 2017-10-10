package com.yusong.club.ui.express.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.express.mvp.implView.IBannerView;
import com.yusong.club.ui.express.mvp.presenter.GetKdgBannerPresenter;
import com.yusong.club.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 *         crated at on 2017/5/23.
 *         描述: null
 */

public class GetKdgBannerPresenterImpl extends BasePresenterImpl<IBannerView> implements GetKdgBannerPresenter {
    @Override
    public void queryKdgBanner() {
        Subscription subscription = HttpUtil.getInstance().queryKdgBanner(CacheUtils.getToken(mContext))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<String>>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<String>> listHttpResult) {
                        if (listHttpResult.data != null && listHttpResult.data.size() != 0) {
                            mView.bannerSucced(listHttpResult.data);
                        }
                    }
                });
        addSubcribe(subscription);
    }

    public GetKdgBannerPresenterImpl(IBannerView view, Context context) {
        super(view, context);
    }
}
