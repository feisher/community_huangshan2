package com.yusong.club.ui.express.mvp.presenter.impl;

import android.content.Context;
import android.content.Intent;

import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.express.mvp.entity.ContactGroup;
import com.yusong.club.ui.express.mvp.implView.IAddressView;
import com.yusong.club.ui.express.mvp.presenter.IAddressPresenter;
import com.yusong.club.utils.ActivityConstants;
import com.yusong.club.utils.CacheUtils;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */
public class IAddressPresenterImpl extends BasePresenterImpl<IAddressView>
        implements IAddressPresenter {
    public List<ContactGroup> data;

    public IAddressPresenterImpl(IAddressView view, Context context) {
        super(view, context);

    }

    //查询联系人
    @Override
    public void queryContact(int type) {

        Subscription subscription = HttpUtil.getInstance().queryContact(CacheUtils.getToken(mContext), type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<List<ContactGroup>>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<List<ContactGroup>> result) {
                        data = result.data;
                        mView.setContactAdapter(result.data);
                    }
                });
        addSubcribe(subscription);
    }


    /**
     * 条目点击事件
     *
     * @param data
     */
    @Override
    public void onItemClick(ContactGroup data, int mInfo_fill) {

        if (mInfo_fill == 3) {//如果从个人信息过来 直接不用操作
            return;
        }
        if (mInfo_fill == 4) {//商城订单
            Intent shopIntent = new Intent();
            shopIntent.putExtra("ContactGroup", data);
            mView.result(ActivityConstants.INFO_GET_RESPONS, shopIntent);
            mView.close();
            return;
        }

        String mPhone = data.getMobile();
        String mName = data.getContactName();
        String mCity = data.getProvinceName() + data.getCityName() + data.getDistrictName();//省市区
        String mAddress = data.getStreet();//街道

        Intent mIntent = new Intent();
        mIntent.putExtra(ActivityConstants.INFO_ID, data.getId());
        mIntent.putExtra(ActivityConstants.INFO_NAME, mName);
        mIntent.putExtra(ActivityConstants.INFO_ADDRESS, mAddress);
        mIntent.putExtra(ActivityConstants.INFO_PHONE, mPhone);
        mIntent.putExtra(ActivityConstants.INFO_CITY, mCity);
        mIntent.putExtra(ActivityConstants.PROVINCE_CODE, data.getProvince());
        mIntent.putExtra(ActivityConstants.CITY_CODE, data.getCity());
        mIntent.putExtra(ActivityConstants.DISTRICT_CODE, data.getDistrict());
        if (mInfo_fill == 1) {
            mView.result(ActivityConstants.INFO_SENDER_RESPONS, mIntent);
        } else if (mInfo_fill == 2) {
            mView.result(ActivityConstants.INFO_GET_RESPONS, mIntent);
        } else if (mInfo_fill == 10) {
            mView.result(ActivityConstants.INFO_SENDER_RESPONS, mIntent);
        } else if (mInfo_fill == 11) {
            mView.result(ActivityConstants.INFO_GET_RESPONS, mIntent);
        }
        mView.close();
    }

    /**
     * 删除联系人
     *
     * @param position
     */
    @Override
    public void deleteContact(final int position) {
        final ContactGroup group = data.get(position);
        Subscription subscription = HttpUtil.getInstance()
                .deleteContact(CacheUtils.getToken(mContext), group.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult result) {
                        mView.notifyAdapter();
                    }
                });
        addSubcribe(subscription);
    }


    //设置默认联系人
    @Override
    public void setDftContact(int id, int type) {

        Subscription subscription = HttpUtil.getInstance()
                .dftContact(CacheUtils.getToken(mContext), id, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult result) {
                        mView.notifyAdapter();
                    }
                });
        addSubcribe(subscription);
    }
}
