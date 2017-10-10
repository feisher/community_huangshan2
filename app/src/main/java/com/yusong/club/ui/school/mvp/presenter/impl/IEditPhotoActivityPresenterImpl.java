package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.mvp.entity.EditSucessResult;
import com.yusong.club.ui.school.mvp.implView.IEditPhotoActivityView;
import com.yusong.club.ui.school.mvp.presenter.IEditPhotoActivityPresenter;
import com.yusong.club.utils.ToastUtils;

import org.apache.commons.lang.StringUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian on 2017/3/29.
 */

public class IEditPhotoActivityPresenterImpl extends BasePresenterImpl<IEditPhotoActivityView> implements IEditPhotoActivityPresenter {
    public IEditPhotoActivityPresenterImpl(IEditPhotoActivityView view, Context context) {
        super(view, context);
    }

    @Override
    public void editPhoto(String token, int albumId, String memo, String albumName) {
        if (StringUtils.isEmpty(memo)) {
            ToastUtils.showShort(mContext, "相册描述不能为空");
            return;
        }
        if (StringUtils.isEmpty(memo)) {
            ToastUtils.showShort(mContext, "相册名称不能为空");
            return;
        }

        Subscription subscription = HttpUtil.getInstance()
                .editPhoto(token, albumId, memo, albumName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<EditSucessResult>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<EditSucessResult> listHttpResult) {
                        if (listHttpResult.code == 0) {
                            mView.editPhotoSucess(listHttpResult.data);
                        } else {
                            if (!TextUtils.isEmpty(listHttpResult.message)) {
                                MyApplication.showMessage(listHttpResult.message);
                            } else {
                                MyApplication.showMessage("编辑失败");
                            }
                        }
                    }
                });
        addSubcribe(subscription);


    }


}
