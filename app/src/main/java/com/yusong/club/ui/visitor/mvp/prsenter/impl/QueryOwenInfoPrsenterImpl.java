package com.yusong.club.ui.visitor.mvp.prsenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.visitor.entity.CommuntitySetingBean;
import com.yusong.club.ui.visitor.entity.OwnerInfo;
import com.yusong.club.ui.visitor.mvp.ImplView.IViewOwnerInfo;
import com.yusong.club.ui.visitor.mvp.prsenter.QueryOwenrInfoPrsenter;
import com.yusong.club.utils.CacheUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 * @created at 2017-08-30.
 * @describe: 查询业主信息
 */

public class QueryOwenInfoPrsenterImpl extends BasePresenterImpl<IViewOwnerInfo> implements QueryOwenrInfoPrsenter {
    public QueryOwenInfoPrsenterImpl(IViewOwnerInfo view, Context context) {
        super(view, context);
    }

    @Override
    public void queryOwnerInfo() {
        Subscription subscription = HttpUtil.getInstance().queryOwnerInfo(CacheUtils.getToken(mContext))
                .doOnNext(new Action1<HttpResult<OwnerInfo>>() {
                    @Override
                    public void call(HttpResult<OwnerInfo> ownerInfoHttpResult) {
                        if (ownerInfoHttpResult.code == 0) {
                            saveLoginInfo(ownerInfoHttpResult.data);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<OwnerInfo>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<OwnerInfo> ownerInfoBeanHttpResult) {
                        mView.queryOwnerInfoSucces(ownerInfoBeanHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void queryCommuntityStting() {
        Subscription subscription = HttpUtil.getInstance().queryCommuntiySeting(CacheUtils.getToken(mContext))
                .doOnNext(new Action1<HttpResult<CommuntitySetingBean>>() {
                    @Override
                    public void call(HttpResult<CommuntitySetingBean> communtitySetingBeanHttpResult) {
                        if (communtitySetingBeanHttpResult.code == 0) {
                            savaCommuntitySeting(communtitySetingBeanHttpResult.data);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<CommuntitySetingBean>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<CommuntitySetingBean> communtitySetingBeanHttpResult) {
                        mView.queryCommuntitySucces(communtitySetingBeanHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    private void saveLoginInfo(OwnerInfo ownerInfo) {
        //缓存
        OwnerInfo info = new OwnerInfo();
        info.setAddress(ownerInfo.getAddress());
        info.setCommunityId(ownerInfo.getCommunityId());
        info.setAddress(ownerInfo.getAddress());
        info.setCommunityName(ownerInfo.getCommunityName());
        info.setProprietorName(ownerInfo.getProprietorName());
        info.setMobile(ownerInfo.getMobile());
        info.setProprietorId(ownerInfo.getProprietorId());
        CacheUtils.saveOwnerInfo(mContext, info);
    }

    private void savaCommuntitySeting(CommuntitySetingBean communtitySetingBean) {
        CommuntitySetingBean setingBean = new CommuntitySetingBean();
        setingBean.setTel(communtitySetingBean.getTel());
        setingBean.setServiceTime(communtitySetingBean.getServiceTime());
        CacheUtils.saveCommuntitySeting(mContext, setingBean);
    }
}
