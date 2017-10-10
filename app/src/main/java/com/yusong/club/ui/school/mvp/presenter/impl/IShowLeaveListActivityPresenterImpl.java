package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.mvp.entity.TeacherLeave;
import com.yusong.club.ui.school.mvp.implView.IShowLeaveListActivityView;
import com.yusong.club.ui.school.mvp.presenter.IShowLeaveListActivityPresenter;
import com.yusong.club.utils.ToastUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian on 2017/4/1.
 */

public class IShowLeaveListActivityPresenterImpl extends BasePresenterImpl<IShowLeaveListActivityView> implements IShowLeaveListActivityPresenter {
    public IShowLeaveListActivityPresenterImpl(IShowLeaveListActivityView view, Context context) {
        super(view, context);
    }

    @Override
    public void queryLeaveApllyByDate(String token, Integer status, String leaveBeginTime, String leaveEndTime, int offset, int limit) {

        Subscription subscription = HttpUtil.getInstance().queryLeaveApllyByDate(token, status, leaveBeginTime, leaveEndTime, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<TeacherLeave>>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.closeRefreshing();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<TeacherLeave>> result) {
                        mView.closeRefreshing();
                        if (result.code == 0 ) {
                            mView.getLeaveParentInfo(result.data);
                        } else {
                            ToastUtils.showShort(mContext, "暂无数据");
                        }
                    }
                });
        addSubcribe(subscription);
    }

}
