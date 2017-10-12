package com.yusong.community.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.community.MyApplication;
import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.school.mvp.implView.IUpLoadStudyVideoActivityView;
import com.yusong.community.ui.school.mvp.presenter.IUpLoadStudyVideoActivityPresenter;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian on 2017/3/28.
 */

public class IUpLoadStudyVideoActivityPresenterImpl extends BasePresenterImpl<IUpLoadStudyVideoActivityView> implements IUpLoadStudyVideoActivityPresenter {
    public IUpLoadStudyVideoActivityPresenterImpl(IUpLoadStudyVideoActivityView view, Context context) {
        super(view, context);
    }


    @Override
    public void uploadVideoFile(RequestBody token, RequestBody albumId, MultipartBody.Part image1) {
        Subscription subscription = HttpUtil.getInstance()
                .uploadVideoFile(token, albumId, image1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<String>>(mContext) {
                    @Override
                    protected void onFailure(String err) {
                        mView.onError();

                    }

                    @Override
                    protected void onSuccess(HttpResult<String> listHttpResult) {
                        if (listHttpResult.code == 0) {
                            MyApplication.showMessage("上传成功");
                            mView.upLoadVideoSucess(listHttpResult.message);
                        } else {
                            if (!TextUtils.isEmpty(listHttpResult.message)) {
                                MyApplication.showMessage(listHttpResult.message);
                            } else {
                                MyApplication.showMessage("上传失败");
                            }
                        }
                    }
                });
        addSubcribe(subscription);
    }
}
