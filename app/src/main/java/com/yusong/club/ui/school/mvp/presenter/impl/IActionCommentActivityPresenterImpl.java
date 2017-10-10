package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.mvp.implView.IActionCommentActivityView;
import com.yusong.club.ui.school.mvp.presenter.IActionCommentActivityPresenter;
import com.yusong.club.ui.school.school.bean.Comment;
import com.yusong.club.utils.ToastUtils;

import org.apache.commons.lang.StringUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian on 2017/3/16.
 */

public class IActionCommentActivityPresenterImpl extends BasePresenterImpl<IActionCommentActivityView> implements IActionCommentActivityPresenter {
    public IActionCommentActivityPresenterImpl(IActionCommentActivityView view, Context context) {
        super(view, context);
    }

    @Override
    public void searchCommentList(String token, int activityId, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance()
                .searchCommentList(token, activityId, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<Comment>>>(mContext) {
                    @Override
                    protected void onFailure(String err) {
                        mView.closeRefreshing();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<Comment>> listHttpResult) {
                        mView.closeRefreshing();
                        if (listHttpResult.code == 0 && listHttpResult.data != null) {
                            mView.getCommentList((List<Comment>) listHttpResult.data);
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

    @Override
    public void createComment(String token, int activityId, String content) {
        if (StringUtils.isEmpty(content)) {
            ToastUtils.show(mContext, "发表内容不能为空", Toast.LENGTH_LONG);
            return;
        }
        Subscription subscription = HttpUtil.getInstance()
                .createComment(token, activityId, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<String>>(mContext) {
                    @Override
                    protected void onFailure(String err) {
                        mView.closeRefreshing();
                    }

                    @Override
                    protected void onSuccess(HttpResult<String> listHttpResult) {
                        mView.closeRefreshing();
                        if (listHttpResult.code == 0) {
                            mView.createComment(listHttpResult.data);
                        } else {
                            if (!TextUtils.isEmpty(listHttpResult.message)) {
                                MyApplication.showMessage(listHttpResult.message);
                            } else {
                                MyApplication.showMessage("发表失败");
                            }
                        }
                    }
                });
        addSubcribe(subscription);


    }
}
