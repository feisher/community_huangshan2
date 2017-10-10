package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.teacher.bean.AheadBean;
import com.yusong.club.ui.school.mvp.implView.IQueryAheadView;
import com.yusong.club.ui.school.mvp.presenter.IQueryAheadPresenter;
import com.yusong.club.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 *         crated at on 2017/3/31.
 *         描述: 查询提前放学
 */

public class IQueryAheadPresenterImpl extends BasePresenterImpl<IQueryAheadView> implements IQueryAheadPresenter {
    public IQueryAheadPresenterImpl(IQueryAheadView view, Context context) {
        super(view, context);
    }

    @Override
    public void queryAhead() {
        Subscription subscription = HttpUtil.getInstance()
                .QueryAheadAfterSchool(CacheUtils.getToken(mContext))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<AheadBean>>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<AheadBean>> listHttpResult) {
                        mView.queryAheadSucced(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }
}
