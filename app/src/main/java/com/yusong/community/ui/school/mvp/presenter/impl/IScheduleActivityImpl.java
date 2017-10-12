package com.yusong.community.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.community.MyApplication;
import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.school.mvp.implView.IScheduleActivityView;
import com.yusong.community.ui.school.mvp.presenter.IScheduleActivityPresenter;
import com.yusong.community.ui.school.teacher.bean.SubjectTable;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian on 2017/3/8.
 */

public class IScheduleActivityImpl extends BasePresenterImpl<IScheduleActivityView> implements IScheduleActivityPresenter {
    public IScheduleActivityImpl(IScheduleActivityView view, Context context) {
        super(view, context);
    }


    @Override
    public void timeTableList(String token) {
        Subscription subscription = HttpUtil.getInstance()
                                            .timeTableList(token)
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(new BaseSubscriber<HttpResult<List<SubjectTable>>>(mContext) {
                                                @Override
                                                protected void onFailure(String err) {


                                                }

                                                @Override
                                                protected void onSuccess(HttpResult<List<SubjectTable>> listHttpResult) {
                                                    if (listHttpResult.code == 0 ) {
                                                        mView.getAllTable((List<SubjectTable>) listHttpResult.data);

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
