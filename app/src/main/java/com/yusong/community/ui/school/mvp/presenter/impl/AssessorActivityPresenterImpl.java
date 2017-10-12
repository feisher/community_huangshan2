package com.yusong.community.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.community.MyApplication;
import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.school.mvp.entity.ApplyRole;
import com.yusong.community.ui.school.mvp.implView.AssessorActivityView;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * create by feisher on 2017/3/9
 * Email：458079442@qq.com
 */
public class AssessorActivityPresenterImpl extends BasePresenterImpl<AssessorActivityView> {

    public AssessorActivityPresenterImpl(AssessorActivityView v, Context mContext) {
        super(v, mContext);
    }

    public void queryRoleApplyList(String token,int offset,int limit){
        Subscription subscription = HttpUtil.getInstance()
                .queryRoleApplyList(token,offset,limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<ApplyRole>>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.closeRefreshing();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<ApplyRole>> listHttpResult) {
                        mView.closeRefreshing();
                        if (listHttpResult.code==0&&listHttpResult.data!=null) {
                            mView.queryRoleApplyListCallback((List<ApplyRole>) listHttpResult.data);
                        }else {
                            if (!TextUtils.isEmpty(listHttpResult.message)) {
                                MyApplication.showMessage(listHttpResult.message);
                            }
                        }
                    }
                });
        addSubcribe(subscription);
    }


}