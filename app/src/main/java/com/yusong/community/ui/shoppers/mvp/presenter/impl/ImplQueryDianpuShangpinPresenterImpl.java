package com.yusong.community.ui.shoppers.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.shoppers.bean.CommodityBean;
import com.yusong.community.ui.shoppers.mvp.implView.ImplQueryDianpuShangpinView;
import com.yusong.community.ui.shoppers.mvp.presenter.ImplQueryDianpuShangpinPresenter;
import com.yusong.community.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 查询店铺商品
 */

public class ImplQueryDianpuShangpinPresenterImpl extends BasePresenterImpl<ImplQueryDianpuShangpinView> implements ImplQueryDianpuShangpinPresenter {
    @Override
    public void queryDianPuShangpin(int categoryId, int shopId, String orderBy, int offset, int limit) {

        Subscription subscription = HttpUtil.getInstance().queryDianPuShangPin(CacheUtils.getToken(mContext), categoryId, shopId, orderBy, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<CommodityBean>>>() {
                    @Override
                    protected void onFailure(String e) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<CommodityBean>> listHttpResult) {
                        mView.shangpinSucced(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    public ImplQueryDianpuShangpinPresenterImpl(ImplQueryDianpuShangpinView view, Context context) {
        super(view, context);
    }
}
