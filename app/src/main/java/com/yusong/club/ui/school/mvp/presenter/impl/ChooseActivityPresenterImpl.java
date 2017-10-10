package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.mvp.entity.School;
import com.yusong.club.ui.school.mvp.implView.IChooseActivityView;
import com.yusong.club.ui.school.mvp.presenter.IChooseActivityPresenter;
import com.yusong.club.utils.SPUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by feisher on 2017/2/24.
 */

public class ChooseActivityPresenterImpl extends BasePresenterImpl<IChooseActivityView> implements IChooseActivityPresenter {

    public ChooseActivityPresenterImpl(IChooseActivityView view, Context context) {
        super(view, context);
    }

    @Override
    public void querySchoolList(String token, int offset, int limit) {
                Subscription subscription = HttpUtil.getInstance()
                .schoolList(token,offset,limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<School>>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<School>> listHttpResult) {
                        if (listHttpResult.code==0&&listHttpResult.data!=null) {
                            mView.schoolListCallback((List<School>) listHttpResult.data);
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
    public void selectRole(String token, int schoolId, final int roleId, final int type) {
        Subscription subscription = HttpUtil.getInstance()
                .selectRole(token,schoolId,roleId,type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<String>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<String> listHttpResult) {
                        if (listHttpResult.code==0) {
                            SPUtils.put(mContext,"selectRoleId",roleId);
                            mView.selectRoleCallback(type);
                        }else {
                            if (!TextUtils.isEmpty(listHttpResult.message)) {
                                MyApplication.showMessage(listHttpResult.message);
                            }
                        }
                    }
                });

        addSubcribe(subscription);
    }

//    @Override
//    public void queryPublicNoticeList(String token, int offset, int limit) {
//        Subscription subscription = HttpUtil.getInstance()
//                .public_noticeList(token,offset,limit)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new BaseSubscriber<HttpResult<List<Notice>>>() {
//                    @Override
//                    protected void onFailure(String err) {
//
//                    }
//
//                    @Override
//                    protected void onSuccess(HttpResult<List<Notice>> listHttpResult) {
//                        if (listHttpResult.code==0&&listHttpResult.data!=null) {
//                            mView.noticeDataCallback((List<Notice>) listHttpResult.data);
//                        }else {
//                            if (!TextUtils.isEmpty(listHttpResult.message)) {
//                                MyApplication.showMessage(listHttpResult.message);
//                            }
//                        }
//                    }
//                });
//        addSubcribe(subscription);
//    }

}
