package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.mvp.implView.IUploadVideoActivityView;
import com.yusong.club.ui.school.mvp.presenter.IUploadVideoActivityPresenter;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian on 2017/3/11.
 */

public class IUploadVideoActivityPresenterImpl extends BasePresenterImpl<IUploadVideoActivityView> implements IUploadVideoActivityPresenter {


    public IUploadVideoActivityPresenterImpl(IUploadVideoActivityView view, Context context) {
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
