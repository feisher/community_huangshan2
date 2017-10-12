package com.yusong.community.ui.express.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.MyApplication;
import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.express.mvp.entity.SaveDetails;
import com.yusong.community.ui.express.mvp.entity.ScanOrder;
import com.yusong.community.ui.express.mvp.entity.SenderDetails;
import com.yusong.community.ui.express.mvp.implView.ISenderOrderDetailsView;
import com.yusong.community.ui.express.mvp.presenter.ISenderOrderDetailsPresenter;
import com.yusong.community.ui.express.mvp.request.RequestOrderLogistics;
import com.yusong.community.utils.CacheUtils;

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

public class ISenderOrderDetailsPresenterImpl extends BasePresenterImpl<ISenderOrderDetailsView>
        implements ISenderOrderDetailsPresenter {

    public ISenderOrderDetailsPresenterImpl(ISenderOrderDetailsView view, Context context) {
        super(view, context);
    }


    @Override
    public void querySenderDetails(String id) {
        Subscription subscription = HttpUtil.getInstance()
                .querySenderDetails(CacheUtils.getToken(mContext), id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<SenderDetails>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<SenderDetails> result) {
                        mView.updateUI(result.data);
                    }
                });
        addSubcribe(subscription);

    }

    @Override
    public void scanOrder(String number) {

        Map<String, String> params = null;
        try {
            params = RequestOrderLogistics.scanOrder(number+"");
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
                    }

                    @Override
                    public void onNext(ScanOrder order) {
                        MyApplication.closeProgressDialog();
                        if (order.isSuccess()) {
                            List<ScanOrder.ShipperInfo> shippers = order.getShippers();
                            if (shippers!=null && shippers.size() > 0){
                                mView.setIcon(shippers.get(0).getShipperCode());
                            }
                        }
                    }
                });
        addSubcribe(subscription);

    }

    @Override
    public void querySaveDetails(String orderid) {
        Subscription subscription = HttpUtil.getInstance()
                .saveDetail(CacheUtils.getToken(mContext), orderid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<SaveDetails>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<SaveDetails> result) {
                        mView.updateUI(result.data);
                    }
                });
        addSubcribe(subscription);

    }
}
