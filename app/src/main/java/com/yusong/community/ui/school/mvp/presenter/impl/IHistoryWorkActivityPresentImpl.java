package com.yusong.community.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.community.MyApplication;
import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.school.mvp.implView.IHistoryWorkActivityView;
import com.yusong.community.ui.school.mvp.presenter.IHistoryWorkActivityPresent;
import com.yusong.community.ui.school.teacher.bean.ClassHomework;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ds on 2017/3/15.
 */

public class IHistoryWorkActivityPresentImpl extends BasePresenterImpl<IHistoryWorkActivityView> implements IHistoryWorkActivityPresent {
    public IHistoryWorkActivityPresentImpl(IHistoryWorkActivityView view, Context context) {
        super(view, context);
    }

    @Override
    public void searchHistoryHomework(String token, String day) {
        Subscription subscription = HttpUtil.getInstance()
                .searchHistoryHomework(token, day)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<ClassHomework>>>(mContext) {
                    @Override
                    protected void onFailure(String err) {
                      mView.closeRefresh();
                    }
                    @Override
                    protected void onSuccess(HttpResult<List<ClassHomework>> listHttpResult) {
                        mView.closeRefresh();
                        if (listHttpResult.code == 0 ) {
                            mView.getClassHomework(listHttpResult.data);
                        } else {
                            if (!TextUtils.isEmpty(listHttpResult.message)) {
                                MyApplication.showMessage(listHttpResult.message);
                            } else {
                                MyApplication.showMessage("没有更多数据了");
                            }
                        }
                    }
                });
        addSubcribe(subscription);
    }

}
