package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.mvp.entity.Notice;
import com.yusong.club.ui.school.mvp.entity.SchoolIntro;
import com.yusong.club.ui.school.mvp.implView.IFirstFragmentView;
import com.yusong.club.ui.school.mvp.presenter.IFirstFragmentPresenter;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by feisher on 2017/2/24.
 */

public class FirstFragmentPresenterImpl extends BasePresenterImpl<IFirstFragmentView> implements IFirstFragmentPresenter{

    public FirstFragmentPresenterImpl(IFirstFragmentView view, Context context) {
        super(view, context);
    }
    @Override
    public void queryBannerList(String token) {
        Subscription subscription = HttpUtil.getInstance()
                .bannerList(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<String>>>() {
                    @Override
                    protected void onFailure(String err) {
                    }
                    @Override
                    protected void onSuccess(HttpResult<List<String>> stringHttpResult) {
                        if (stringHttpResult.code==0&&stringHttpResult.data!=null) {
                            mView.setBannerAdapter((List<String>) stringHttpResult.data);
                        }else {
                            if (!TextUtils.isEmpty(stringHttpResult.message)) {
                                MyApplication.showMessage(stringHttpResult.message);
                            }
                        }
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void queryPublicNoticeList(String token, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance()
                .public_noticeList(token,offset,limit)
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
                        if (listHttpResult.code==0&&listHttpResult.data!=null) {
                            mView.schoolIntroCallback((SchoolIntro) listHttpResult.data);
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
