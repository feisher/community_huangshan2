package com.yusong.community.ui.repairs.mvp.persenter.impl;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.repairs.mvp.ImplView.CreateRepairsView;
import com.yusong.community.ui.repairs.mvp.persenter.CreateRepairPresenter;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 * @created at 2017-09-01.
 * @describe: null
 */

public class CreateRepairPresenterImpl extends BasePresenterImpl<CreateRepairsView> implements CreateRepairPresenter {

    public CreateRepairPresenterImpl(CreateRepairsView view, Context context) {
        super(view, context);
    }

    @Override
    public void createRepairs(RequestBody token, RequestBody categoryId, RequestBody proprietorId,
                              RequestBody proprietorName, RequestBody proprietorMobile, RequestBody
                                      proprietorAddress, RequestBody content, MultipartBody.Part image1,
                              MultipartBody.Part image2, MultipartBody.Part image3, MultipartBody.Part image4) {
        Subscription subscription = HttpUtil.getInstance()
                .createRepairs(token, categoryId, proprietorId, proprietorName, proprietorMobile, proprietorAddress, content, image1, image2, image3, image4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.createSucces();
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void createRepairs(RequestBody token, RequestBody categoryId, RequestBody proprietorId, RequestBody proprietorName, RequestBody proprietorMobile, RequestBody proprietorAddress, RequestBody content, MultipartBody.Part image1, MultipartBody.Part image2, MultipartBody.Part image3) {
        Subscription subscription = HttpUtil.getInstance()
                .createRepairs(token, categoryId, proprietorId, proprietorName, proprietorMobile, proprietorAddress, content, image1, image2, image3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.createSucces();
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void createRepairs(RequestBody token, RequestBody categoryId, RequestBody proprietorId, RequestBody proprietorName, RequestBody proprietorMobile, RequestBody proprietorAddress, RequestBody content, MultipartBody.Part image1, MultipartBody.Part image2) {
        Subscription subscription = HttpUtil.getInstance()
                .createRepairs(token, categoryId, proprietorId, proprietorName, proprietorMobile, proprietorAddress, content, image1, image2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.createSucces();
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void createRepairs(RequestBody token, RequestBody categoryId, RequestBody proprietorId, RequestBody proprietorName, RequestBody proprietorMobile, RequestBody proprietorAddress, RequestBody content, MultipartBody.Part image1) {
        Subscription subscription = HttpUtil.getInstance()
                .createRepairs(token, categoryId, proprietorId, proprietorName, proprietorMobile, proprietorAddress, content, image1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.createSucces();
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void createRepairs(RequestBody token, RequestBody categoryId, RequestBody proprietorId, RequestBody proprietorName, RequestBody proprietorMobile, RequestBody proprietorAddress, RequestBody content) {
        Subscription subscription = HttpUtil.getInstance()
                .createRepairs(token, categoryId, proprietorId, proprietorName, proprietorMobile, proprietorAddress, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.createSucces();
                    }
                });
        addSubcribe(subscription);
    }
}
