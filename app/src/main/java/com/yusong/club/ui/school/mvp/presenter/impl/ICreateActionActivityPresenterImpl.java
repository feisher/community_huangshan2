package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.mvp.implView.ICreateActionActivityView;
import com.yusong.club.ui.school.mvp.presenter.ICreateActionActivityPresenter;
import com.yusong.club.ui.school.school.bean.HuoType;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian on 2017/3/15.
 */

public class ICreateActionActivityPresenterImpl extends BasePresenterImpl<ICreateActionActivityView> implements ICreateActionActivityPresenter {

    public ICreateActionActivityPresenterImpl(ICreateActionActivityView view, Context context) {
        super(view, context);
    }

    @Override
    public void createAction(RequestBody token, RequestBody categoryId, RequestBody activityName, RequestBody beginTime, RequestBody endTime, RequestBody memo, MultipartBody.Part image1,  MultipartBody.Part image2,
                              MultipartBody.Part image3,  MultipartBody.Part image4) {
        Subscription subscription = HttpUtil.getInstance()
                .createAction(token, categoryId, activityName, beginTime, endTime, memo, image1,image2,image3,image4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<String>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<String> listHttpResult) {
                        if (listHttpResult.code == 0 ) {
                            mView.createAction(listHttpResult.data);
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
    public void categoryList(String token) {
        Subscription subscription = HttpUtil.getInstance()
                .categoryList(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<HuoType>>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }
                    @Override
                    protected void onSuccess(HttpResult<List<HuoType>> listHttpResult) {
                        if (listHttpResult.code == 0 && listHttpResult.data != null) {
                            mView.getcategoryList((List<HuoType>) listHttpResult.data);
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
