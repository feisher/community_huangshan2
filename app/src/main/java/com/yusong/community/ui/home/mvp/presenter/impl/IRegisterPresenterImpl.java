package com.yusong.community.ui.home.mvp.presenter.impl;

import android.content.Context;
import android.widget.EditText;

import com.yusong.community.MyApplication;
import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.home.mvp.entity.AuthCodeResult;
import com.yusong.community.ui.home.mvp.entity.Protocol;
import com.yusong.community.ui.home.mvp.implView.IRegisterView;
import com.yusong.community.ui.home.mvp.presenter.IRegisterPresenter;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.Constants;
import com.yusong.community.utils.MD5Utils;

import org.apache.commons.lang.StringUtils;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by quaner on 17/1/3.
 */

public class IRegisterPresenterImpl extends BasePresenterImpl<IRegisterView>
        implements IRegisterPresenter {

    public IRegisterPresenterImpl(IRegisterView view, Context context) {
        super(view, context);
    }

    /**
     * 用于判断用户是否注册
     *
     * @param phone
     */
    @Override
    public void getCode(EditText phone) {

        String phoneNumber = phone.getText().toString().trim();

        if (StringUtils.isEmpty(phoneNumber)){
            MyApplication.showMessage("手机号码不能为空！");
            return;
        }

        HttpUtil.getInstance().getAuthCode(phoneNumber, Constants.AGENTID)
                .doOnNext(new Action1<HttpResult<AuthCodeResult>>() {
                    @Override
                    public void call(HttpResult<AuthCodeResult> result) {
                        if (result.code == 0) {
                            startCountdown(60);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<AuthCodeResult>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<AuthCodeResult> result) {
                        MyApplication.showMessage("获取成功");
                    }
                });

    }

    /**
     * 回显倒计时
     */
    @Override
    public void isStartCountdown() {
        int time = (int) CacheUtils.getCountdown(mContext);
        if (time != 0) {
            startCountdown(time);
        }
    }


    /**
     * 用户注册
     *
     * @param phone
     * @param code
     * @param pswd
     * @param okpwd
     */
    @Override
    public void register(EditText phone, EditText code, EditText pswd, EditText okpwd) {
        //获取输入
        final String mPhone = phone.getText().toString().trim();
        final String mCode = code.getText().toString().trim();
        String mPswd = okpwd.getText().toString().trim();
        String mOkpwd = okpwd.getText().toString().trim();
        if (StringUtils.isEmpty(mPhone)) {
            MyApplication.showMessage("手机号码不能为空!");
            return;
        }
        if (StringUtils.isEmpty(mCode)) {
            MyApplication.showMessage("短信验证码不能为空!");
            return;
        }
        if (StringUtils.isEmpty(mPswd) || StringUtils.isEmpty(mOkpwd)) {
            MyApplication.showMessage("密码不能为空!");
            return;
        }
        if (!mPswd.equals(mOkpwd)){
            MyApplication.showMessage("输入密码不一致!");
            return;
        }

        final String password = MD5Utils.md5Password(mOkpwd);

        HttpUtil.getInstance().register(mPhone,password,mCode,Constants.AGENTID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult result) {
                        MyApplication.showMessage("注册成功");
                        mView.close();
                    }
                });

    }

    @Override
    public void revisePwd(EditText phone, EditText code, EditText pswd, EditText okpwd) {
        //获取输入
        String mPhone = phone.getText().toString().trim();
        final String mCode = code.getText().toString().trim();
        String mPswd = okpwd.getText().toString().trim();
        String mOkpwd = okpwd.getText().toString().trim();
        if (StringUtils.isEmpty(mPhone)) {
            MyApplication.showMessage("手机号码不能为空!");
            return;
        }
        if (StringUtils.isEmpty(mCode)) {
            MyApplication.showMessage("短信验证码不能为空!");
            return;
        }
        if (StringUtils.isEmpty(mPswd) || StringUtils.isEmpty(mOkpwd)) {
            MyApplication.showMessage("密码不能为空!");
            return;
        }
        if (!mPswd.equals(mOkpwd)){
            MyApplication.showMessage("输入密码不一致!");
            return;
        }

        String password = MD5Utils.md5Password(mOkpwd);

        HttpUtil.getInstance().revisePwd(mPhone,password,mCode,Constants.AGENTID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult result) {

                        MyApplication.showMessage("修改成功");
                        mView.close();
                    }
                });

    }


    /**
     * 开启倒计时
     *
     * @param time
     */
    @Override
    public void startCountdown(final int time) {

        Observable.interval(0, 1, TimeUnit.SECONDS)//设置0延迟，每隔一秒发送一条数据
                .take(time + 1)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return time - aLong; //
                    }
                })
                .doOnNext(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        CacheUtils.savaCountdown(mContext, aLong);
                    }
                })
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.start();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())//操作UI主要在UI线程
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {
                        mView.recovery();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Long aLong) { //接受到一条就是会操作一次UI
                        mView.upDataTime(aLong);
                    }
                });
    }

    public void queryProtocol() {
        Subscription subscrption= HttpUtil.getInstance().queryProtocol(Constants.AGENTID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<Protocol>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<Protocol> result) {
                        mView.jumpActivity(result.data);
                    }
                });
        addSubcribe(subscrption);


    }
}
