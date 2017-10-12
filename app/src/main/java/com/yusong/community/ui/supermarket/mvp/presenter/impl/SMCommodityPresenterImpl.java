package com.yusong.community.ui.supermarket.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.shoppers.bean.CommodityBean;
import com.yusong.community.ui.shoppers.bean.PinLunBean;
import com.yusong.community.ui.shoppers.bean.SpecificationBean;
import com.yusong.community.ui.supermarket.mvp.ImolView.SMCommodityView;
import com.yusong.community.ui.supermarket.mvp.presenter.SMCommodityPresenter;
import com.yusong.community.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 * @created at 2017-09-05.
 * @describe: 超市 商品详情/商品评论/下单/提交评论/规格查询
 */

public class SMCommodityPresenterImpl extends BasePresenterImpl<SMCommodityView> implements SMCommodityPresenter {
    @Override
    public void querySMCommodityDetails(int itemId) {
        Subscription subscription = HttpUtil.getInstance().querySMCommodityDetalis(CacheUtils.getToken(mContext), itemId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<CommodityBean>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<CommodityBean> listHttpResult) {
                        mView.queryCommoditySucceed(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void querySMComment(int itemId, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance().querySMCommodityComment(CacheUtils.getToken(mContext), itemId, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<PinLunBean>>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.queryCommodityCommentError();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<PinLunBean>> listHttpResult) {
                        mView.queryCommodityCommentSucced(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void querySMSpecification(int itemId) {
        Subscription subscription = HttpUtil.getInstance().querySMItemSpecification(CacheUtils.getToken(mContext),itemId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<SpecificationBean>>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<SpecificationBean>> listHttpResult) {
                        mView.querySMSpecificationSuccess(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    public SMCommodityPresenterImpl(SMCommodityView view, Context context) {
        super(view, context);
    }
}
