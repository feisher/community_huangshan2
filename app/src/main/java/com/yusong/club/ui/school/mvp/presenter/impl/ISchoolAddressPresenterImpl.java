package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.mvp.implView.ISchoolAddressView;
import com.yusong.club.ui.school.mvp.presenter.ISchoolAddressPresenter;
import com.yusong.club.ui.school.school.bean.AddressBean;
import com.yusong.club.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 *         crated at on 2017/6/1.
 *         描述: 查询校训录
 */

public class ISchoolAddressPresenterImpl extends BasePresenterImpl<ISchoolAddressView> implements ISchoolAddressPresenter {
    @Override
    public void querySchoolAddressTwo() {
        Subscription subscription = HttpUtil.getInstance()
                .schoolTelBookList2(CacheUtils.getToken(mContext))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<AddressBean>>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<AddressBean>> listHttpResult) {
                        if(listHttpResult.data.size()>0){
                            mView.queryAddressViewSucced(listHttpResult.data);
                        }else{
                            mView.listSizeNull();
                        }
                    }
                });
        addSubcribe(subscription);
    }

    public ISchoolAddressPresenterImpl(ISchoolAddressView view, Context context) {
        super(view, context);
    }
}
