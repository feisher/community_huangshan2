package com.yusong.community.ui.home.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.community.MyApplication;
import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.home.mvp.implView.IHomeView;
import com.yusong.community.ui.home.mvp.presenter.IHomePresenter;
import com.yusong.community.ui.school.mvp.entity.Role;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.SPUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by quaner on 17/1/3.
 */

public class IHomePresenterImpl extends BasePresenterImpl<IHomeView> implements IHomePresenter{

    public IHomePresenterImpl(IHomeView view, Context context) {
        super(view, context);
    }

    @Override
    public void requestBanner() {

        Subscription subscriptions =  HttpUtil.getInstance().getBanner(CacheUtils.getToken(mContext))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<String>>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.closeLoading();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<String>> result) {
                        mView.closeLoading();
                        if (result.data != null && result.data.size() != 0) {
                            mView.showBanner(result.data);
                        }
                    }
                });
        addSubcribe(subscriptions);
    }

    @Override
    public void queryRoleList(String token) {
        Subscription subscription = HttpUtil.getInstance()
                .roleList(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<Role>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.closeLoading();
                    }

                    @Override
                    protected void onSuccess(HttpResult<Role> listHttpResult) {
                        mView.closeLoading();
                        if (listHttpResult.code==0&&listHttpResult.data!=null) {
//                            mView.setAdapter4ListDataCallback((List<HaveCommunityCity>)result.data);
                            mView.roleListCallback(listHttpResult.data);
                        }else {
                            if (!TextUtils.isEmpty(listHttpResult.message)) {
//                                MyApplication.showMessage(listHttpResult.message);
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
                .subscribe(new BaseSubscriber<HttpResult<String>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.closeLoading();
                    }

                    @Override
                    protected void onSuccess(HttpResult<String> listHttpResult) {
                        mView.closeLoading();
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

}
