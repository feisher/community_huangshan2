package com.yusong.community.ui.me.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.pay.bean.WeiXinPayBean;
import com.yusong.community.pay.bean.ZhiFuBaoPayBean;
import com.yusong.community.ui.me.mvp.entity.MoneyList;
import com.yusong.community.ui.me.mvp.implView.IRechargeView;
import com.yusong.community.utils.AppUtils;
import com.yusong.community.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * <li>时间：17/3/7 16:32 </li>
 * </ul>
 */
public class RechargePresenterImpl extends BasePresenterImpl<IRechargeView> {

    public RechargePresenterImpl(IRechargeView v, Context mContext) {
        super(v, mContext);
    }

    public void zfbPay(String token, int money) {

        Subscription subscription = HttpUtil.getInstance().zfbRecharge(token, money)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<ZhiFuBaoPayBean>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<ZhiFuBaoPayBean> result) {
                        mView.zfbPay(result.data, "账户充值", "账户充值");
                    }
                });
        addSubcribe(subscription);

    }

    public void wxPay(String token, int money) {

        Subscription subscription = HttpUtil.getInstance().wxRecharge(token, money)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<WeiXinPayBean>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<WeiXinPayBean> result) {

                        mView.wxPay(result.data);
                    }
                });
        addSubcribe(subscription);


    }

    public void queryPreferenceList() {


        Subscription subscription = HttpUtil.getInstance().queryPreferenceList(CacheUtils.getToken(mContext))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<MoneyList>>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<MoneyList>> result) {
                        if (!AppUtils.listIsEmpty(result.data))
                            mView.setAdapter(result.data);
                    }
                });
        addSubcribe(subscription);


    }
}