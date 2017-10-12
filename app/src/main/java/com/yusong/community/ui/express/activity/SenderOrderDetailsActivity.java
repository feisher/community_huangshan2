package com.yusong.community.ui.express.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.express.mvp.entity.SaveDetails;
import com.yusong.community.ui.express.mvp.entity.SenderDetails;
import com.yusong.community.ui.express.mvp.implView.ISenderOrderDetailsView;
import com.yusong.community.ui.express.mvp.presenter.impl.ISenderOrderDetailsPresenterImpl;
import com.yusong.community.utils.ActivityConstants;
import com.yusong.community.utils.AppUtils;

import org.apache.commons.lang.StringUtils;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * <ul>
 * <li>功能说明：寄件详情界面</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class SenderOrderDetailsActivity extends BaseActivity implements ISenderOrderDetailsView {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView mIvAdaptiveDownApi18;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.iv_icon)
    ImageView mIvIcon;
    @InjectView(R.id.tv_corporationName)
    TextView mTvCorporationName;
    @InjectView(R.id.tv_orderId)
    TextView mTvOrderId;
    @InjectView(R.id.btn_look)
    Button mBtnLook;
    @InjectView(R.id.tv_name)
    TextView mTvName;
    @InjectView(R.id.tv_mobile)
    TextView mTvMobile;
    @InjectView(R.id.iv_mobile)
    ImageView mIvMobile;
    @InjectView(R.id.tv_senderName)
    TextView mTvSenderName;
    @InjectView(R.id.tv_senderPhone)
    TextView mTvSenderPhone;
    @InjectView(R.id.tv_senderAddress)
    TextView mTvSenderAddress;
    @InjectView(R.id.tv_getName)
    TextView mTvGetName;
    @InjectView(R.id.tv_getPhone)
    TextView mTvGetPhone;
    @InjectView(R.id.tv_getAddress)
    TextView mTvGetAddress;
    @InjectView(R.id.tv_money)
    TextView mTvMoney;
    @InjectView(R.id.tv_type)
    TextView mTvType;
    @InjectView(R.id.tv_boxType)
    TextView mTvBoxType;
    @InjectView(R.id.tv_boxNumber)
    TextView mTvBoxNumber;
    @InjectView(R.id.tv_orderNumber)
    TextView mTvOrderNumber;
    @InjectView(R.id.tv_createTime)
    TextView mTvCreateTime;
    @InjectView(R.id.tv_getTime)
    TextView mTvGetTime;
    @InjectView(R.id.tv_mome)
    TextView mTvMome;
    @InjectView(R.id.tv_empty)
    TextView mTvEmpty;
    @InjectView(R.id.ll_info)
    LinearLayout mLlInfo;
    private ISenderOrderDetailsPresenterImpl mPresenter;
    private String mOrderId;
    private String code;
    private String mName;

    @Override
    public void updateUI(SenderDetails data) {
        mOrderId = data.getOrderNo();
        mName = (String) data.getCompanyName();
        if (StringUtils.isEmpty(mName)) {
            mLlInfo.setVisibility(View.GONE);
            mTvEmpty.setVisibility(View.VISIBLE);
        } else {
            mLlInfo.setVisibility(View.VISIBLE);
            mTvEmpty.setVisibility(View.GONE);

            mTvCorporationName.setText(data.getCourierName());
            mTvOrderId.setText(data.getOrderNo());
            if (!StringUtils.isEmpty(data.getOrderNo())) {
                mPresenter.scanOrder(data.getOrderNo());
            }
        }
        if (data.getBoxType() == 1) {
            mTvBoxType.setText("小箱");
        } else if (data.getBoxType() == 2) {
            mTvBoxType.setText("中箱");
        } else if (data.getBoxType() == 3) {
            mTvBoxType.setText("大箱");
        }
        mTvName.setText(data.getCourierName());
        mTvMobile.setText(data.getCourierMobile());
        mTvSenderName.setText(data.getSender());
        mTvSenderPhone.setText(data.getSenderMobile());
        mTvSenderAddress.setText(data.getSenderAddress());
        mTvGetName.setText(data.getReceiver());
        mTvGetPhone.setText(data.getReceiverMobile());
        mTvGetAddress.setText(data.getReceiverAddress());
        mTvMoney.setText(data.getCharge()+"");
        mTvBoxNumber.setText(data.getThingAmount() + "件");
        mTvType.setText(data.getThing());
        mTvOrderNumber.setText(data.getId());
        mTvMome.setText(data.getMemo() + "");
        mTvCreateTime.setText(data.getCreateTime());
        mTvGetTime.setText(data.getTakeTime());

    }

    @Override
    public void setIcon(String code) {
        this.code = code;
        AppUtils.setIcon(mIvIcon,code);
    }

    @Override
    public void updateUI(SaveDetails data) {

    }


    @OnClick({R.id.ll_back, R.id.btn_look, R.id.iv_mobile})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.btn_look:
                Intent mIntent = new Intent(this, LogisticsInfoActivity.class);
                mIntent.putExtra(ActivityConstants.ORDER_ID, mOrderId);
                mIntent.putExtra(ActivityConstants.ShipperCode, code);
                mIntent.putExtra(ActivityConstants.ShipperName, mName);
                startActivity(mIntent);
                break;
            case R.id.iv_mobile:
                String phoneNumber = mTvMobile.getText().toString();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
                break;
        }
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        mPresenter.querySenderDetails(intent.getStringExtra("id"));
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_express_senderorder_details;
    }

    @Override
    public void initView() {
        mPresenter = new ISenderOrderDetailsPresenterImpl(this, this);
        mTvTitle.setText("订单详情");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(this);
    }
}
