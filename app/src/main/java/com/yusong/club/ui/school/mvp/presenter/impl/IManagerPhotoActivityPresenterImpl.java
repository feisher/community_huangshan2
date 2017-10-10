package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.mvp.implView.IManagerPhotoActivityView;
import com.yusong.club.ui.school.mvp.presenter.IManagerPhotoActivityPresenter;
import com.yusong.club.ui.school.teacher.bean.EventMsg;
import com.yusong.club.ui.school.teacher.bean.PhotoAlbum;
import com.yusong.club.utils.CacheUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian on 2017/3/22.
 */

public class IManagerPhotoActivityPresenterImpl extends BasePresenterImpl<IManagerPhotoActivityView> implements IManagerPhotoActivityPresenter {
    public IManagerPhotoActivityPresenterImpl(IManagerPhotoActivityView view, Context context) {
        super(view, context);
    }

    @Override
    public boolean useEvenBus() {


        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onEventMsg(EventMsg event) {
        if (event.msg.equals(EventMsg.REFRESH_PHOT_DETAIL)) {
            getClazzPhotoList(CacheUtils.getToken(mContext), event.id);
        }
    }

    @Override
    public void getClazzPhotoList(String token, int albumId) {
        Subscription subscription = HttpUtil.getInstance()
                .getClazzPhotoList(token, albumId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<PhotoAlbum>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.closeRefreshing();
                    }

                    @Override
                    protected void onSuccess(HttpResult<PhotoAlbum> result) {
                        mView.closeRefreshing();
                        if (result.code == 0) {
                            mView.setPhotList(result.data);
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
    public void deletePhoto(String token, String photoId,int albumId) {
        Subscription subscription = HttpUtil.getInstance()
                .deletePhoto(token, photoId,albumId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<String>>() {
                    @Override
                    protected void onFailure(String err) {
                    }

                    @Override
                    protected void onSuccess(HttpResult<String> result) {
                        if (result.code == 0 ) {
                            mView.deletePhoto(result.data);
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
