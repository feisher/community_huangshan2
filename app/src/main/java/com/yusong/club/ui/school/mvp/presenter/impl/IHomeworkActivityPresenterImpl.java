package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.mvp.implView.IHomeworkActivityView;
import com.yusong.club.ui.school.mvp.presenter.IHomeworkActivityPresenter;
import com.yusong.club.ui.school.teacher.bean.ClassHomework;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian on 2017/3/10.
 */

public class IHomeworkActivityPresenterImpl extends BasePresenterImpl<IHomeworkActivityView> implements IHomeworkActivityPresenter {
    public IHomeworkActivityPresenterImpl(IHomeworkActivityView view, Context context) {
        super(view, context);
    }

    @Override
    public void searchClassHomework(String token, String day) {
        Subscription subscription = HttpUtil.getInstance()
                .searchClassHomework(token, day)
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
                            mView.getClassHomework((List<ClassHomework>) listHttpResult.data);
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
