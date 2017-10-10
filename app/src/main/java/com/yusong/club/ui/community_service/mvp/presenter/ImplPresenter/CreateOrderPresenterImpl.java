package com.yusong.club.ui.community_service.mvp.presenter.ImplPresenter;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.community_service.mvp.ImplView.CreateOrderView;
import com.yusong.club.ui.community_service.mvp.presenter.CreateOrderPresenter;
import com.yusong.club.ui.shoppers.bean.CreataOrderBean;
import com.yusong.club.utils.CacheUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 * @created at 2017-09-22.
 * @describe: null
 */

public class CreateOrderPresenterImpl extends BasePresenterImpl<CreateOrderView> implements CreateOrderPresenter {
    @Override
    public void createServiceOrder(int deliverType, int itemId, double price, String province, String city, String district, String street, String receiverMobile, String reciever, String serviceTime, String leaveMessage) {
        Subscription subscription = HttpUtil.getInstance().serviceCreateOrder(CacheUtils.getToken(mContext), deliverType, itemId,
                price, province, city, district, street, receiverMobile, reciever, serviceTime, leaveMessage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<CreataOrderBean>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<CreataOrderBean> creataOrderBeanHttpResult) {
                        mView.createOrderSucces(creataOrderBeanHttpResult.data.getId());
                    }
                });
        addSubcribe(subscription);
    }

    public CreateOrderPresenterImpl(CreateOrderView view, Context context) {
        super(view, context);
    }
}
