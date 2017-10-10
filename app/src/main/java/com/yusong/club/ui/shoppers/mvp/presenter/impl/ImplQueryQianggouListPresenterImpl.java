package com.yusong.club.ui.shoppers.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.shoppers.bean.CommodityBean;
import com.yusong.club.ui.shoppers.mvp.implView.ImplQueryQianggouListView;
import com.yusong.club.ui.shoppers.mvp.presenter.ImplQueryQianggouListPresenter;
import com.yusong.club.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 查询抢购商品列表
 */

public class ImplQueryQianggouListPresenterImpl extends BasePresenterImpl<ImplQueryQianggouListView> implements ImplQueryQianggouListPresenter {
    public ImplQueryQianggouListPresenterImpl(ImplQueryQianggouListView view, Context context) {
        super(view, context);
    }

    @Override
    public void queryQianggouList(int grabCategoryId, int itemCategoryId, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance().queryQiangGouLieBiao(CacheUtils.getToken(mContext), grabCategoryId,itemCategoryId, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<CommodityBean>>>() {
                    @Override
                    protected void onFailure(String e) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<CommodityBean>> listHttpResult) {
                        mView.refreshQiangGouList(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }
}
