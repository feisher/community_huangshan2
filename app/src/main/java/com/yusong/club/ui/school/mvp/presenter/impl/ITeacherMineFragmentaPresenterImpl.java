package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.mvp.entity.TeacherInfo;
import com.yusong.club.ui.school.mvp.implView.ITeacherMineFragmentaView;
import com.yusong.club.ui.school.mvp.presenter.ITeacherMineFragmentaPresenter;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian on 2017/3/28.
 */

public class ITeacherMineFragmentaPresenterImpl extends BasePresenterImpl<ITeacherMineFragmentaView> implements ITeacherMineFragmentaPresenter {

    public ITeacherMineFragmentaPresenterImpl(ITeacherMineFragmentaView view, Context context) {
        super(view, context);
    }

    @Override
    public void queryTeacherInfo(String token, Integer roleId) {
        Subscription subscription = HttpUtil.getInstance()
                .queryTeacherInfo(token, roleId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<TeacherInfo>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<TeacherInfo> listHttpResult) {
                        if (listHttpResult.code == 0 && listHttpResult.data != null) {
                            mView.getTeacherIn(listHttpResult.data);
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
