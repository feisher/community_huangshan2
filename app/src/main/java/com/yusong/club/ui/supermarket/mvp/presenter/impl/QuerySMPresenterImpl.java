package com.yusong.club.ui.supermarket.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.shoppers.bean.FenLeiBean;
import com.yusong.club.ui.supermarket.entity.SMCommodityBean;
import com.yusong.club.ui.supermarket.entity.SuperMarketDetailsBean;
import com.yusong.club.ui.supermarket.mvp.ImolView.QuerySMView;
import com.yusong.club.ui.supermarket.mvp.presenter.QuerySMViewPresenter;
import com.yusong.club.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 * @created at 2017-09-04.
 * @describe: 查询超市/超市分类/超市商品
 */

public class QuerySMPresenterImpl extends BasePresenterImpl<QuerySMView> implements QuerySMViewPresenter {
    @Override
    public void querySuperMarket() {
        Subscription subscription = HttpUtil.getInstance().querySuperMarket(CacheUtils.getToken(mContext))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<SuperMarketDetailsBean>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<SuperMarketDetailsBean> superMarketDeatailsBeanHttpResult) {
                        mView.querySMSucces(superMarketDeatailsBeanHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void queryFenlei() {
        Subscription subscription = HttpUtil.getInstance().querySuperMarketCategory(CacheUtils.getToken(mContext))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<FenLeiBean>>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<FenLeiBean>> listHttpResult) {
                        mView.querFenleiSucces(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void queryCommodity(int categoryId, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance().querySMCommodity(CacheUtils.getToken(mContext),categoryId,offset,limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<SMCommodityBean>>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<SMCommodityBean>> listHttpResult) {
                        mView.queryCommoditySucces(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    public QuerySMPresenterImpl(QuerySMView view, Context context) {
        super(view, context);
    }
}
