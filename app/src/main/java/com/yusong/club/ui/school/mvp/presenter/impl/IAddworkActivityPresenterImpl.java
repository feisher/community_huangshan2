package com.yusong.club.ui.school.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.school.mvp.implView.IAddworkActivityView;
import com.yusong.club.ui.school.mvp.presenter.IAddworkActivityPresenter;
import com.yusong.club.ui.school.teacher.bean.Course;

import org.apache.commons.lang.StringUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ruanjian on 2017/3/11.
 */

public class IAddworkActivityPresenterImpl extends BasePresenterImpl<IAddworkActivityView> implements IAddworkActivityPresenter {
    public IAddworkActivityPresenterImpl(IAddworkActivityView view, Context context) {
        super(view, context);
    }

    @Override
    public void createClasswork(String token, int courseId, String content, String workDate) {
        if (StringUtils.isEmpty(content)) {
            MyApplication.showMessage("课程内容不能为空");
            return;
        }

        if (StringUtils.isEmpty(workDate)) {
            MyApplication.showMessage("日期不能为空");
            return;
        }

        Subscription subscription = HttpUtil.getInstance()
                .createClasswork(token,  courseId, content, workDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<String>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<String> listHttpResult) {
                        if (listHttpResult.code == 0 ) {
                            mView.createClassWork(listHttpResult.data);
                        } else {
                            if (!TextUtils.isEmpty(listHttpResult.message)) {
                                MyApplication.showMessage(listHttpResult.message);
                            } else {
                                MyApplication.showMessage("创建失败");
                            }
                        }
                    }
                });
        addSubcribe(subscription);


    }

    @Override
    public void searchCourseList(String token) {
        Subscription subscription = HttpUtil.getInstance()
                .searchCourseList(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<Course>>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<Course>> listHttpResult) {
                        if (listHttpResult.code == 0 && listHttpResult.data != null) {
                            mView.getCourseList((List<Course>) listHttpResult.data);
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
