package com.yusong.community.ui.shoppers.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.shoppers.bean.QiangGouBean;
import com.yusong.community.ui.shoppers.mvp.implView.ImplQueryQiangouLeiView;
import com.yusong.community.ui.shoppers.mvp.presenter.IShopQueryQianggouLeiPresenter;
import com.yusong.community.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 查询抢购大类
 */

public class IShopQueryQianggouLeiPresenterImpl extends BasePresenterImpl<ImplQueryQiangouLeiView> implements IShopQueryQianggouLeiPresenter {
    public IShopQueryQianggouLeiPresenterImpl(ImplQueryQiangouLeiView view, Context context) {
        super(view, context);
    }

    @Override
    public void queryQianggouDalei() {
        Subscription subscription = HttpUtil.getInstance().queryQiangGou(CacheUtils.getToken(mContext))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<QiangGouBean>>>() {
                    @Override
                    protected void onFailure(String e) {
                        mView.leiClose();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<QiangGouBean>> listHttpResult) {
                        mView.refreshQianggou(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }
}
