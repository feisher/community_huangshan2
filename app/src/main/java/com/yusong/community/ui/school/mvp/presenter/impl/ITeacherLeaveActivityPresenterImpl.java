package com.yusong.community.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.community.MyApplication;
import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.school.mvp.entity.TeacherLeave;
import com.yusong.community.ui.school.mvp.implView.ITeacherLeaveActivityView;
import com.yusong.community.ui.school.mvp.presenter.ITeacherLeaveActivityPresenter;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian on 2017/3/27.
 */

public class ITeacherLeaveActivityPresenterImpl extends BasePresenterImpl<ITeacherLeaveActivityView> implements ITeacherLeaveActivityPresenter {
    public ITeacherLeaveActivityPresenterImpl(ITeacherLeaveActivityView view, Context context) {
        super(view, context);
    }

    @Override
    public void queryLeaveAplly(String token, Integer status, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance()
                .queryLeaveAplly(token, status, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<TeacherLeave>>>(mContext) {
                    @Override
                    protected void onFailure(String err) {
                        mView.closeRefresh();

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<TeacherLeave>> listHttpResult) {
                        mView.closeRefresh();
                        if (listHttpResult.code == 0 ) {
                            mView.getTeacherLeave(listHttpResult.data);
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
    public void judgeLeave(String token, int applyId, String auditMemo, int status) {
        Subscription subscription = HttpUtil.getInstance()
                .judgeLeave(token, applyId, auditMemo, status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<String>>(mContext) {
                    @Override
                    protected void onFailure(String err) {


                    }
                    @Override
                    protected void onSuccess(HttpResult<String> listHttpResult) {
                        if (listHttpResult.code == 0) {
                            mView.judgeServeLeave(listHttpResult.data);
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
