package com.yusong.community.ui.community_service.mvp.presenter.ImplPresenter;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.community_service.entity.ServiceOrderBean;
import com.yusong.community.ui.community_service.mvp.ImplView.ServiceOrderView;
import com.yusong.community.ui.community_service.mvp.presenter.ServiceOrderPresenter;
import com.yusong.community.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 * @created at 2017-09-23.
 * @describe: null
 */

public class ServiceOrderPresenterImpl extends BasePresenterImpl<ServiceOrderView> implements ServiceOrderPresenter {
    public ServiceOrderPresenterImpl(ServiceOrderView view, Context context) {
        super(view, context);
    }

    @Override
    public void queryServiceOrderList(String type, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance().queryServiceOrder(CacheUtils.getToken(mContext), type, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<ServiceOrderBean>>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.queryOrderError();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<ServiceOrderBean>> listHttpResult) {
                        mView.queryOrderSucces(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void commitServiceComment(String orderId, String content) {
        Subscription subscription = HttpUtil.getInstance().commentServiceOrder(CacheUtils.getToken(mContext), orderId,content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.commitServiceCommentSucces();
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void cancelServiceOrder(String orderId) {
        Subscription subscription = HttpUtil.getInstance().cancelServiceOrder(CacheUtils.getToken(mContext), orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.cancelServiceOrderSucces();
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void confirmOrder(String orderId) {
        Subscription subscription = HttpUtil.getInstance().confirmServiceOrder(CacheUtils.getToken(mContext), orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.confirmOrderSucces();
                    }
                });
        addSubcribe(subscription);
    }
}
