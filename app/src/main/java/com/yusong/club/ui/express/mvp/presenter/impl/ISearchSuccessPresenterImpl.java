package com.yusong.club.ui.express.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.MyApplication;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.express.mvp.entity.ScanOrder;
import com.yusong.club.ui.express.mvp.implView.ISearchSuccessView;
import com.yusong.club.ui.express.mvp.presenter.ISearchSuccessPresenter;
import com.yusong.club.ui.express.mvp.request.RequestOrderLogistics;

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

public class ISearchSuccessPresenterImpl extends BasePresenterImpl<ISearchSuccessView>
        implements ISearchSuccessPresenter{
    public ISearchSuccessPresenterImpl(ISearchSuccessView view, Context context) {
        super(view, context);
    }


    @Override
    public void scanOrder(long number) {
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
                        MyApplication.showMessage("网络繁忙！");
                        mView.errEmpty();
                    }

                    @Override
                    public void onNext(ScanOrder order) {
                        MyApplication.closeProgressDialog();
                        if (order.isSuccess()) {

                            List<ScanOrder.ShipperInfo> shippers = order.getShippers();
                            if (shippers == null || shippers.size() == 0){
                                mView.errEmpty();
                            }else {
                                mView.showInfo(order);
                            }
                        }else {
                            mView.errEmpty();
                        }
                    }
                });
        addSubcribe(subscription);

    }
}
