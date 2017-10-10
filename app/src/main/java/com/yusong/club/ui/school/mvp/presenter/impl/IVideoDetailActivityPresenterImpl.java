package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.mvp.implView.IVideoDetailActivityView;
import com.yusong.club.ui.school.mvp.presenter.IVideoDetailActivityPresenter;
import com.yusong.club.ui.school.teacher.bean.VideoAlbum;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian on 2017/3/25.
 */

public class IVideoDetailActivityPresenterImpl extends BasePresenterImpl<IVideoDetailActivityView> implements IVideoDetailActivityPresenter {
    public IVideoDetailActivityPresenterImpl(IVideoDetailActivityView view, Context context) {
        super(view, context);
    }

    @Override
    public void getClazzVideoList(String token, int albumId) {
        Subscription subscription = HttpUtil.getInstance()
                .getClazzVideoList(token, albumId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<VideoAlbum>>(mContext) {
                    @Override
                    protected void onFailure(String err) {
                        mView.closeRefresh();

                    }

                    @Override
                    protected void onSuccess(HttpResult<VideoAlbum> listHttpResult) {
                        mView.closeRefresh();
                        if (listHttpResult.code == 0 ) {
                            mView.getAllVideo(listHttpResult.data);
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
    public void deleteAllVideo(String token, int albumId) {
        Subscription subscription = HttpUtil.getInstance()
                .deleteAllVideo(token, albumId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<String>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<String> listHttpResult) {
                        if (listHttpResult.code == 0) {
                            mView.deleteAllVideo(listHttpResult.data);
                        } else {
                            if (!TextUtils.isEmpty(listHttpResult.message)) {
                                MyApplication.showMessage(listHttpResult.message);
                            } else {
                                MyApplication.showMessage("删除失败");
                            }
                        }
                    }
                });
        addSubcribe(subscription);
    }


}
