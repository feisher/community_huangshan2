package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.mvp.implView.IClassActionFragmentView;
import com.yusong.club.ui.school.mvp.presenter.IClassActionActivityPresenter;
import com.yusong.club.ui.school.school.bean.HuoType;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian on 2017/3/10.
 */

public class IClassActionFragmentPresenterImpl extends BasePresenterImpl<IClassActionFragmentView> implements IClassActionActivityPresenter {

    public IClassActionFragmentPresenterImpl(IClassActionFragmentView view, Context context) {
        super(view, context);
    }

    @Override
    public void categoryList(String token) {
            Subscription subscription = HttpUtil.getInstance()
                    .categoryList(token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseSubscriber<HttpResult<List<HuoType>>>(mContext) {
                        @Override
                        protected void onFailure(String err) {

                        }
                        @Override
                        protected void onSuccess(HttpResult<List<HuoType>> listHttpResult) {
                            if (listHttpResult.code == 0 ) {
                                mView.getcategoryList((List<HuoType>) listHttpResult.data);
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
