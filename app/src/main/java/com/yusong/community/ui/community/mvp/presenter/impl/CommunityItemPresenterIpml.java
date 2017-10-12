package com.yusong.community.ui.community.mvp.presenter.impl;

import android.app.AlertDialog;
import android.content.Context;

import com.yusong.community.MyApplication;
import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.community.mvp.entity.Posts;
import com.yusong.community.ui.community.mvp.implView.ICommunityItemView;
import com.yusong.community.ui.community.mvp.presenter.ICommunityItemPresenter;
import com.yusong.community.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by feisher on 2017/1/10.
 */
public class CommunityItemPresenterIpml extends BasePresenterImpl<ICommunityItemView> implements ICommunityItemPresenter {

    public AlertDialog.Builder builder;

    public CommunityItemPresenterIpml(ICommunityItemView view, Context context) {
        super(view, context);
    }

    @Override
    public void requestGetCommubityItemFragementItem(int type, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance()
                .queryPostsList(CacheUtils.getToken(mContext),type,limit,offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<Posts>>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.closeRefreshing();
                        MyApplication.showMessage("网络繁忙");
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<Posts>> listHttpResult) {
                        mView.closeRefreshing();
                        if (listHttpResult.data!=null) {
                            mView.setFragmentItemAdapter((List<Posts>) listHttpResult.data);
                            List<Posts> data = (List<Posts>) listHttpResult.data;
                            if (data.size()==0) {
                                MyApplication.showMessage("没有更多数据了");
                            }
                        }
                    }
                });

        addSubcribe(subscription);

    }

}
