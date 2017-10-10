package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.mvp.entity.GoodAction;
import com.yusong.club.ui.school.mvp.implView.IActionOneFragmentView;
import com.yusong.club.ui.school.mvp.presenter.IActionOneFragmentPresenter;
import com.yusong.club.ui.school.school.bean.ActivityBean;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian on 2017/3/10.
 */

public class IActionOneFragmentPresenterImpl extends BasePresenterImpl<IActionOneFragmentView> implements IActionOneFragmentPresenter {

    public IActionOneFragmentPresenterImpl(IActionOneFragmentView view, Context context) {
        super(view, context);
    }

    @Override
    public void  activityList(String token,int categoryId,int offset,int limit) {
        Subscription subscription = HttpUtil.getInstance()
                .activityList(token, categoryId,offset,limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<ActivityBean>>>(mContext) {
                    @Override
                    protected void onFailure(String err) {
                        mView.closeRefreshing();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<ActivityBean>> listHttpResult) {
                        mView.closeRefreshing();
                        if (listHttpResult.code == 0  ) {
                            mView.getactivityList((List<ActivityBean>) listHttpResult.data);
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
    public void getGoodAction(String token, int activityId) {
        Subscription subscription = HttpUtil.getInstance()
                .getGoodAction(token,activityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<GoodAction>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }
                    @Override
                    protected void onSuccess(HttpResult<GoodAction> listHttpResult) {
                        if (listHttpResult.code == 0) {
                            mView.getGoodAction( listHttpResult.data);
                        } else {
                            if (!TextUtils.isEmpty(listHttpResult.message)) {
                                MyApplication.showMessage(listHttpResult.message);
                            }
                        }
                    }
                });
        addSubcribe(subscription);




    }
}
