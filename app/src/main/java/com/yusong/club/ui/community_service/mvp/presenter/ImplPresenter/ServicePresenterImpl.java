package com.yusong.club.ui.community_service.mvp.presenter.ImplPresenter;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.community_service.entity.ServiceBean;
import com.yusong.club.ui.community_service.entity.ServiceDetailBean;
import com.yusong.club.ui.community_service.mvp.ImplView.ServiceView;
import com.yusong.club.ui.community_service.mvp.presenter.ServicePresenter;
import com.yusong.club.ui.shoppers.bean.FenLeiBean;
import com.yusong.club.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 * @created at 2017-09-22.
 * @describe: null
 */

public class ServicePresenterImpl extends BasePresenterImpl<ServiceView> implements ServicePresenter {
    @Override
    public void queryServiceDetail() {//服务详情
        Subscription subscription = HttpUtil.getInstance().queryServiceInfo(CacheUtils.getToken(mContext))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<ServiceDetailBean>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<ServiceDetailBean> serviceDetailBeanHttpResult) {
                        mView.queryServiceSucces(serviceDetailBeanHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void queryServiceCategory() {//分类查询
        Subscription subscription = HttpUtil.getInstance().queryServiceCategory(CacheUtils.getToken(mContext))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<FenLeiBean>>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<FenLeiBean>> listHttpResult) {
                        mView.queryServiceCategorySucces(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void queryServiceList(int category, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance().queryServiceList(CacheUtils.getToken(mContext), category, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<ServiceBean>>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.queryServiceListError();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<ServiceBean>> listHttpResult) {
                        mView.queryServiceListSucces(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    public ServicePresenterImpl(ServiceView view, Context context) {
        super(view, context);
    }
}
