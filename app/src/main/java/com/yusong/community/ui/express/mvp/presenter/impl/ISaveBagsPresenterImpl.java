package com.yusong.community.ui.express.mvp.presenter.impl;

import android.content.Context;
import android.widget.EditText;

import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.express.mvp.entity.CommitOrderResult;
import com.yusong.community.ui.express.mvp.entity.DisclaimerBean;
import com.yusong.community.ui.express.mvp.implView.ISaveBagsView;
import com.yusong.community.ui.express.mvp.presenter.ISaveBagsPresenter;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.RegexUtils;
import com.yusong.community.utils.ToastUtils;

import org.apache.commons.lang.StringUtils;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class ISaveBagsPresenterImpl extends BasePresenterImpl<ISaveBagsView> implements ISaveBagsPresenter {

    public ISaveBagsPresenterImpl(ISaveBagsView view, Context context) {
        super(view, context);
    }

    @Override
    public void commitOrder(EditText et_phone, EditText et_mome) {

        String phone = et_phone.getText().toString().trim();
        final String mome = et_mome.getText().toString().trim();
        if (StringUtils.isEmpty(phone)) {
            ToastUtils.showShort(mContext, "手机号码不能为空");
            return;
        }
        if (!RegexUtils.checkMobile(phone)) {
            ToastUtils.showShort(mContext, "请输入正确的手机号");
            return;
        }

        Subscription subscription = HttpUtil.getInstance().commitSaveOrder(CacheUtils.getToken(mContext), phone, mome)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<CommitOrderResult>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<CommitOrderResult> result) {
                        mView.commitSuccess(result.data);
                    }
                });
        addSubcribe(subscription);

    }

    @Override
    public void queryDisclaimer() {
        Subscription subscription = HttpUtil.getInstance().queryDisclaimer(CacheUtils.getToken(mContext))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<DisclaimerBean>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<DisclaimerBean> result) {
                        mView.queryUrlSuccess(result.data);
                    }
                });
        addSubcribe(subscription);
    }
}
