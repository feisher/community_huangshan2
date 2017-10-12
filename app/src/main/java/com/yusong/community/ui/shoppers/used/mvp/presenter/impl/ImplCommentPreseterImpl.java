package com.yusong.community.ui.shoppers.used.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.shoppers.used.bean.UsedCommentBean;
import com.yusong.community.ui.shoppers.used.mvp.implView.ImplCommentView;
import com.yusong.community.ui.shoppers.used.mvp.presenter.ImplCommentPreseter;
import com.yusong.community.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 *         crated at on 2017/3/22.
 *         描述: 二手详情页面
 */

public class ImplCommentPreseterImpl extends BasePresenterImpl<ImplCommentView> implements ImplCommentPreseter {

    @Override
    public void queryComment(int itemId, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance().queryUsedPinlun(CacheUtils.getToken(mContext), itemId, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<UsedCommentBean>>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.close();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<UsedCommentBean>> listHttpResult) {
                        mView.queryCommentSucced(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void reply(int commentId, String content) {
        Subscription subscription = HttpUtil.getInstance().replyComment(CacheUtils.getToken(mContext),
                commentId, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.close();
                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.replySucced();
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void comment(int itemId, String content) {
        Subscription subscription = HttpUtil.getInstance().usedComment(CacheUtils.getToken(mContext),
                itemId, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.close();
                    }

                    @Override
                    protected void onSuccess(HttpResult httpResult) {
                        mView.commentSucced();
                    }
                });
        addSubcribe(subscription);
    }

    public ImplCommentPreseterImpl(ImplCommentView view, Context context) {
        super(view, context);
    }
}
