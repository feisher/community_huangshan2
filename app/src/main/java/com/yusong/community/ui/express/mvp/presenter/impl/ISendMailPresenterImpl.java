package com.yusong.community.ui.express.mvp.presenter.impl;

import android.content.Context;

import com.yusong.community.MyApplication;
import com.yusong.community.api.BaseSubscriber;
import com.yusong.community.api.HttpResult;
import com.yusong.community.api.HttpUtil;
import com.yusong.community.mvp.implPresenter.BasePresenterImpl;
import com.yusong.community.ui.express.mvp.entity.CommitOrderResult;
import com.yusong.community.ui.express.mvp.entity.ConfigInfo;
import com.yusong.community.ui.express.mvp.entity.ContactGroup;
import com.yusong.community.ui.express.mvp.entity.RatesInfo;
import com.yusong.community.ui.express.mvp.implView.ISendMailView;
import com.yusong.community.ui.express.mvp.presenter.ISendMailPresenter;
import com.yusong.community.utils.CacheUtils;

import org.apache.commons.lang.StringUtils;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class ISendMailPresenterImpl extends BasePresenterImpl<ISendMailView> implements ISendMailPresenter {

    public ISendMailPresenterImpl(ISendMailView view, Context context) {
        super(view, context);
    }

    /**
     * 提交前判空
     *  @param senderAddress
     * @param address
     * @param thing
     * @param amount
     * @param tvCorporation
     * @param tvBox @return
     */
    @Override
    public boolean isEmpty(String senderAddress, String address, String thing, String amount,
                           String tvCorporation, String tvBox) {
        if (StringUtils.isEmpty(senderAddress)) {
            MyApplication.showMessage("寄件人不能为空");
            return false;
        }
        if (StringUtils.isEmpty(address)) {
            MyApplication.showMessage("收件人不能为空");
            return false;
        }
        if (StringUtils.isEmpty(thing)) {
            MyApplication.showMessage("类型不能为空");
            return false;
        }
        if (StringUtils.isEmpty(amount)) {
            MyApplication.showMessage("数量不能为空");
            return false;
        }
        if (StringUtils.isEmpty(tvCorporation)) {
            MyApplication.showMessage("快递公司不能为空");
            return false;
        }
        if (StringUtils.isEmpty(tvBox)) {
            MyApplication.showMessage("箱体不能为空");
            return false;
        }
        return true;
    }

    /**
     * 提交寄件订单
     *
     * @param id
     * @param thing            类型
     * @param senderName
     * @param senderPhone
     * @param senderProvince
     * @param senderCity
     * @param senderDistrict
     * @param senderAddress
     * @param name
     * @param phone
     * @param receiverProvince
     * @param receiverCity
     * @param receiverDistrict
     * @param address
     * @param thingAmount      数量
     */
    @Override
    public void commitOrder(int id, String thing, String senderName, String senderPhone, String senderProvince,
                            String senderCity, String senderDistrict, String senderAddress,
                            String name, String phone, String receiverProvince, String receiverCity,
                            String receiverDistrict, String address, int thingAmount,int charge,int boxType) {
        Subscription subscription = HttpUtil.getInstance().commitSenderOrder(CacheUtils.getToken(mContext),id,thing,senderName,senderPhone
                ,senderProvince,senderCity,senderDistrict,senderAddress,name,phone,receiverProvince,receiverCity,receiverDistrict
                ,address,thingAmount,"",charge,boxType)
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


    /**
     * 查询快递公司 物品类型
     */
    @Override
    public void queryConfig() {
        Subscription subscriptions = HttpUtil.getInstance().queryConfig(CacheUtils.getToken(mContext))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<ConfigInfo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable err) {

                    }

                    @Override
                    public void onNext(HttpResult<ConfigInfo> result) {

                        if (result.code == 0) {

                            mView.saveConfig(result.data);

                        }
                    }
                });

        addSubcribe(subscriptions);
    }

    /**
     * 查询快递资费
     *
     * @param companyId
     * @param senderProvince
     * @param senderCity
     * @param receiverProvince
     * @param receiverCity
     * @param boxType
     */
    @Override
    public void queryRates(int companyId, int senderProvince, int senderCity, int receiverProvince, int receiverCity, int boxType) {

        Subscription subscription = HttpUtil.getInstance().queryRates(CacheUtils.getToken(mContext), companyId, senderProvince, senderCity,
                receiverProvince, receiverCity, boxType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<RatesInfo>>(mContext) {
                    @Override
                    protected void onFailure(String err) {
                        mView.showRates(0);
                    }

                    @Override
                    protected void onSuccess(HttpResult<RatesInfo> result) {
                        mView.showRates(result.data.getCharge()/100);
                    }
                });
        addSubcribe(subscription);
    }

    @Override
    public void queryContact(int type) {
        Subscription subscription = HttpUtil.getInstance().queryContact(CacheUtils.getToken(mContext),type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<ContactGroup>>>() {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<ContactGroup>> result) {
                        Observable.from(result.data)
                                .filter(new Func1<ContactGroup, Boolean>() {
                                    @Override
                                    public Boolean call(ContactGroup group) {
                                        return group.getFavoriteSenderFlag() == 1 || group.getFavoriteReceiverFlag() == 1;
                                    }
                                })
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Action1<ContactGroup>() {
                                    @Override
                                    public void call(ContactGroup group) {
                                        mView.setDftInfo(group);
                                    }
                                });
                    }
                });
        addSubcribe(subscription);
    }


}
