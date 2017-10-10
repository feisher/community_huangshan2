package com.yusong.club.ui.express.mvp.presenter.impl;

import android.content.Context;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.yusong.club.MyApplication;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.mvp.implPresenter.BasePresenterImpl;
import com.yusong.club.ui.express.mvp.entity.Contact;
import com.yusong.club.ui.express.mvp.implView.IFillInfoView;
import com.yusong.club.ui.express.mvp.presenter.IFillInfoPresenter;
import com.yusong.club.utils.ActivityConstants;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.RegexUtils;
import com.yusong.club.utils.ToastUtils;

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
public class IFillInfoPresenterImpl extends BasePresenterImpl<IFillInfoView>
        implements IFillInfoPresenter {


    private String mAddress;
    private String mPhone;
    private String mName;
    private String mCity;
    private int info_fill;

    //用于添加联系人
    String provinceCode;
    String cityCode;
    String districtCode;
    private int contact_id;

    public IFillInfoPresenterImpl(IFillInfoView view, Context context) {
        super(view, context);
    }


    @Override
    public void commit(EditText name, EditText phone, TextView tv_city, EditText address,
                       CheckBox cbDftSender, CheckBox cbSava, int info_fill,
                       String province, String city, String couny, String provinceCode,
                       String cityCode, String districtCode, int contact_id, int request_id) {

        this.info_fill = info_fill;
        this.provinceCode = provinceCode;
        this.cityCode = cityCode;
        this.districtCode = districtCode;
        this.contact_id = contact_id;

        mAddress = address.getText().toString().trim();
        mPhone = phone.getText().toString().trim();
        mName = name.getText().toString().trim();
        mCity = tv_city.getText().toString().trim();

        if (StringUtils.isEmpty(mName)) {
            MyApplication.showMessage("请填写姓名");
            return;
        }
        if (StringUtils.isEmpty(mPhone)) {
            MyApplication.showMessage("请填写手机号");
            return;
        }
        if (!RegexUtils.checkMobile(mPhone)) {
            ToastUtils.showShort(mContext, "请输入正确的手机号");
            return;
        }

        if ("请选择省市区".equals(mCity)) {
            MyApplication.showMessage("请选择省市区");
            return;
        }

        if (StringUtils.isEmpty(mAddress)) {
            MyApplication.showMessage("请填写详细地址");
            return;
        }

        Intent mIntent = new Intent();
        mIntent.putExtra(ActivityConstants.INFO_NAME, mName);
        mIntent.putExtra(ActivityConstants.INFO_ADDRESS, mAddress);
        mIntent.putExtra(ActivityConstants.INFO_PHONE, mPhone);
        mIntent.putExtra(ActivityConstants.INFO_CITY, mCity);
        mIntent.putExtra(ActivityConstants.CITY, city);
        mIntent.putExtra(ActivityConstants.PROVINCE, province);
        mIntent.putExtra(ActivityConstants.COUNY, couny);
        mIntent.putExtra(ActivityConstants.CITY_CODE, cityCode);
        mIntent.putExtra(ActivityConstants.PROVINCE_CODE, provinceCode);
        mIntent.putExtra(ActivityConstants.DISTRICT_CODE, districtCode);

        addOrUpdata(cbDftSender, cbSava, info_fill, mIntent, request_id);


    }


    /**
     * 判断添加还是修改
     *
     * @param cbDftSender
     * @param cbSava
     * @param info_fill
     * @param mIntent
     */
    private void addOrUpdata(CheckBox cbDftSender, CheckBox cbSava, int info_fill, Intent mIntent, int request_id) {

        if (info_fill == 1 || info_fill == 2 || info_fill == 3) {

            if (info_fill == 1) {
                mView.result(ActivityConstants.INFO_SENDER_RESPONS, mIntent);
            } else if (info_fill == 2) {
                mView.result(ActivityConstants.INFO_GET_RESPONS, mIntent);
            }
            if (cbDftSender.isChecked()) {
                addContact(1, 0);
            }
            if (cbSava.isChecked()) {
                addContact(0, 1);
            }

        }

        if (info_fill == 4 || info_fill == 6 || info_fill == 7) {

            if (cbDftSender.isChecked()) {
                mView.result(ActivityConstants.INFO_SENDER_RESPONS, mIntent);
                UpdataContact(1, 0, request_id);
            }
            if (cbSava.isChecked()) {
                mView.result(ActivityConstants.INFO_GET_RESPONS, mIntent);
                UpdataContact(0, 1, request_id);
            }
        }

    }

    /**
     * 修改联系人
     */
    private void UpdataContact(int dftSender, int dftGet, int request_id) {

        int id = 0;
        if (info_fill == 6 || info_fill == 7) {
            id = request_id;
        }

        if (info_fill != 7 && info_fill != 6) {
            id = contact_id;
        }

        Subscription subscription = HttpUtil.getInstance().
                updateContact(CacheUtils.getToken(mContext)
                        , id, mName, provinceCode, cityCode, districtCode, mPhone, mAddress, dftSender, dftGet)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult result) {
                        mView.close();
                    }
                });
        addSubcribe(subscription);
    }


    /**
     * 保存信息
     */
    private void addContact(int dftSender, int dftGet) {
        Subscription subscription = HttpUtil.getInstance().addContact(CacheUtils.getToken(mContext),
                mName, provinceCode, cityCode, districtCode, mPhone, mAddress, dftSender, dftGet)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<Contact>>(mContext) {
                    @Override
                    protected void onFailure(String err) {

                    }

                    @Override
                    protected void onSuccess(HttpResult<Contact> result) {
                        mView.close();
                    }
                });

        addSubcribe(subscription);

    }
}
