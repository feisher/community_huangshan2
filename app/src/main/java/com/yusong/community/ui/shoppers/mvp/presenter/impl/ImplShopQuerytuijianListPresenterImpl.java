package com.yusong.community.ui.shoppers.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.shoppers.bean.TuiJianBean;
import com.yusong.community.ui.shoppers.mvp.implView.ImplQuerytuijianListView;
import com.yusong.community.ui.shoppers.mvp.presenter.ImplQuerytuijianListPresenter;
import com.yusong.community.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 *         created at 2017/3/6 15:22.
 *         推荐商品列表
 */

public class ImplShopQuerytuijianListPresenterImpl extends BasePresenterImpl<ImplQuerytuijianListView> implements ImplQuerytuijianListPresenter {

    public ImplShopQuerytuijianListPresenterImpl(ImplQuerytuijianListView view, Context context) {
        super(view, context);
    }

    @Override
    public void queryTuijianList(int categoryId, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance().queryTuijianList(CacheUtils.getToken(mContext), categoryId, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<TuiJianBean.Commodity>>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.refreshuituijianClose();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<TuiJianBean.Commodity>> listHttpResult) {
                        mView.refreshTuiJianList(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }
}
