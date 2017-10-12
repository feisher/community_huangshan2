package com.yusong.community.ui.shoppers.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.shoppers.mvp.implView.ImplRefershView;
import com.yusong.community.ui.shoppers.mvp.presenter.IShopQueryImagePresenter;
import com.yusong.community.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 商城轮播图
 */

public class IShopQueryImagePresenterImpl extends BasePresenterImpl<ImplRefershView> implements IShopQueryImagePresenter {
    public IShopQueryImagePresenterImpl(ImplRefershView view, Context context) {
        super(view, context);
    }

    @Override
    public void queryImageList() {
        Subscription subscription = HttpUtil.getInstance().shopImageList(CacheUtils.getToken(mContext))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<String>>>() {
                    @Override
                    protected void onFailure(String e) {
                        mView.imageListClose();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<String>> listHttpResult) {
                        mView.referImageList(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }
}
