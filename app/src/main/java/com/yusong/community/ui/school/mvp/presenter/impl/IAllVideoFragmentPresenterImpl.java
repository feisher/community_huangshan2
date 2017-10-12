package com.yusong.community.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.community.MyApplication;
import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.school.mvp.implView.IAllVideoFragmentView;
import com.yusong.community.ui.school.mvp.presenter.IAllVideoFragmentPresenter;
import com.yusong.community.ui.school.teacher.bean.VideoAlbum;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian on 2017/3/9.
 */

public class IAllVideoFragmentPresenterImpl extends BasePresenterImpl<IAllVideoFragmentView> implements IAllVideoFragmentPresenter {
    public IAllVideoFragmentPresenterImpl(IAllVideoFragmentView view, Context context) {
        super(view, context);
    }
    @Override
    public void queryVideoAlbumList(String token,  int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance()
                .queryVideoAlbumList(token,offset,limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<VideoAlbum>>>(mContext) {
                    @Override
                    protected void onFailure(String err) {
                        mView.closeRefreshing();

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<VideoAlbum>> listHttpResult) {
                        mView.closeRefreshing();
                        if (listHttpResult.code == 0) {
                            mView.getVideoAlbumList((List<VideoAlbum>) listHttpResult.data);
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
