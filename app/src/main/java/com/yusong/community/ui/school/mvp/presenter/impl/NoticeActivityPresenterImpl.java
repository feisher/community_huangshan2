package com.yusong.community.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.community.MyApplication;
import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.school.mvp.entity.Notice;
import com.yusong.community.ui.school.mvp.implView.INoticeActivityView;
import com.yusong.community.ui.school.mvp.presenter.INoticeActivityPresenter;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by feisher on 2017/2/24.
 */

public class NoticeActivityPresenterImpl extends BasePresenterImpl<INoticeActivityView> implements INoticeActivityPresenter {

    public NoticeActivityPresenterImpl(INoticeActivityView view, Context context) {
        super(view, context);
    }

    @Override
    public void queryPublicNoticeList(String token, int offset, int limit, final boolean isRefresh) {
        Subscription subscription = HttpUtil.getInstance()
                .public_noticeList(token,offset,limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<Notice>>>(mContext) {
                    @Override
                    protected void onFailure(String err) {
                        mView.closeRefreshing();
                    }

                    @Override
                    protected void onSuccess(HttpResult<List<Notice>> listHttpResult) {
                        mView.closeRefreshing();
                        if (listHttpResult.code==0) {
                            mView.noticeDataCallback((List<Notice>) listHttpResult.data);
                            if (listHttpResult.data.size()==0) {
                                if (isRefresh==true) {
                                    MyApplication.showMessage("没有数据");
                                }else {
                                    MyApplication.showMessage("没有更多数据了");
                                }
                            }
                        }else {
                            if (!TextUtils.isEmpty(listHttpResult.message)) {
                                MyApplication.showMessage(listHttpResult.message);
                            }else {
                                MyApplication.showMessage("没有更多数据了");
                            }
                        }
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void queryTeacherNoticeList(String token, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance()
                .teacherNoticeList(token,offset,limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<Notice>>>(mContext) {
                    @Override
                    protected void onFailure(String err) {
                        mView.closeRefreshing();
                    }
                    @Override
                    protected void onSuccess(HttpResult<List<Notice>> stringHttpResult) {
                        mView.closeRefreshing();
                        if (stringHttpResult.code==0&&stringHttpResult.data!=null) {
                            mView.noticeTeacherDataCallback((List<Notice>) stringHttpResult.data);
                        }else {
                            if (!TextUtils.isEmpty(stringHttpResult.message)) {
                                MyApplication.showMessage(stringHttpResult.message);
                            }
                        }
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void queryGuardianNoticeList(String token, int offset, int limit) {
        Subscription subscription = HttpUtil.getInstance()
                .guardianNoticeList(token,offset,limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<Notice>>>(mContext) {
                    @Override
                    protected void onFailure(String err) {
                        mView.closeRefreshing();
                    }
                    @Override
                    protected void onSuccess(HttpResult<List<Notice>> stringHttpResult) {
                        mView.closeRefreshing();
                        if (stringHttpResult.code==0&&stringHttpResult.data!=null) {
                            mView.noticeTeacherDataCallback((List<Notice>) stringHttpResult.data);
                        }else {
                            if (!TextUtils.isEmpty(stringHttpResult.message)) {
                                MyApplication.showMessage(stringHttpResult.message);
                            }
                        }
                    }
                });
        addSubcribe(subscription);



    }

    @Override
    public void deleteNotice(String token, int id, final int position) {
        Subscription subscription = HttpUtil.getInstance()
                .deleteNotice(token,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<String>>(mContext) {
                    @Override
                    protected void onFailure(String err) {
                    }

                    @Override
                    protected void onSuccess(HttpResult<String> listHttpResult) {
                        if (listHttpResult.code==0) {
                          MyApplication.showMessage("删除成功");
                            mView.refreshList(position);
                        }else {
                            if (!TextUtils.isEmpty(listHttpResult.message)) {
                                MyApplication.showMessage(listHttpResult.message);
                            }
                        }
                    }
                });
        addSubcribe(subscription);
    }
}
