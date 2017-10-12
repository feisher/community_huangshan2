package com.yusong.community.ui.community.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.community.MyApplication;
import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.community.mvp.entity.PostComment;
import com.yusong.community.ui.community.mvp.implView.PostDetailActivityView;
import com.yusong.community.ui.community.mvp.presenter.IPostDetailPresenter;
import com.yusong.community.ui.express.mvp.entity.Contact;
import com.yusong.community.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by feisher on 2017/1/10.
 */
public class PostDetailActivityPresenterIpml extends BasePresenterImpl<PostDetailActivityView> implements IPostDetailPresenter {

    public PostDetailActivityPresenterIpml(PostDetailActivityView view, Context context) {
        super(view, context);
    }

    @Override
    public void queryPostComments(int postId, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance()
                .queryPostComment(CacheUtils.getToken(mContext),postId,offset,limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<PostComment>>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.closeRefresh();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<PostComment>> listHttpResult) {
                        mView.closeRefresh();
                        if (listHttpResult.code==0&&listHttpResult.data!=null) {
                            List<PostComment> data = (List<PostComment>) listHttpResult.data;
                            mView.setCommentsAdapter(data);
                            if (data.size()==0) {
                                MyApplication.showMessage("没有更多数据了");
                            }
                        }else {
                            if (!TextUtils.isEmpty(listHttpResult.message)) {
                                MyApplication.showMessage(listHttpResult.message);
                            }
                        }
                    }
                });

        addSubcribe(subscription);
    }



    @Override
    public void createPostComment(int postId, String content) {
        Subscription subscription = HttpUtil.getInstance()
                .createPostComment(CacheUtils.getToken(mContext),postId,content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<Contact>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<Contact> contactHttpResult) {
                        if (contactHttpResult.code==0) {
                            MyApplication.showMessage("评论成功");
                            mView.commentSucceedCallback();//成功回调，以便清空输入框
                        }else {
                            if (!TextUtils.isEmpty(contactHttpResult.message)) {
                                MyApplication.showMessage(contactHttpResult.message);
                            }
                        }
                    }
                });

        addSubcribe(subscription);
    }

}
