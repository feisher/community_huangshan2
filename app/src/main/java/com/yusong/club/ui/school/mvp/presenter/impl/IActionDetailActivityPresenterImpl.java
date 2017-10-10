package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.mvp.entity.ActionDetail;
import com.yusong.club.ui.school.mvp.entity.DetailComment;
import com.yusong.club.ui.school.mvp.entity.JoinedResult;
import com.yusong.club.ui.school.mvp.implView.IActionDetailActivityView;
import com.yusong.club.ui.school.mvp.presenter.IActionDetailActivityPresenter;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian on 2017/3/25.
 */

public class IActionDetailActivityPresenterImpl extends BasePresenterImpl<IActionDetailActivityView> implements IActionDetailActivityPresenter {
    public IActionDetailActivityPresenterImpl(IActionDetailActivityView view, Context context) {
        super(view, context);
    }

    @Override
    public void getActionDeatail(String token, int activityId) {
        Subscription subscription = HttpUtil.getInstance()
                .getActionDeatail(token, activityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<ActionDetail>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<ActionDetail> listHttpResult) {
                        if (listHttpResult.code == 0 && listHttpResult.data != null) {
                            mView.getActionDetail(listHttpResult.data);
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
    public void getDetailComment(String token, int activityId, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance()
                .getDetailComment(token, activityId, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<DetailComment>>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<DetailComment>> listHttpResult) {
                        if (listHttpResult.code == 0) {
                            mView.getDetailCommentList(listHttpResult.data);
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
    public void joinActy(String token, int activityId) {
        Subscription subscription = HttpUtil.getInstance()
                .joinActy(token, activityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<JoinedResult>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }
                    @Override
                    protected void onSuccess(HttpResult<JoinedResult> listHttpResult) {
                        if (listHttpResult.code == 0) {
                            mView.partAction(listHttpResult.data);
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
