package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.mvp.implView.IEducationActivityView;
import com.yusong.club.ui.school.mvp.presenter.IEducationActivityPresenter;
import com.yusong.club.ui.school.teacher.bean.StudyVideo;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian on 2017/3/27.
 */

public class IEducationActivityPresenterImpl extends BasePresenterImpl<IEducationActivityView> implements IEducationActivityPresenter {
    public IEducationActivityPresenterImpl(IEducationActivityView view, Context context) {
        super(view, context);
    }

    @Override
    public void deleteStudyVideo(String token, int videoId) {
        Subscription subscription = HttpUtil.getInstance()
                .deleteStudyVideo(token, videoId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<String>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<String> listHttpResult) {
                        if (listHttpResult.code == 0) {
                            MyApplication.showMessage("删除成功");
                            mView.deleteVideo(listHttpResult.data);
                        } else {
                            if (!TextUtils.isEmpty(listHttpResult.message)) {
                                MyApplication.showMessage(listHttpResult.message);
                            } else {
                                MyApplication.showMessage("删除失败");
                            }
                        }
                    }
                });
        addSubcribe(subscription);


    }

    @Override
    public void studyVideoList(String token, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance()
                .studyVideoList(token, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<StudyVideo>>>(mContext) {
                    @Override
                    protected void onFailure(String err) {
                        mView.closeRefreshing();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<StudyVideo>> listHttpResult) {
                        mView.closeRefreshing();
                        if (listHttpResult.code == 0 ) {
                            mView.getVideoAlbumList( listHttpResult.data);
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
