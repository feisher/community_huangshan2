package com.yusong.club.ui.community.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.community.mvp.entity.Community;
import com.yusong.club.ui.community.mvp.entity.SetCommunity;
import com.yusong.club.ui.community.mvp.implView.INearbyCommuinityActivityView;
import com.yusong.club.ui.community.mvp.presenter.INearbyActivityPresenter;
import com.yusong.club.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by feisher on 2017/1/10.
 */
public class NearbyActivityPresenterIpml extends BasePresenterImpl<INearbyCommuinityActivityView> implements INearbyActivityPresenter {

    public NearbyActivityPresenterIpml(INearbyCommuinityActivityView view, Context context) {
        super(view, context);
    }

    @Override
    public void queryNearbyCommuity(int areaId, final int baiduAreaId, double lat, double lng) {
        Subscription subscription = HttpUtil.getInstance()
                .queryNearbyCommuity(CacheUtils.getToken(mContext),areaId,baiduAreaId,lat,lng)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<Community>>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<Community>> listHttpResult) {
                        mView.closeRefresh();
                        if (listHttpResult.code==0&&listHttpResult.data!=null) {
                            mView.setCommunitysAdapter((List<Community>) listHttpResult.data);
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
    public void setCurrentCommunity(int communityId) {
        Subscription subscription = HttpUtil.getInstance()
                .setCurrentCommunity(CacheUtils.getToken(mContext),communityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<SetCommunity>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.setCurrentCommunityFailCallback();
                    }

                    @Override
                    protected void onSuccess(HttpResult<SetCommunity> setCommunityHttpResult) {
                        if (setCommunityHttpResult.code==0&&setCommunityHttpResult.data!=null) {
                            mView.setCurrentCommunitySucceedCallback((SetCommunity)setCommunityHttpResult.data);
                        }else {
                            if (!TextUtils.isEmpty(setCommunityHttpResult.message)) {
                                MyApplication.showMessage(setCommunityHttpResult.message);
                            }
                            mView.setCurrentCommunityFailCallback();
                        }
                    }
                });

        addSubcribe(subscription);
    }




}
