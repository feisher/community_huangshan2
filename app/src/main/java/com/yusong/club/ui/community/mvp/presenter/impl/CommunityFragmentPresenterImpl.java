package com.yusong.club.ui.community.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.community.mvp.entity.PostSupport;
import com.yusong.club.ui.community.mvp.entity.PostsCatogrey;
import com.yusong.club.ui.community.mvp.implView.IPostCatogreyView;
import com.yusong.club.ui.community.mvp.presenter.ICommunityFragmentPresenter;
import com.yusong.club.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by feisher on 2017/1/14.
 */
public class CommunityFragmentPresenterImpl extends BasePresenterImpl<IPostCatogreyView> implements ICommunityFragmentPresenter {

    public CommunityFragmentPresenterImpl(IPostCatogreyView mIPostCatogreyView, Context context) {
        super(mIPostCatogreyView, context);
    }

    @Override//联网查询数据
    public void queryPostCatogrey(String token) {
        Subscription subscription = HttpUtil.getInstance()
                .queryPostsCategory(CacheUtils.getToken(mContext))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<PostsCatogrey>>>() {
                    @Override
                    protected void onFailure(String err) {
                        MyApplication.showMessage("网络繁忙！");
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<PostsCatogrey>> listHttpResult) {
                        if (listHttpResult.code==0&& listHttpResult.data!=null) {
                            mView.setViewPagerAdapter((List<PostsCatogrey>) listHttpResult.data);
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
    public void commitLikePosts(int id) {
        Subscription subscription = HttpUtil.getInstance()
                .likePost(CacheUtils.getToken(mContext),id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<PostSupport>>() {
                    @Override
                    protected void onFailure(String err) {
                        MyApplication.showMessage("网络繁忙！");
                    }

                    @Override
                    protected void onSuccess(HttpResult<PostSupport> postSupportHttpResult) {
                        if (postSupportHttpResult.code==0) {
                            if (postSupportHttpResult.data != null) {
                                PostSupport mPostSupport = (PostSupport) postSupportHttpResult.data;
                                if (mPostSupport.getSupported() == 0) {
                                    MyApplication.showMessage("已取消点赞！");
                                } else {
                                    MyApplication.showMessage("点赞成功！");
                                }
                            }
                        }
                    }
                });
        addSubcribe(subscription);
    }
}
