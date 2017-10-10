package com.yusong.club.ui.express.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.express.mvp.entity.GetDetails;
import com.yusong.club.ui.express.mvp.implView.IGetOrderDetailsView;
import com.yusong.club.ui.express.mvp.presenter.impl.IGetOrderDetailsPresenterImpl;
import com.yusong.club.utils.ActivityConstants;
import com.yusong.club.utils.AppUtils;

import org.apache.commons.lang.StringUtils;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * <ul>
 * <li>功能说明：取件详情界面</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class GetOrderDetailsActivity extends BaseActivity implements IGetOrderDetailsView {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView mIvAdaptiveDownApi18;
    @InjectView(R.id.ll_back)
    LinearLayout mLlBack;
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
    @InjectView(R.id.tv_authCode)
    TextView mTvAuthCode;
    @InjectView(R.id.tv_orderNo)
    TextView mTvOrderNo;
    @InjectView(R.id.tv_createTime)
    TextView mTvCreateTime;
    @InjectView(R.id.tv_saveTime)
    TextView mTvSaveTime;
    @InjectView(R.id.tv_getTime)
    TextView mTvGetTime;
    @InjectView(R.id.tv_boxNumber)
    TextView mTvBoxNumber;
    @InjectView(R.id.tv_money)
    TextView mTvMoney;
    @InjectView(R.id.ll_money)
    LinearLayout mLlMoney;
    @InjectView(R.id.tv_boxAddress)
    TextView mTvBoxAddress;
    @InjectView(R.id.tv_empty)
    TextView mTvEmpty;
    @InjectView(R.id.ll_info)
    LinearLayout mLlInfo;
    private IGetOrderDetailsPresenterImpl mPresenter;
    private String mOrderId;
    private String code;
    private String mName;

    @Override
    public void updateUI(GetDetails data) {
        mOrderId = data.getOrderNo();
        mName = data.getCompanyName();
        if (StringUtils.isEmpty(mName)) {
            mLlInfo.setVisibility(View.GONE);
            mTvEmpty.setVisibility(View.VISIBLE);
        } else {
            mLlInfo.setVisibility(View.VISIBLE);
            mTvEmpty.setVisibility(View.GONE);
            mTvCorporationName.setText(mName);
            mTvOrderId.setText(data.getOrderNo());
            if (!StringUtils.isEmpty(data.getOrderNo())) {
                mPresenter.scanOrder(data.getOrderNo());
            }
        }
        mTvAuthCode.setText(data.getAuthCode() + "");
        mTvCreateTime.setText(data.getCreateTime());
        mTvSaveTime.setText(data.getPutTime());
        mTvGetTime.setText(data.getTakeTime());
        mTvBoxNumber.setText(data.getBoxNum());
        mTvOrderNo.setText(data.getId());
        mTvName.setText(data.getCourierName());
        mTvMobile.setText(data.getCourierMobile());
    }

    @Override
    public void setIcon(String code) {
        this.code = code;
        AppUtils.setIcon(mIvIcon, code);
    }


    @Override
    protected void initListener() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_express_getorder_details;
    }

    @Override
    public void initView() {
        mPresenter = new IGetOrderDetailsPresenterImpl(this, this);
        mTvTitle.setText("订单详情");
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        mPresenter.queryGetDetails(intent.getStringExtra("id"));
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
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }


    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(this);
    }
}
