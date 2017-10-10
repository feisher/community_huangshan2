package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.mvp.implView.IAllPhotoFragmentView;
import com.yusong.club.ui.school.mvp.presenter.IAllPhotoFragmentPresenter;
import com.yusong.club.ui.school.teacher.bean.EventMsg;
import com.yusong.club.ui.school.teacher.bean.PhotoAlbum;
import com.yusong.club.utils.CacheUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian on 2017/3/9.
 */

public class IAllPhotoFragmentPresenterImpl extends BasePresenterImpl<IAllPhotoFragmentView> implements IAllPhotoFragmentPresenter {
    public IAllPhotoFragmentPresenterImpl(IAllPhotoFragmentView view, Context context) {
        super(view, context);
    }

    @Override
    public boolean useEvenBus() {


        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onEventMsg(EventMsg event) {
        if (event.msg.equals(EventMsg.REFRESH_PHOT_DETAIL)) {
            queryPhotoAlbumList(CacheUtils.getToken(mContext), 0, 10);
        }
    }

    @Override
    public void queryPhotoAlbumList(String token, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance()
                .queryPhotoAlbumList(token, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<PhotoAlbum>>>(mContext) {
                    @Override
                    protected void onFailure(String err) {
                        mView.closeRefreshing();

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<PhotoAlbum>> listHttpResult) {
                        mView.closeRefreshing();
                        if (listHttpResult.code == 0 ) {
                            mView.getPhotoAlbumList((List<PhotoAlbum>) listHttpResult.data);
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
