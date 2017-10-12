package com.yusong.community.ui.home.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.MyApplication;
import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.home.mvp.cache.TokenInfo;
import com.yusong.community.ui.home.mvp.entity.LoginResult;
import com.yusong.community.ui.home.mvp.implView.ILoginView;
import com.yusong.community.ui.home.mvp.presenter.ILoginPresenter;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.Constants;
import com.yusong.community.utils.MD5Utils;

import org.apache.commons.lang.StringUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by quaner on 16/12/30.
 */

public class ILoginPresenterImpl extends BasePresenterImpl<ILoginView> implements ILoginPresenter {

    private String mPassword;

    public ILoginPresenterImpl(ILoginView view, Context context) {
        super(view, context);
    }

    @Override
    public void login(final String username, final String pswd, int agentId) {

        if (StringUtils.isEmpty(username)) {
            MyApplication.showMessage("账号不能为空");
            return;
        }

        if (StringUtils.isEmpty(pswd)) {
            MyApplication.showMessage("密码不能为空");
            return;
        }

        try {
            //进行加密
            mPassword = MD5Utils.md5Password(pswd);

        } catch (Exception e) {
            e.printStackTrace();
            MyApplication.showMessage("登录失败");
            return;
        }

        Subscription subscription = HttpUtil.getInstance().
                login(username, mPassword, Constants.AGENTID)
                .doOnNext(new Action1<HttpResult<LoginResult>>() {
                    @Override
                    public void call(HttpResult<LoginResult> result) {
                        if (result.code == 0) {
                            saveLoginInfo(result, username);//保存信息
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<LoginResult>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<LoginResult> result) {
                        mView.jumpHome();
                    }
                });
        //防止内存泄露
        addSubcribe(subscription);

    }



    private void saveLoginInfo(HttpResult<LoginResult> result, String username) {
        long timeMillis = System.currentTimeMillis();
        //缓存
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setName(username);
        tokenInfo.setPwd(mPassword);
        tokenInfo.setToken(result.data.token);
        tokenInfo.setId(result.data.id);
        tokenInfo.setExpireIn(result.data.expireIn);
        tokenInfo.setSaveTime(timeMillis);
        tokenInfo.setImAccount(result.data.imAccount);
        CacheUtils.saveTokenInfo(mContext, tokenInfo);
    }
}
