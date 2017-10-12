package com.yusong.community.ui.evaluate.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.evaluate.EvaluateBean;
import com.yusong.community.ui.evaluate.mvp.implview.EvaluateComentView;
import com.yusong.community.ui.evaluate.mvp.presenter.EvaluateComentPresenter;
import com.yusong.community.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 * @created at 2017-09-02.
 * @describe: null
 */

public class EvaluateComentPresenterImpl extends BasePresenterImpl<EvaluateComentView> implements EvaluateComentPresenter {
    public EvaluateComentPresenterImpl(EvaluateComentView view, Context context) {
        super(view, context);
    }

    @Override
    public void queryEvaluateComent(int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance()
                .queryEvaluateHistory(CacheUtils.getToken(mContext), offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<EvaluateBean>>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.queryError();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<EvaluateBean>> listHttpResult) {
                        mView.querySucces(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }
}
