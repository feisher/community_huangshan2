package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.mvp.implView.ICreateVideoActivityView;
import com.yusong.club.ui.school.mvp.presenter.ICreateVideoActivityPresenter;
import com.yusong.club.ui.school.teacher.bean.Result;

import org.apache.commons.lang.StringUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian on 2017/3/13.
 */

public class ICreateVideoActivityPresenterImpl extends BasePresenterImpl<ICreateVideoActivityView> implements ICreateVideoActivityPresenter {
    public ICreateVideoActivityPresenterImpl(ICreateVideoActivityView view, Context context) {
        super(view, context);
    }


    @Override
    public void createVideo_album(String token,  String albumName, String memo) {
        if (StringUtils.isEmpty(albumName)) {
            MyApplication.showMessage("相册名称不能为空");
            return;
        }
        if (StringUtils.isEmpty(memo)) {
            MyApplication.showMessage("相册描述不能为空");
            return;
        }

        Subscription subscription = HttpUtil.getInstance()
                .createVideo_album(token, albumName, memo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<Result>>(mContext) {
                    @Override
                    protected void onFailure(String err) {
                    }
                    @Override
                    protected void onSuccess(HttpResult<Result> listHttpResult) {
                        if (listHttpResult.code == 0) {
                            mView.createVideoAlbum(listHttpResult.data);
                        } else {
                            if (!TextUtils.isEmpty(listHttpResult.message)) {
                                MyApplication.showMessage(listHttpResult.message);
                            } else {
                                MyApplication.showMessage("新建失败");
                            }
                        }
                    }
                });
        addSubcribe(subscription);

    }
}
