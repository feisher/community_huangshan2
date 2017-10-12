package com.yusong.community.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.community.MyApplication;
import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.school.mvp.implView.IFirstItemFragmentView;
import com.yusong.community.ui.school.mvp.presenter.IFirstItemFragmentPresenter;
import com.yusong.community.ui.school.school.bean.StudentComment;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian on 2017/3/21.
 */

public class IFirstItemFragmentPresenterImpl extends BasePresenterImpl<IFirstItemFragmentView> implements IFirstItemFragmentPresenter {
    public IFirstItemFragmentPresenterImpl(IFirstItemFragmentView view, Context context) {
        super(view, context);
    }
    @Override
    public void getStuCommentList(String token,  int period,int offset,int limit) {
        Subscription subscription = HttpUtil.getInstance()
                .searchStuCommentList(token, period,offset,limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<StudentComment>>>(mContext) {
                    @Override
                    protected void onFailure(String err) {
                        mView.closeRefresh();

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<StudentComment>> listHttpResult) {
                        mView.closeRefresh();
                        if (listHttpResult.code == 0 ) {
                            mView.getStuComment((List<StudentComment>) listHttpResult.data);
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
