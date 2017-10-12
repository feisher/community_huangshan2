package com.yusong.community.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.community.MyApplication;
import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.school.mvp.implView.RoleApplyDetailActivityView;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * create by feisher on 2017/3/9
 * Email：458079442@qq.com
 */
public class RoleApplyDetailActivityPresenterImpl extends BasePresenterImpl<RoleApplyDetailActivityView> {

    public RoleApplyDetailActivityPresenterImpl(RoleApplyDetailActivityView v, Context mContext) {
        super(v, mContext);
    }

    /**
     * @param token
     * @param applyId int 审批id
     * @param auditMemo 审核说明
     * @param status 1 待审核 2审核通过 3 审核不通过
     */
    public void auditRoleApply(String token,int applyId,String auditMemo,int status){
        Subscription subscription = HttpUtil.getInstance()
                .auditRoleApply(token,applyId,auditMemo,status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<String>>(mContext) {
                    @Override
                    protected void onFailure(String err) {
                    }
                    @Override
                    protected void onSuccess(HttpResult<String> listHttpResult) {

                        if (listHttpResult.code==0) {
                            MyApplication.showMessage("审批成功");
                            mView.auditRoleApplyCallback();
                        }else {
                            if (!TextUtils.isEmpty(listHttpResult.message)) {
                                MyApplication.showMessage(listHttpResult.message);
                            }else {
                                MyApplication.showMessage("审批失败");
                            }
                        }
                    }
                });
        addSubcribe(subscription);
    }


}