package com.yusong.club.ui.community.mvp.presenter.impl;

import android.content.Context;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.community.mvp.implView.CreatePostActivityView;
import com.yusong.club.ui.community.mvp.presenter.ICreatePostActivityPresenter;
import com.yusong.club.ui.express.mvp.entity.Contact;

import org.apache.commons.lang.StringUtils;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.yusong.club.MyApplication.showMessage;

/**
 * Created by feisher on 2017/1/14.
 */
public class CreatePostActivityPresenterImpl extends BasePresenterImpl<CreatePostActivityView> implements ICreatePostActivityPresenter {

    public CreatePostActivityPresenterImpl(CreatePostActivityView mCreatePostActivityView, Context context) {
        super(mCreatePostActivityView, context);
    }

    @Override
    public void createPost(RequestBody token, RequestBody categoryId, RequestBody content, MultipartBody.Part image1, MultipartBody.Part image2, MultipartBody.Part image3, MultipartBody.Part image4) {
        Subscription subscription = HttpUtil.getInstance()
                .createPost(token, categoryId, content, image1, image2, image3, image4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<Contact>>() {
                    @Override
                    protected void onFailure(String err) {
                        showMessage("网络繁忙！");
                    }

                    @Override
                    protected void onSuccess(HttpResult<Contact> contactHttpResult) {
                        if (contactHttpResult.code == 0) {
                            mView.createPostSucceedCallback();
                            MyApplication.showMessage("发帖成功！");
                        } else if (StringUtils.isNotEmpty(contactHttpResult.message)) {
                            MyApplication.showMessage(contactHttpResult.message);
                        }
                    }
                });
        addSubcribe(subscription);
    }


    @Override
    public void createPost(RequestBody token, RequestBody categoryId, RequestBody content, MultipartBody.Part image1, MultipartBody.Part image2, MultipartBody.Part image3) {
        Subscription subscription = HttpUtil.getInstance()
                .createPost(token, categoryId, content, image1, image2, image3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<Contact>>() {
                    @Override
                    protected void onFailure(String err) {
                    }

                    @Override
                    protected void onSuccess(HttpResult<Contact> contactHttpResult) {
                        if (contactHttpResult.code == 0) {
                            mView.createPostSucceedCallback();
                            showMessage("发帖成功！");
                        } else if (StringUtils.isNotEmpty(contactHttpResult.message)) {
                            showMessage(contactHttpResult.message);
                        }

                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void createPost(RequestBody token, RequestBody categoryId, RequestBody content, MultipartBody.Part image1, MultipartBody.Part image2) {
        Subscription subscription = HttpUtil.getInstance()
                .createPost(token, categoryId, content, image1, image2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<Contact>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<Contact> contactHttpResult) {
                        if (contactHttpResult.code == 0) {
                            mView.createPostSucceedCallback();
                            showMessage("发帖成功！");
                        } else if (StringUtils.isNotEmpty(contactHttpResult.message)) {
                            showMessage(contactHttpResult.message);
                        }
                    }
                });
        addSubcribe(subscription);
    }


    @Override
    public void createPost(RequestBody token, RequestBody categoryId, RequestBody content, MultipartBody.Part image1) {
        Subscription subscription = HttpUtil.getInstance()
                .createPost(token, categoryId, content, image1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<Contact>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<Contact> contactHttpResult) {
                        if (contactHttpResult.code == 0) {
                            mView.createPostSucceedCallback();
                            showMessage("发帖成功！");
                        } else if (StringUtils.isNotEmpty(contactHttpResult.message)) {
                            showMessage(contactHttpResult.message);
                        }
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void createPost(RequestBody token, RequestBody categoryId, RequestBody content) {
        Subscription subscription = HttpUtil.getInstance()
                .createPost(token, categoryId, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<Contact>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<Contact> contactHttpResult) {
                        if (contactHttpResult.code == 0) {
                            mView.createPostSucceedCallback();
                        } else if (StringUtils.isNotEmpty(contactHttpResult.message)) {
                            MyApplication.showMessage(contactHttpResult.message);
                        }
                    }
                });
        addSubcribe(subscription);
    }

}
