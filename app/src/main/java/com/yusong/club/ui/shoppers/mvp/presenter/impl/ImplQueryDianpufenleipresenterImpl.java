package com.yusong.club.ui.shoppers.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.shoppers.bean.FenLeiBean;
import com.yusong.club.ui.shoppers.bean.ShopDetailsBean;
import com.yusong.club.ui.shoppers.mvp.implView.ImplQueryDianpufenleiView;
import com.yusong.club.ui.shoppers.mvp.presenter.ImplQueryDianpufenleipresenter;
import com.yusong.club.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 查询店铺商品分类
 */

public class ImplQueryDianpufenleipresenterImpl extends BasePresenterImpl<ImplQueryDianpufenleiView> implements ImplQueryDianpufenleipresenter {
    @Override
    public void queryDianpufenlei(int shopId) {
        Subscription subscription = HttpUtil.getInstance().queryDianPuShangPinFenLei(CacheUtils.getToken(mContext), shopId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<FenLeiBean>>>() {
                    @Override
                    protected void onFailure(String e) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<FenLeiBean>> listHttpResult) {
                        mView.dianpuFenleiSucced(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void queryDianpuDetails(int shopId) {
        Subscription subscription = HttpUtil.getInstance().queryShopDetails(CacheUtils.getToken(mContext), shopId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<ShopDetailsBean>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<ShopDetailsBean> shopDetailsBeanHttpResult) {
                        mView.dianpuDetailsSucced(shopDetailsBeanHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    public ImplQueryDianpufenleipresenterImpl(ImplQueryDianpufenleiView view, Context context) {
        super(view, context);
    }
}
