package com.yusong.club.ui.shoppers.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.shoppers.bean.CommodityBean;
import com.yusong.club.ui.shoppers.bean.SpecificationBean;
import com.yusong.club.ui.shoppers.mvp.implView.ImplQueryShangpingDetailsView;
import com.yusong.club.ui.shoppers.mvp.presenter.ImplQueryShangpingDetailsPresenter;
import com.yusong.club.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 查询商品详情
 */

public class ImplQueryShangpingDetailsPresenterImpl extends BasePresenterImpl<ImplQueryShangpingDetailsView> implements ImplQueryShangpingDetailsPresenter {
    @Override
    public void queryShangpinDetails(int itemId) {
        Subscription subscription = HttpUtil.getInstance().queryShangPinDetails(CacheUtils.getToken(mContext),itemId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<CommodityBean>>() {
                    @Override
                    protected void onFailure(String e) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<CommodityBean> commodityBeanHttpResult) {
                        mView.refreshCommundityDetails(commodityBeanHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void querySpecification(int itemId) {
        Subscription subscription = HttpUtil.getInstance().queryItemSpecification(CacheUtils.getToken(mContext),itemId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<SpecificationBean>>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<SpecificationBean>> listHttpResult) {
                        mView.querySpecificationSuccess(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    public ImplQueryShangpingDetailsPresenterImpl(ImplQueryShangpingDetailsView view, Context context) {
        super(view, context);
    }
}
