package com.yusong.community.ui.shoppers.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.shoppers.bean.CommodityBean;
import com.yusong.community.ui.shoppers.mvp.implView.ImplFindView;
import com.yusong.community.ui.shoppers.mvp.presenter.ImplFindCommodityPresenter;
import com.yusong.community.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 * @created at 2017-09-18.
 * @describe: null
 */

public class ImplFindCommodityPresenterImpl extends BasePresenterImpl<ImplFindView> implements ImplFindCommodityPresenter {
    public ImplFindCommodityPresenterImpl(ImplFindView view, Context context) {
        super(view, context);
    }

    @Override
    public void findCommodity(String content) {
        Subscription subscription = HttpUtil.getInstance()
                .findCommodity(CacheUtils.getToken(mContext), content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<CommodityBean>>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.findCommodityError();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<CommodityBean>> commodityBeanHttpResult) {
                        mView.findCommoditySucced(commodityBeanHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void findSMCommodity(String content) {
        Subscription subscription = HttpUtil.getInstance()
                .findSMCommodity(CacheUtils.getToken(mContext), content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<CommodityBean>>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.findSMCommodityError();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<CommodityBean>> commodityBeanHttpResult) {
                        mView.findSMCommoditySucced(commodityBeanHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void findService(String content) {
        Subscription subscription = HttpUtil.getInstance()
                .findService(CacheUtils.getToken(mContext), content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<CommodityBean>>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.findSMCommodityError();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<CommodityBean>> commodityBeanHttpResult) {
                        mView.findSMCommoditySucced(commodityBeanHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }
}
