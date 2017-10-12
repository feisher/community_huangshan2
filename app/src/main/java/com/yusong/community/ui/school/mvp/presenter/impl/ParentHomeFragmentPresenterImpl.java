package com.yusong.community.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.community.MyApplication;
import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.school.mvp.entity.Notice;
import com.yusong.community.ui.school.mvp.entity.SchoolIntro;
import com.yusong.community.ui.school.mvp.implView.IParentHomeFragmentView;
import com.yusong.community.ui.school.mvp.presenter.IParentHomeFragmentPresenter;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by feisher on 2017/2/24.
 */

public class ParentHomeFragmentPresenterImpl extends BasePresenterImpl<IParentHomeFragmentView> implements IParentHomeFragmentPresenter {

    public ParentHomeFragmentPresenterImpl(IParentHomeFragmentView view, Context context) {
        super(view, context);
    }

    @Override
    public void queryGuardianNoticeList(String token, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance()
                .guardianNoticeList(token,offset,limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<Notice>>>() {
                    @Override
                    protected void onFailure(String err) {

                    }
                    @Override
                    protected void onSuccess(HttpResult<List<Notice>> listHttpResult) {
                        if (listHttpResult.code==0&&listHttpResult.data!=null) {
                            mView.noticeDataCallback((List<Notice>) listHttpResult.data);
                        }else {
                            if (!TextUtils.isEmpty(listHttpResult.message)) {
                                MyApplication.showMessage(listHttpResult.message);
                            }
                        }
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void querySchoolIntro(String token) {
        Subscription subscription = HttpUtil.getInstance()
                .querySchoolIntro(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<SchoolIntro>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<SchoolIntro> listHttpResult) {
                        if (listHttpResult.code == 0 && listHttpResult.data != null) {
                            mView.querySchoolIntro(listHttpResult.data);
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


