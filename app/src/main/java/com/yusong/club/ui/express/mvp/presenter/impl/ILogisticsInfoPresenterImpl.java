package com.yusong.club.ui.express.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.MyApplication;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.express.mvp.entity.OrderLogistics;
import com.yusong.club.ui.express.mvp.implView.ILogisticsInfoView;
import com.yusong.club.ui.express.mvp.presenter.ILogisticsInfoPresenter;
import com.yusong.club.ui.express.mvp.request.RequestOrderLogistics;

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

public class ILogisticsInfoPresenterImpl extends BasePresenterImpl<ILogisticsInfoView>
        implements ILogisticsInfoPresenter {

    public ILogisticsInfoPresenterImpl(ILogisticsInfoView view, Context context) {
        super(view, context);
    }

    @Override
    public void queryDetailed(long id, String code) {
        Map<String, String> params = null;
        try {
            params = RequestOrderLogistics.queryDetailedInfo(code,id+"");
        } catch (Exception e) {
            e.printStackTrace();
        }

        mView.showProgressDialog();
        Subscription subscription = HttpUtil.getInstance(true).queryOrderLogistics(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OrderLogistics>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable err) {
                        MyApplication.closeProgressDialog();
                        mView.errEmpty();

                    }

                    @Override
                    public void onNext(OrderLogistics logistics) {
                        MyApplication.closeProgressDialog();
                        if (logistics.isSuccess()) {
                            mView.showInfo(logistics);
                        }else {
                            mView.errEmpty();
                        }
                    }
                });
        addSubcribe(subscription);
    }
}
