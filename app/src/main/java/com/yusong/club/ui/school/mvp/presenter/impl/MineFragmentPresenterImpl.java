package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.mvp.entity.SchoolManager;
import com.yusong.club.ui.school.mvp.implView.IMineFragmentView;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * create by feisher on 2017/3/16
 * Email：458079442@qq.com
 */
public class MineFragmentPresenterImpl extends BasePresenterImpl<IMineFragmentView> {

    public MineFragmentPresenterImpl(IMineFragmentView v, Context mContext) {
        super(v, mContext);
    }
    public void querySchoolManagerInfo(String token,Integer roleId){
        Subscription subscription = HttpUtil.getInstance()
                .querySchoolManagerInfo(token,roleId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<SchoolManager>>(mContext) {
                    @Override
                    protected void onFailure(String err) {}

                    @Override
                    protected void onSuccess(HttpResult<SchoolManager> HttpResult) {
                        if (HttpResult.code==0&&HttpResult.data!=null) {
                            mView.querySchoolManagerInfoCallback(HttpResult.data);
                        }else {
                            if (!TextUtils.isEmpty(HttpResult.message)) {
                                MyApplication.showMessage(HttpResult.message);
                            }else {
                                MyApplication.showMessage("获取数据失败！");
                            }
                        }
                    }
                });
        addSubcribe(subscription);

    }
}