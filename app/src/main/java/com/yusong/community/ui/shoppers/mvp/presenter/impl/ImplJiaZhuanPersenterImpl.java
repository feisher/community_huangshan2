package com.yusong.community.ui.shoppers.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.shoppers.bean.CommodityBean;
import com.yusong.community.ui.shoppers.bean.FenLeiBean;
import com.yusong.community.ui.shoppers.mvp.implView.ImplHomeView;
import com.yusong.community.ui.shoppers.mvp.presenter.ImplJiaZhuanPersenter;
import com.yusong.community.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 *         created at 2017/3/15 16:44.
 *         家装
 */

public class ImplJiaZhuanPersenterImpl extends BasePresenterImpl<ImplHomeView> implements ImplJiaZhuanPersenter {

    public ImplJiaZhuanPersenterImpl(ImplHomeView view, Context context) {
        super(view, context);
    }

    @Override
    public void queryHomeFenlei(int categoryType) {//查询分类
        Subscription subscription = HttpUtil.getInstance().queryHomeFeilei(CacheUtils.getToken(mContext), categoryType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<FenLeiBean>>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.close();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<FenLeiBean>> listHttpResult) {
                        mView.HomeFlSucced(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }


    @Override
    public void queryHomeList(int categoryId, int offset, int limit) {//查询列表
        Subscription subscription = HttpUtil.getInstance().queryHomeList(CacheUtils.getToken(mContext), categoryId, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<CommodityBean>>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.close();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<CommodityBean>> listHttpResult) {
                        mView.HomeListSucced(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }
}
