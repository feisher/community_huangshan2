package com.yusong.club.ui.shoppers.used.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.shoppers.used.bean.MyUsedBean;
import com.yusong.club.ui.shoppers.used.mvp.implView.ImplMyUsedView;
import com.yusong.club.ui.shoppers.used.mvp.presenter.ImplMyUsedPersenter;
import com.yusong.club.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 *         crated at on 2017/3/18.
 *         描述: 我的二手
 */

public class ImplMyUsedPersenterImpl extends BasePresenterImpl<ImplMyUsedView> implements ImplMyUsedPersenter {
    public ImplMyUsedPersenterImpl(ImplMyUsedView view, Context context) {
        super(view, context);
    }

    @Override
    public void queryMyUsed(int offset, int limit) {//查询列表
        Subscription subscription = HttpUtil.getInstance().queryMyUsed(CacheUtils.getToken(mContext), offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<MyUsedBean>>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.close();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<MyUsedBean>> listHttpResult) {
                        mView.myUsedSucced(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void outUsed(int id) {//下架
        Subscription subscription = HttpUtil.getInstance().outUsed(CacheUtils.getToken(mContext), id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.close();
                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.outSucced();
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void deleteUsed(int id) {//删除
        Subscription subscription = HttpUtil.getInstance().deleteUsed(CacheUtils.getToken(mContext), id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.close();
                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.deleteSucced();
                    }
                });
        addSubcribe(subscription);
    }
}
