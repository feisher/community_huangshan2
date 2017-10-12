package com.yusong.community.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.community.MyApplication;
import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.school.mvp.implView.IUploadPhotoActivityView;
import com.yusong.community.ui.school.mvp.presenter.IUploadPhotoActivityPresenter;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian on 2017/3/11.
 */

public class IUploadPhotoActivityPresenterImpl extends BasePresenterImpl<IUploadPhotoActivityView> implements IUploadPhotoActivityPresenter {
    public IUploadPhotoActivityPresenterImpl(IUploadPhotoActivityView view, Context context) {
        super(view, context);
    }

    @Override
    public void uploadPhotoFile(RequestBody token, RequestBody albumId, MultipartBody.Part image1) {
        Subscription subscription = HttpUtil.getInstance()
                .uploadPhotoFile(token,albumId,image1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<String>>(mContext) {
                    @Override
                    protected void onFailure(String err) {
                        mView.onError();

                    }
                    @Override
                    protected void onSuccess(HttpResult<String> listHttpResult) {
                        if (listHttpResult.code==0) {
                            MyApplication.showMessage("上传成功");
                            mView.upLoadPhotoSucess(listHttpResult.message);
                        }else {
                            if (!TextUtils.isEmpty(listHttpResult.message)) {
                                MyApplication.showMessage(listHttpResult.message);
                            }else {
                                MyApplication.showMessage("上传失败");
                            }
                        }
                    }
                });
        addSubcribe(subscription);
    }
}
