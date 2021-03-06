package com.yusong.community.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.community.MyApplication;
import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.school.mvp.implView.AddNoticeActivityView;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * create by feisher on 2017/3/7
 * Email：458079442@qq.com
 */
public class AddNoticeActivityPresenterImpl extends BasePresenterImpl<AddNoticeActivityView> {

    public AddNoticeActivityPresenterImpl(AddNoticeActivityView v, Context mContext) {
        super(v, mContext);
    }

    public void createNotice( String token,String title, String content,int noticeType,int publishRange){
        Subscription subscription = HttpUtil.getInstance()
                .addNotice(token,title,content,noticeType,publishRange)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<String>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<String> listHttpResult) {
                        if (listHttpResult.code==0) {
                            MyApplication.showMessage("发布成功");
                            mView.addNoticeCallback();
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