package com.yusong.community.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.community.MyApplication;
import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.school.mvp.entity.TelBookData;
import com.yusong.community.ui.school.mvp.implView.IAddressActivityView;
import com.yusong.community.ui.school.mvp.presenter.IAddressActivityPresenter;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian on 2017/3/11.
 */

public class IAddressActivityPresenterImpl extends BasePresenterImpl<IAddressActivityView> implements IAddressActivityPresenter {
    public IAddressActivityPresenterImpl(IAddressActivityView view, Context context) {
        super(view, context);
    }

    @Override
    public void schoolTelBookList(String token) {
        Subscription subscription = HttpUtil.getInstance()
                .schoolTelBookList(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<TelBookData>>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<TelBookData>> listHttpResult) {
                        if (listHttpResult.code == 0 && listHttpResult.data != null) {
                            mView.getschoolTelBookList((List<TelBookData>) listHttpResult.data);
                        } else {
                            if (!TextUtils.isEmpty(listHttpResult.message)) {
                                MyApplication.showMessage(listHttpResult.message);
                            } else {
                                MyApplication.showMessage("没有更多数据了");
                            }
                        }
                    }
                });
        addSubcribe(subscription);





    }
}
