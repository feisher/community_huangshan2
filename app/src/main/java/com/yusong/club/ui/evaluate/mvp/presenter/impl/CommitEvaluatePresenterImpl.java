package com.yusong.club.ui.evaluate.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.evaluate.mvp.implview.CommitEvaluateView;
import com.yusong.club.ui.evaluate.mvp.presenter.CommitEvaluatePresenter;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 * @created at 2017-09-02.
 * @describe: null
 */

public class CommitEvaluatePresenterImpl extends BasePresenterImpl<CommitEvaluateView> implements CommitEvaluatePresenter {
    public CommitEvaluatePresenterImpl(CommitEvaluateView view, Context context) {
        super(view, context);
    }

    @Override
    public void commitEvaluate(RequestBody token, RequestBody categoryType, RequestBody proprietorId, RequestBody proprietorName, RequestBody proprietorMobile, RequestBody proprietorAddress, RequestBody content, MultipartBody.Part image1, MultipartBody.Part image2, MultipartBody.Part image3, MultipartBody.Part image4) {
        Subscription subscription = HttpUtil.getInstance()
                .commitEvaluate(token, categoryType, proprietorId, proprietorName, proprietorMobile, proprietorAddress, content, image1, image2, image3, image4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.commitSucces();
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void commitEvaluate(RequestBody token, RequestBody categoryType, RequestBody proprietorId, RequestBody proprietorName, RequestBody proprietorMobile, RequestBody proprietorAddress, RequestBody content, MultipartBody.Part image1, MultipartBody.Part image2, MultipartBody.Part image3) {
        Subscription subscription = HttpUtil.getInstance()
                .commitEvaluate(token, categoryType, proprietorId, proprietorName, proprietorMobile, proprietorAddress, content, image1, image2, image3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.commitSucces();
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void commitEvaluate(RequestBody token, RequestBody categoryType, RequestBody proprietorId, RequestBody proprietorName, RequestBody proprietorMobile, RequestBody proprietorAddress, RequestBody content, MultipartBody.Part image1, MultipartBody.Part image2) {
        Subscription subscription = HttpUtil.getInstance()
                .commitEvaluate(token, categoryType, proprietorId, proprietorName, proprietorMobile, proprietorAddress, content, image1, image2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.commitSucces();
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void commitEvaluate(RequestBody token, RequestBody categoryType, RequestBody proprietorId, RequestBody proprietorName, RequestBody proprietorMobile, RequestBody proprietorAddress, RequestBody content, MultipartBody.Part image1) {
        Subscription subscription = HttpUtil.getInstance()
                .commitEvaluate(token, categoryType, proprietorId, proprietorName, proprietorMobile, proprietorAddress, content, image1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.commitSucces();
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void commitEvaluate(RequestBody token, RequestBody categoryType, RequestBody proprietorId, RequestBody proprietorName, RequestBody proprietorMobile, RequestBody proprietorAddress, RequestBody content) {
        Subscription subscription = HttpUtil.getInstance()
                .commitEvaluate(token, categoryType, proprietorId, proprietorName, proprietorMobile, proprietorAddress, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.commitSucces();
                    }
                });
        addSubcribe(subscription);
    }
}
