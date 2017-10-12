package com.yusong.community.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.community.MyApplication;
import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.school.mvp.implView.IEditEvaluateActivityView;
import com.yusong.community.ui.school.mvp.presenter.IEditEvaluateActivityPresenter;
import com.yusong.community.utils.ToastUtils;

import org.apache.commons.lang.StringUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ds on 2017/3/17.
 */

public class IEditEvaluateActivityPresenterImpl extends BasePresenterImpl<IEditEvaluateActivityView> implements IEditEvaluateActivityPresenter {
    public IEditEvaluateActivityPresenterImpl(IEditEvaluateActivityView view, Context context) {
        super(view, context);
    }

    @Override
    public void StuCommentCreate(String token, int studentId, int period, int star1, int star2, int star3, int star4, String content) {
        if (studentId == -1) {
            ToastUtils.showShort(mContext, "学生还没选择");
            return;

        }
        if (StringUtils.isEmpty(content)) {
            ToastUtils.showShort(mContext, "学生评价不能为空");
            return;
        }

        Subscription subscription = HttpUtil.getInstance()
                .StuCommentCreate(token, studentId, period, star1, star2, star3, star4, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<String>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<String> listHttpResult) {
                        if (listHttpResult.code == 0) {
                            mView.StuCommentCreate(listHttpResult.data);
                        } else {
                            if (!TextUtils.isEmpty(listHttpResult.message)) {
                                MyApplication.showMessage(listHttpResult.message);
                            } else {
                                MyApplication.showMessage("评论失败");
                            }
                        }
                    }
                });
        addSubcribe(subscription);


    }

}
