package com.yusong.club.ui.shoppers.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.shoppers.bean.PinLunBean;
import com.yusong.club.ui.shoppers.mvp.implView.ImplQueryShangPinPinLunView;
import com.yusong.club.ui.shoppers.mvp.presenter.ImplQueryShangPinPinLunPresenter;
import com.yusong.club.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_Peng on 2017/3/1.
 * 查询商品评论
 */

public class ImplQueryShangPinPinLunPresenterImpl extends BasePresenterImpl<ImplQueryShangPinPinLunView>implements ImplQueryShangPinPinLunPresenter {
    @Override
    public void queryShangPinPinLun(int itemId, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance().queryShangPinPinLun(CacheUtils.getToken(mContext),itemId,offset,limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<PinLunBean>>>() {
                    @Override
                    protected void onFailure(String e) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<PinLunBean>> listHttpResult) {
                        mView.refreshPinlun(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    public ImplQueryShangPinPinLunPresenterImpl(ImplQueryShangPinPinLunView view, Context context) {
        super(view, context);
    }
}
