package com.yusong.club.ui.me.mvp.presenter.impl;

import android.content.Context;
import android.text.TextUtils;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.me.mvp.entity.UserInfo;
import com.yusong.club.ui.me.mvp.implView.IMyInfoActivityView;
import com.yusong.club.ui.me.mvp.presenter.IMyInfoActivityPresenter;
import com.yusong.club.ui.school.mvp.entity.Role;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.SPUtils;
import com.yusong.club.utils.ToastUtils;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by feisher on 2017/1/21.
 */
public class MyInfoActivityPresenterImpl extends BasePresenterImpl<IMyInfoActivityView> implements IMyInfoActivityPresenter {

    public MyInfoActivityPresenterImpl(IMyInfoActivityView v, Context mContext) {
        super(v,mContext);
    }

    @Override
    public void upDateUserInfo(RequestBody nickname, final RequestBody realName, RequestBody  gender, RequestBody  mail, MultipartBody.Part  portrait) {
        RequestBody token = RequestBody.create(
                MediaType.parse("multipart/form-data"), CacheUtils.getToken(mContext));
        Subscription subscription = HttpUtil.getInstance()
                .updateUserInfo(token,nickname,realName,gender,mail,portrait)
                .doOnNext(new Action1<HttpResult<UserInfo>>() {
                    @Override
                    public void call(HttpResult<UserInfo> userInfoHttpResult) {
                        if (userInfoHttpResult.data!=null) {
                            SPUtils.saveObject(mContext, "UserInfo", userInfoHttpResult.data);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<UserInfo>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<UserInfo> userInfoHttpResult) {
                        if (userInfoHttpResult.code==0&&userInfoHttpResult.data!=null) {
                            mView.updateUserInfoCallback((UserInfo) userInfoHttpResult.data);
                        }else {
                            if (!TextUtils.isEmpty(userInfoHttpResult.message)) {
                                MyApplication.showMessage(userInfoHttpResult.message);
                            }
                        }
                    }
                });

        addSubcribe(subscription);
    }

    @Override
    public void updatePassword( String oldPassword, String newPassword) {
        String token =  CacheUtils.getToken(mContext);
        Subscription subscription = HttpUtil.getInstance()
                .updatePassword(token,oldPassword,newPassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<String>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<String> stringHttpResult) {
                        if (!TextUtils.isEmpty(stringHttpResult.message)) {
                            ToastUtils.showShort(mContext,stringHttpResult.message);
                        }
                        if (stringHttpResult.code ==0) {
                            MyApplication.showMessage("密码修改成功，请重新登陆");
                            mView.updatePasswordCallback();

                        }
                    }
                });

        addSubcribe(subscription);
    }

    @Override
    public void queryRoleList(String token) {
        Subscription subscription = HttpUtil.getInstance()
                .roleList(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<Role>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<Role> listHttpResult) {
                        if (listHttpResult.code==0&&listHttpResult.data!=null) {
//                            mView.setAdapter4ListDataCallback((List<HaveCommunityCity>)result.data);
                            mView.roleListCallback(listHttpResult.data);
                        }else {
                            if (!TextUtils.isEmpty(listHttpResult.message)) {
//                                BaseActivity.showMessage(listHttpResult.message);
                            }
                        }
                    }
                });

        addSubcribe(subscription);
    }

//    @Override
//    public void qureyUserInfo(String token) {
//         ();
//        Subscription subscription = HttpUtil.getInstance()
//                .qureyUserInfo(token)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<HttpResult>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//                    @Override
//                    public void onError(String err) {
//                        mView.hideLoading();
//                        mView.showMessage("网络繁忙！");
//                    }
//
//                    @Override
//                    public void onNext(HttpResult result) {
//                        mView.hideLoading();
////                        mView.getUserInfo((UserInfo) result.data);
//                        UserInfo mUserInfo = (UserInfo) result.data;
//                        if (mUserInfo!=null) {
//                            SPUtils.saveObject(mContext,"UserInfo",mUserInfo);
//                        }
//                    }
//                });
//
//        addSubcribe(subscription);
//    }
}
