package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.mvp.implView.IDutyTeacherActivityView;
import com.yusong.club.ui.school.mvp.presenter.IDutyTeacherActivityPresenter;
import com.yusong.club.ui.school.teacher.bean.AllTeacher;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian on 2017/3/8.
 */

public class IDutyTeacherPresenterImpl extends BasePresenterImpl<IDutyTeacherActivityView> implements IDutyTeacherActivityPresenter {
    public IDutyTeacherPresenterImpl(IDutyTeacherActivityView view, Context context) {
        super(view, context);
    }

    @Override
    public void searchTeacherList(String token) {
        Subscription subscription = HttpUtil.getInstance()
                .searchTeacherList(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<AllTeacher>>>(mContext) {
                    @Override
                    protected void onFailure(String err) {
                        mView.closeRefresh();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<AllTeacher>> listHttpResult) {
                        mView.closeRefresh();
                        if (listHttpResult.code == 0 && listHttpResult.data != null) {
                            mView.getAllTeacher((List<AllTeacher>) listHttpResult.data);
                            if (listHttpResult.data.size() == 0) {
                                MyApplication.showMessage("没有数据");
                            }
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

    @Override
    public void setOndutyTeacher(String token, int teacherId) {
        Subscription subscription = HttpUtil.getInstance()
                .setOndutyTeacher(token, teacherId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<String>>(mContext) {
                    @Override
                    protected void onFailure(String err) {
                    }

                    @Override
                    protected void onSuccess(HttpResult<String> listHttpResult) {
                        if (listHttpResult.code == 0) {
                            mView.setOndutyTeacher(listHttpResult.data);
                        } else {
                            if (!TextUtils.isEmpty(listHttpResult.message)) {
                                MyApplication.showMessage(listHttpResult.message);
                            } else {
                                MyApplication.showMessage("设置失败");
                            }
                        }
                    }
                });
        addSubcribe(subscription);


    }
}
