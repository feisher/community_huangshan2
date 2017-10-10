package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.mvp.entity.Clazz;
import com.yusong.club.ui.school.mvp.implView.ICreateRoleActivityView;
import com.yusong.club.ui.school.mvp.presenter.ICreateRoleActivityPresenter;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by feisher on 2017/2/24.
 */

public class CreateRoleActivityPresenterImpl extends BasePresenterImpl<ICreateRoleActivityView> implements ICreateRoleActivityPresenter {

    public CreateRoleActivityPresenterImpl(ICreateRoleActivityView view, Context context) {
        super(view, context);
    }

    @Override
    public void queryClazzList(String token, int schoolId, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance()
                .clazzList(token,schoolId,offset,limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<Clazz>>>(mContext) {
                    @Override
                    protected void onFailure(String err) {}
                    @Override
                    protected void onSuccess(HttpResult<List<Clazz>> listHttpResult) {
                        if (listHttpResult.code==0&&listHttpResult.data!=null) {
                            mView.clazzCallback((List<Clazz>) listHttpResult.data);
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
    public void teacherApply(RequestBody token, RequestBody schoolId, RequestBody clazzId, RequestBody userName, RequestBody applyRole, RequestBody idCard, RequestBody applyMemo, MultipartBody.Part photo) {
        Subscription subscription = HttpUtil.getInstance()
                .teacherApply(token,schoolId,clazzId,userName,applyRole,idCard,applyMemo,photo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<String>>(mContext) {
                    @Override
                    protected void onFailure(String err) {}
                    @Override
                    protected void onSuccess(HttpResult<String> listHttpResult) {
                        if (listHttpResult.code==0) {
                            mView.teacherApplyCallback();
                        }else {
                        }
                        if (!TextUtils.isEmpty(listHttpResult.message)) {
                            MyApplication.showMessage(listHttpResult.message);
                        }
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void guardianApply(RequestBody token, RequestBody schoolId, RequestBody clazzId, RequestBody userName, RequestBody applyRole, RequestBody idCard, RequestBody studentName, RequestBody studentNo, RequestBody relation, RequestBody applyMemo, MultipartBody.Part photo) {
        Subscription subscription = HttpUtil.getInstance()
                .guardianApply(token,schoolId,clazzId,userName,applyRole,idCard,studentName,studentNo,relation,applyMemo,photo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<String>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<String> listHttpResult) {
                        if (listHttpResult.code==0) {
                            mView.guardianApplyCallback();
                        }else {
                        }
                        if (!TextUtils.isEmpty(listHttpResult.message)) {
                            MyApplication.showMessage(listHttpResult.message);
                        }
                    }
                });
        addSubcribe(subscription);
    }

}
