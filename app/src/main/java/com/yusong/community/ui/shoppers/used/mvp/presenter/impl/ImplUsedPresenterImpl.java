package com.yusong.community.ui.shoppers.used.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.shoppers.bean.FenLeiBean;
import com.yusong.community.ui.shoppers.used.bean.UsedBean;
import com.yusong.community.ui.shoppers.used.mvp.implView.ImplUsedView;
import com.yusong.community.ui.shoppers.used.mvp.presenter.ImplUsedPresenter;
import com.yusong.community.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 *         crated at on 2017/3/18.
 *         描述: 二手分类 列表  请求
 */

public class ImplUsedPresenterImpl extends BasePresenterImpl<ImplUsedView> implements ImplUsedPresenter {

    public ImplUsedPresenterImpl(ImplUsedView view, Context context) {
        super(view, context);
    }

    @Override
    public void queryUsedFenlei() {
        Subscription subscription = HttpUtil.getInstance().queryUsedFenlei(CacheUtils.getToken(mContext))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<FenLeiBean>>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.colse();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<FenLeiBean>> listHttpResult) {
                        mView.queryFenleiSucced(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void queryUsedList(int categoryId, double lng, double lat, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance().queryUsedList(CacheUtils.getToken(mContext), categoryId, lng, lat, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<UsedBean>>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.colse();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<UsedBean>> listHttpResult) {
                        mView.quertListSucced(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }
}
