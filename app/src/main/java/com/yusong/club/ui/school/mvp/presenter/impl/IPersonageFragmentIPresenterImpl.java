package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.mvp.implView.IPersonageFragmentIView;
import com.yusong.club.ui.school.mvp.presenter.IPersonageFragmentIPresenter;
import com.yusong.club.ui.school.teacher.bean.TelBook;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian on 2017/3/13.
 */

public class IPersonageFragmentIPresenterImpl extends BasePresenterImpl<IPersonageFragmentIView> implements IPersonageFragmentIPresenter {
    public IPersonageFragmentIPresenterImpl(IPersonageFragmentIView view, Context context) {
        super(view, context);
    }

    @Override
    public void telBookList(String token) {
        Subscription subscription = HttpUtil.getInstance()
                .telBookList(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<TelBook>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.closeRefreshing();
                    }

                    @Override
                    protected void onSuccess(HttpResult<TelBook> result) {
                        mView.closeRefreshing();
                        if (result.code == 0 ) {
                            mView.getBookList(result.data);
                        } else {
                            if (!TextUtils.isEmpty(result.message)) {
                                MyApplication.showMessage(result.message);
                            }
                        }


                    }
                });
        addSubcribe(subscription);
    }

}
