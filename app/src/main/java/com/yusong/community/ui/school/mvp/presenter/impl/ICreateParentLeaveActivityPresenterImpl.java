package com.yusong.community.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.community.MyApplication;
import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.school.mvp.implView.ICreateParentLeaveActivityView;
import com.yusong.community.ui.school.mvp.presenter.ICreateParentLeaveActivityPresenter;
import com.yusong.community.utils.DateUtil;
import com.yusong.community.utils.ToastUtils;

import org.apache.commons.lang.StringUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian on 2017/3/30.
 */

public class ICreateParentLeaveActivityPresenterImpl extends BasePresenterImpl<ICreateParentLeaveActivityView> implements ICreateParentLeaveActivityPresenter {
    public ICreateParentLeaveActivityPresenterImpl(ICreateParentLeaveActivityView view, Context context) {
        super(view, context);
    }

    @Override
    public void CreateLeaveApply(String token, String studentName, String reason, String beginTime, String endTime) {
        if (StringUtils.isEmpty(studentName)) {
            ToastUtils.showShort(mContext, "学生没有选择");
            return;
        }
        if (StringUtils.isEmpty(reason)) {
            ToastUtils.showShort(mContext, "请假理由不能为空");
            return;
        }
        if (StringUtils.isEmpty(beginTime)) {
            ToastUtils.showShort(mContext, "请假开始不能为空");
            return;
        }
        if (StringUtils.isEmpty(endTime)) {
            ToastUtils.showShort(mContext, "请假时间结束时间不能为空");
            return;
        }
        if (DateUtil.compareDate(beginTime, endTime)) {
            MyApplication.showMessage("结束时间不能小于开始时间");
            return;
        }
        endTime=endTime+" 23:59:59";
        Subscription subscription = HttpUtil.getInstance()
                .CreateLeaveApply(token, studentName, reason, beginTime, endTime)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<String>>(mContext) {
                    @Override
                    protected void onFailure(String err) {
                    }

                    @Override
                    protected void onSuccess(HttpResult<String> listHttpResult) {
                        if (listHttpResult.code == 0) {
                            mView.createParentLeaveSucess(listHttpResult.data);
                        } else {
                            if (!TextUtils.isEmpty(listHttpResult.message)) {
                                MyApplication.showMessage(listHttpResult.message);
                            } else {
                                MyApplication.showMessage("创建失败");
                            }
                        }
                    }
                });
        addSubcribe(subscription);


    }
}
