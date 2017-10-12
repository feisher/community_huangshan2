package com.yusong.community.ui.repairs.mvp.persenter.impl;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.repairs.RepairsHistoryBean;
import com.yusong.community.ui.repairs.mvp.ImplView.RepairsHistoryView;
import com.yusong.community.ui.repairs.mvp.persenter.RepairsHistoryPresenter;
import com.yusong.community.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 * @created at 2017-09-01.
 * @describe: null
 */

public class RepairsHistoryPresenterImpl extends BasePresenterImpl<RepairsHistoryView> implements RepairsHistoryPresenter {
    @Override
    public void queryRepairsHistory(int proprietorId, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance().queryRepairsHistory(CacheUtils.getToken(mContext), proprietorId, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<RepairsHistoryBean>>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<RepairsHistoryBean>> listHttpResult) {
                        mView.querySucces(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    public RepairsHistoryPresenterImpl(RepairsHistoryView view, Context context) {
        super(view, context);
    }
}
