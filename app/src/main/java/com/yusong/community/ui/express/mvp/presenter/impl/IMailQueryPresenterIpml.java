package com.yusong.community.ui.express.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.MyApplication;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.express.mvp.entity.ScanOrder;
import com.yusong.community.ui.express.mvp.implView.IMailQueryView;
import com.yusong.community.ui.express.mvp.presenter.IMailQueryPresenter;
import com.yusong.community.ui.express.mvp.request.RequestOrderLogistics;

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

public class IMailQueryPresenterIpml extends BasePresenterImpl<IMailQueryView>
        implements IMailQueryPresenter {

    public IMailQueryPresenterIpml(IMailQueryView view, Context context) {
        super(view, context);
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
                        mView.jump();
                    }

                    @Override
                    public void onNext(ScanOrder order) {
                        MyApplication.closeProgressDialog();
                        if (order.isSuccess()) {
                            List<ScanOrder.ShipperInfo> shippers = order.getShippers();
                            int size = shippers.size();
                            if (size==1){
                                //只有一条直接进入详情
                                mView.jumpActivity(order.getShippers().get(0));
                            }else if (size <1 || size >1){
                                //多条或者没有 进入搜索结果
                                mView.jump();
                            }
                        }else {
                            mView.jump();
                        }
                    }
                });
        addSubcribe(subscription);

    }
}
