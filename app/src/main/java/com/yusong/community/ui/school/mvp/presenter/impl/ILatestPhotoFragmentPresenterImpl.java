package com.yusong.community.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.community.MyApplication;
import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.school.mvp.entity.LatestPhoto;
import com.yusong.community.ui.school.mvp.implView.ILatestPhotoFragmentView;
import com.yusong.community.ui.school.mvp.presenter.ILatestPhotoFragmentPresenter;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian on 2017/3/9.
 */

public class ILatestPhotoFragmentPresenterImpl extends BasePresenterImpl<ILatestPhotoFragmentView> implements ILatestPhotoFragmentPresenter {
    public ILatestPhotoFragmentPresenterImpl(ILatestPhotoFragmentView view, Context context) {
        super(view, context);
    }

    @Override
    public void getLatestPhoto(String token, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance()
                .getLatestPhto(token, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<LatestPhoto>>>(mContext) {
                    @Override
                    protected void onFailure(String err) {
                        mView.closeRefreshing();
                    }
                    @Override
                    protected void onSuccess(HttpResult<List<LatestPhoto>> listHttpResult) {
                        mView.closeRefreshing();
                        if (listHttpResult.code == 0 ) {
                            mView.getLatestPhotoList(listHttpResult.data);
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
