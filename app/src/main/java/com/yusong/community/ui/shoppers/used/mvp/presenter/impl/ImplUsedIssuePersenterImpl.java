package com.yusong.community.ui.shoppers.used.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.shoppers.used.mvp.implView.ImplUsedIssueView;
import com.yusong.community.ui.shoppers.used.mvp.presenter.ImplUsedIssuePersenter;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 *         crated at on 2017/3/18.
 *         描述: 发布or修改 二手商品
 */

public class ImplUsedIssuePersenterImpl extends BasePresenterImpl<ImplUsedIssueView> implements ImplUsedIssuePersenter {
    public ImplUsedIssuePersenterImpl(ImplUsedIssueView view, Context context) {
        super(view, context);
    }

    @Override
    public void issueUsed(RequestBody token ,RequestBody categoryId ,RequestBody introduction, MultipartBody.Part image1, MultipartBody.Part image2,
                          MultipartBody.Part image3, MultipartBody.Part image4, RequestBody price,
                          RequestBody showPrice, RequestBody address, RequestBody lng, RequestBody lat) {
        Subscription subscription = HttpUtil.getInstance().issueUsed(token,categoryId, introduction, image1, image2, image3, image4, price, showPrice, address, lng, lat)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>(mContext) {
                    @Override
                    protected void onFailure(String err) {
                        mView.issueClose();
                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.issueSucced();
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void editUsed(RequestBody token ,RequestBody id,RequestBody categoryId , RequestBody introduction, MultipartBody.Part image1,
                         MultipartBody.Part image2, MultipartBody.Part image3, MultipartBody.Part image4,
                         RequestBody price, RequestBody showPrice, RequestBody address, RequestBody lng, RequestBody lat) {
        Subscription subscription = HttpUtil.getInstance().editUsed(token, id,categoryId, introduction, image1, image2, image3, image4, price, showPrice, address, lng, lat)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>(mContext) {
                    @Override
                    protected void onFailure(String err) {
                        mView.issueClose();
                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.editSucced();
                    }
                });
        addSubcribe(subscription);
    }
}
