package com.yusong.community.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.community.MyApplication;
import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.school.mvp.entity.ReadCount;
import com.yusong.community.ui.school.mvp.implView.NoticeDetailActivityView;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * create by feisher on 2017/3/14
 * Email：458079442@qq.com
 */
public class NoticeDetailActivityPresenterImpl extends BasePresenterImpl<NoticeDetailActivityView> {

    public NoticeDetailActivityPresenterImpl(NoticeDetailActivityView v, Context mContext) {
        super(v, mContext);
    }

    public void commit4AddReadCount(String token, int noticeId) {
        Subscription subscription = HttpUtil.getInstance()
                .addReadCount(token,noticeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<ReadCount>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<ReadCount> listHttpResult) {
                        if (listHttpResult.code==0) {
                            mView.noticeReadedCallback();
//                            MyApplication.showMessage("阅读次数+1");
                        }else {
                            if (!TextUtils.isEmpty(listHttpResult.message)) {
                                MyApplication.showMessage(listHttpResult.message);
                            }
                        }
                    }
                });
        addSubcribe(subscription);
    }

}