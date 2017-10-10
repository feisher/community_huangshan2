package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.mvp.implView.IPhotoDetailActivityView;
import com.yusong.club.ui.school.mvp.presenter.IPhotoDetailActivityPresenter;
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

public class IPhotoDetailActivityPresenterImpl extends BasePresenterImpl<IPhotoDetailActivityView> implements IPhotoDetailActivityPresenter {
    public IPhotoDetailActivityPresenterImpl(IPhotoDetailActivityView view, Context context) {
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
                        if (result.code == 0 && result.data != null) {
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
    public void deleteAllPhoto(String token, int albumId) {
        Subscription subscription = HttpUtil.getInstance()
                .deleteAllPhoto(token, albumId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<String>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<String> listHttpResult) {
                        if (listHttpResult.code == 0) {
                            mView.deleteAllPhoto(listHttpResult.data);
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
