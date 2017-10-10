package com.yusong.club.ui.community.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.community.mvp.entity.HaveCommunityCity;
import com.yusong.club.ui.community.mvp.implView.HaveCommunityCityActivityView;
import com.yusong.club.ui.community.mvp.presenter.IHaveCommunityCityActivityPresenter;
import com.yusong.club.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by feisher on 2017/1/10.
 */
public class HaveCommunityCityActivityPresenterIpml extends BasePresenterImpl<HaveCommunityCityActivityView> implements IHaveCommunityCityActivityPresenter {

    public HaveCommunityCityActivityPresenterIpml(HaveCommunityCityActivityView view, Context context) {
        super(view, context);
    }

    @Override
    public void queryHaveCommunityCity() {
        Subscription subscription = HttpUtil.getInstance()
                .queryCityList(CacheUtils.getToken(mContext))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<HaveCommunityCity>>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<HaveCommunityCity>> listHttpResult) {
                        if (listHttpResult.code==0&&listHttpResult.data!=null) {
                            mView.setAdapter4ListDataCallback((List<HaveCommunityCity>)listHttpResult.data);
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
