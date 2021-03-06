package com.yusong.community.ui.shoppers.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.shoppers.bean.TuiJianBean;
import com.yusong.community.ui.shoppers.mvp.implView.ImplQueryTuijianLeiView;
import com.yusong.community.ui.shoppers.mvp.presenter.IShopQueryTuijianPresenter;
import com.yusong.community.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 查询推荐商品分类
 */

public class IShopQueryTuijianPresenterImpl extends BasePresenterImpl<ImplQueryTuijianLeiView> implements IShopQueryTuijianPresenter {
    @Override
    public void queryTuijianLei(int type) {
        Subscription subscription = HttpUtil.getInstance().queryTuiJian(CacheUtils.getToken(mContext), type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<TuiJianBean>>>() {
                    @Override
                    protected void onFailure(String e) {
                        mView.tuijianClose();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<TuiJianBean>> listHttpResult) {
                        mView.refreshTuijian(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    public IShopQueryTuijianPresenterImpl(ImplQueryTuijianLeiView view, Context context) {
        super(view, context);
    }
}
