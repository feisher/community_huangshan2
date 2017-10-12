package com.yusong.community.ui.express.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.MyApplication;
import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.express.event.OpenBox;
import com.yusong.community.ui.express.event.QueryLogistics;
import com.yusong.community.ui.express.mvp.entity.GetOrderInfo;
import com.yusong.community.ui.express.mvp.entity.RatesInfo;
import com.yusong.community.ui.express.mvp.entity.SaveOrderInfo;
import com.yusong.community.ui.express.mvp.entity.ScanOrder;
import com.yusong.community.ui.express.mvp.entity.SenderOrderInfo;
import com.yusong.community.ui.express.mvp.implView.IOrderView;
import com.yusong.community.ui.express.mvp.presenter.IOrderPresenter;
import com.yusong.community.ui.express.mvp.request.RequestOrderLogistics;
import com.yusong.community.utils.AppUtils;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Map;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class IOrderPresenterIpml extends BasePresenterImpl<IOrderView> implements IOrderPresenter {


    public IOrderPresenterIpml(IOrderView view, Context context) {
        super(view, context);
    }

    @Override
    public void requestGetOrder(String type, int offset, int limit) {

        Subscription subscription = HttpUtil.getInstance().queryGetOrder(CacheUtils.getToken(mContext), type, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<GetOrderInfo>>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.hideRefresh();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<GetOrderInfo>> result) {
                        mView.setGetOrderAdapter(result.data);
                    }


                });
        addSubcribe(subscription);

    }

    @Override
    public void requestSenderOrder(String type, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance().querySenderOrder(CacheUtils.getToken(mContext), type, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<SenderOrderInfo>>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.hideRefresh();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<SenderOrderInfo>> result) {
                        mView.setSenderOrderAdapter(result.data);
                    }

                });
        addSubcribe(subscription);

    }


    @Override
    public void requestSaveOrder(String type, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance().querySaveOrder(CacheUtils.getToken(mContext), type, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<SaveOrderInfo>>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<SaveOrderInfo>> result) {
                        mView.setSaveOrderAdapter(result.data);
                    }

                });
        addSubcribe(subscription);

    }

    public void openBoxEvent(final String token, final int type, final String id) {

        Subscription subscription = HttpUtil.getInstance().queryTimeoutCharge(token, type, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<RatesInfo>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<RatesInfo> result) {
                        RatesInfo data = result.data;
                        int charge = data.getCharge();
                        LogUtils.e("查询到的费用是--"+charge);
                        if (charge > 0) {
                            mView.jumpActivity(charge, id);
                        } else {
                            EventBus.getDefault().post(new OpenBox(token, type, id));
                        }

                    }
                });

        addSubcribe(subscription);
    }


    //删除订单
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void cancleOrder(String id, final int position) {
        Subscription subscription =
                HttpUtil.getInstance().cancleOrder(CacheUtils.getToken(mContext), id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseSubscriber<HttpResult>(mContext) {
                            @Override
                            protected void onFailure(String err) {

                            }

                            @Override
                            protected void onSuccess(HttpResult result) {
                                MyApplication.showMessage("删除成功");
                                mView.refreshList(position);
                            }
                        });
        addSubcribe(subscription);
    }

    //查询物流
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void scanOrder(QueryLogistics mQueryLogistics) {

        Map<String, String> params = null;
        final String number = mQueryLogistics.getNumber();
        try {
            params = RequestOrderLogistics.scanOrder(number + "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        mView.showProgressDialog();
        Subscription subscription = HttpUtil.getInstance(true).scanOrder(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ScanOrder>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable err) {
                        MyApplication.closeProgressDialog();
                        MyApplication.showMessage("网络繁忙！");
                    }

                    @Override
                    public void onNext(ScanOrder order) {
                        MyApplication.closeProgressDialog();
                        if (order.isSuccess()) {
                            List<ScanOrder.ShipperInfo> shippers = order.getShippers();
                            if (!AppUtils.listIsEmpty(shippers)) {
                                ScanOrder.ShipperInfo info = shippers.get(0);
                                mView.jumpActivity(info, number);
                            }
                        }
                    }
                });
        addSubcribe(subscription);

    }


    @Override
    public boolean useEvenBus() {
        return true;
    }


}
