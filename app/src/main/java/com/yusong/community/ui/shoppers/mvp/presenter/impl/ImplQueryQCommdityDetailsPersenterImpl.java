package com.yusong.community.ui.shoppers.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.shoppers.bean.CommodityBean;
import com.yusong.community.ui.shoppers.mvp.implView.ImplQueryQCommdityDetailsView;
import com.yusong.community.ui.shoppers.mvp.presenter.ImplQueryQCommdityDetailsPersenter;
import com.yusong.community.utils.CacheUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 *         created at 2017/3/10 18:26.
 *         查询抢购
 */
public class ImplQueryQCommdityDetailsPersenterImpl extends BasePresenterImpl<ImplQueryQCommdityDetailsView>
        implements ImplQueryQCommdityDetailsPersenter {
    @Override
    public void queryQgCommdity(int grabItemId) {
        Subscription subscription = HttpUtil.getInstance().queryQgCommdityDetails(CacheUtils.getToken(mContext), grabItemId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<CommodityBean>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<CommodityBean> commodityBeanHttpResult) {
                        mView.succedQgCommdityDetails(commodityBeanHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    public ImplQueryQCommdityDetailsPersenterImpl(ImplQueryQCommdityDetailsView view, Context context) {
        super(view, context);
    }
}
