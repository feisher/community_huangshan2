package com.yusong.community.ui.community_notice.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.community_notice.mvp.presenter.CommunityNoticePresenter;
import com.yusong.community.ui.community_notice.mvp.implview.CommunityNoticeView;
import com.yusong.community.ui.community_notice.entity.NoticeBean;
import com.yusong.community.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Mr_Peng
 * @created at 2017-09-06.
 * @describe: 社区公告
 */

public class CommunityNoticePresenterImpl extends BasePresenterImpl<CommunityNoticeView> implements CommunityNoticePresenter {
    @Override
    public void queryNotic(int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance().queryCommunityNotice(CacheUtils.getToken(mContext), offset, limit).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<NoticeBean>>>() {
                    @Override
                    protected void onFailure(String err) {
                        mView.queryError();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<NoticeBean>> listHttpResult) {
                        mView.querySucced(listHttpResult.data);
                    }
                });
        addSubcribe(subscription);
    }

    public CommunityNoticePresenterImpl(CommunityNoticeView view, Context context) {
        super(view, context);
    }
}
