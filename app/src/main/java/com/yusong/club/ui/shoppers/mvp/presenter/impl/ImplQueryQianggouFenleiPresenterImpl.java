package com.yusong.club.ui.shoppers.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.shoppers.bean.QiangGouDaleiBean;
import com.yusong.club.ui.shoppers.mvp.implView.ImplQueryQianggouFenleiView;
import com.yusong.club.ui.shoppers.mvp.presenter.ImplQueryQianggouFenleiPresenter;
import com.yusong.club.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 查询抢购分类
 */

public class ImplQueryQianggouFenleiPresenterImpl extends BasePresenterImpl<ImplQueryQianggouFenleiView> implements ImplQueryQianggouFenleiPresenter {
    public ImplQueryQianggouFenleiPresenterImpl(ImplQueryQianggouFenleiView view, Context context) {
        super(view, context);
    }

    @Override
    public void queryQianggouFenlei(int categoryId) {
        Subscription subscription = HttpUtil.getInstance().queryQiangGouFenLei(CacheUtils.getToken(mContext), categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<QiangGouDaleiBean>>>() {
                    @Override
                    protected void onFailure(String e) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<QiangGouDaleiBean>> listHttpResult) {
                        mView.refreshQianggoufenlei(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }
}
