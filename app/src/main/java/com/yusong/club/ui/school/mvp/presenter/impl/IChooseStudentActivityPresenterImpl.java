package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.mvp.entity.StuList;
import com.yusong.club.ui.school.mvp.implView.IChooseStudentActivityView;
import com.yusong.club.ui.school.mvp.presenter.IChooseStudentActivityPresenter;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian on 2017/3/18.
 */

public class IChooseStudentActivityPresenterImpl extends BasePresenterImpl<IChooseStudentActivityView> implements IChooseStudentActivityPresenter {
    public IChooseStudentActivityPresenterImpl(IChooseStudentActivityView view, Context context) {
        super(view, context);
    }

    @Override
    public void ueryStuList(String token, String studentName, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance()
                .queryStuList(token, studentName, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<StuList>>>(mContext) {
                    @Override
                    protected void onFailure(String err) {
                        mView.closeRefresh();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<StuList>> listHttpResult) {
                        mView.closeRefresh();
                        if (listHttpResult.code == 0 ) {
                            mView.getStuList((List<StuList>) listHttpResult.data);
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
