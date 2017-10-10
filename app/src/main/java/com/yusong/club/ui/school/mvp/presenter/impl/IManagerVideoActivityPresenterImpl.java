package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.mvp.implView.IManagerVideoActivityView;
import com.yusong.club.ui.school.mvp.presenter.IManagerVideoActivityPresenter;
import com.yusong.club.ui.school.teacher.bean.VideoAlbum;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian on 2017/4/7.
 */

public class IManagerVideoActivityPresenterImpl extends BasePresenterImpl<IManagerVideoActivityView> implements IManagerVideoActivityPresenter {
    public IManagerVideoActivityPresenterImpl(IManagerVideoActivityView view, Context context) {
        super(view, context);
    }

    @Override
    public void getClazzVideoList(String token, int albumId) {
        Subscription subscription = HttpUtil.getInstance()
                .getClazzVideoList(token, albumId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<VideoAlbum>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.closeRefreshing();
                    }

                    @Override
                    protected void onSuccess(HttpResult<VideoAlbum> result) {
                        mView.closeRefreshing();
                        if (result.code == 0 ) {
                            mView.setVideoList(result.data);
                        } else {
                            if (!TextUtils.isEmpty(result.message)) {
                                MyApplication.showMessage(result.message);
                            }
                        }
                    }
                });
        addSubcribe(subscription);

    }

    @Override
    public void deleteVideo(String token, String videoIds, int albumId) {
        Subscription subscription = HttpUtil.getInstance()
                .deleteVideo(token, videoIds,albumId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<String>>() {
                    @Override
                    protected void onFailure(String err) {
                    }

                    @Override
                    protected void onSuccess(HttpResult<String> result) {
                        if (result.code == 0 ) {
                            mView.deleteVideo(result.data);
                        } else {
                            if (!TextUtils.isEmpty(result.message)) {
                                MyApplication.showMessage(result.message);
                            }
                        }
                    }
                });
        addSubcribe(subscription);




    }


}
