package com.yusong.community.ui.community_service.mvp.presenter.ImplPresenter;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.community_service.mvp.ImplView.ServiceDetailView;
import com.yusong.community.ui.community_service.mvp.presenter.ServiceDetailPresenter;
import com.yusong.community.ui.shoppers.bean.CommodityBean;
import com.yusong.community.ui.shoppers.bean.PinLunBean;
import com.yusong.community.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 * @created at 2017-09-22.
 * @describe: null
 */

public class ServiceDetailPresenterImpl extends BasePresenterImpl<ServiceDetailView> implements ServiceDetailPresenter {
    @Override
    public void queryServiceDetails(int itemId) {
        Subscription subscription = HttpUtil.getInstance().queryServiceDetails(CacheUtils.getToken(mContext), itemId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<CommodityBean>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<CommodityBean> commodityBeanHttpResult) {
                        mView.queryServiceDetailSucces(commodityBeanHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void queryServiceComments(int itemId, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance().queryServiceComment(CacheUtils.getToken(mContext), itemId,offset,limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<PinLunBean>>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.queryServiceCommentError();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<PinLunBean>> listHttpResult) {
                        mView.queryServiceCommentSucces(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    public ServiceDetailPresenterImpl(ServiceDetailView view, Context context) {
        super(view, context);
    }
}
