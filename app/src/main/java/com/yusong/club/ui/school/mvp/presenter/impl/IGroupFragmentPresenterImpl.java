package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.mvp.implView.IGroupFragmentView;
import com.yusong.club.ui.school.mvp.presenter.IGroupFragmentPresenter;
import com.yusong.club.ui.school.teacher.bean.ChatGroup;
import com.yusong.club.ui.school.teacher.bean.MemberGroup;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ds on 2017/3/13.
 */

public class IGroupFragmentPresenterImpl extends BasePresenterImpl<IGroupFragmentView> implements IGroupFragmentPresenter {
    public IGroupFragmentPresenterImpl(IGroupFragmentView view, Context context) {
        super(view, context);
    }

    @Override
    public void chatGroupList(String token) {
        Subscription subscription = HttpUtil.getInstance()
                .chatGroupList(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<ChatGroup>>(mContext) {
                    @Override
                    protected void onFailure(String err) {
                    }
                    @Override
                    protected void onSuccess(HttpResult<ChatGroup> listHttpResult) {
                        if (listHttpResult.code == 0 && listHttpResult.data != null) {
                            mView.getchatGroupList(listHttpResult.data);
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
    public void memberGroupList(String token, int groupId) {
        Subscription subscription = HttpUtil.getInstance()
                .memberGroupList(token, groupId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<MemberGroup>>>(mContext) {
                    @Override
                    protected void onFailure(String err) {
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<MemberGroup>> listHttpResult) {
                        if (listHttpResult.code == 0 && listHttpResult.data != null) {
                            mView.getMemberList(listHttpResult.data);
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
