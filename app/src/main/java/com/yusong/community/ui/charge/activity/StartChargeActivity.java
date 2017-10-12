package com.yusong.community.ui.charge.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.charge.bean.OrderDetailsBean;
import com.yusong.community.ui.charge.bean.SancContentBean;
import com.yusong.community.ui.charge.mvp.implView.ICharStartChargeView;
import com.yusong.community.ui.charge.mvp.implView.IChargeOrderDetailsView;
import com.yusong.community.ui.charge.mvp.presenter.impl.IChargeOrderDetailsPresenterImpl;
import com.yusong.community.ui.charge.mvp.presenter.impl.IChargeStartChargePresenterImpl;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Mr_Peng on 2017/1/5.
 */

public class StartChargeActivity extends BaseActivity implements ICharStartChargeView, IChargeOrderDetailsView {
    @Override
    public void startChargeSucced() {
        Intent intent = new Intent(this, InChargeActivity.class);
        if (orderDetailsBean != null) {
            intent.putExtra("OrderDetailsBean", orderDetailsBean);
        }
        startActivity(intent);
        this.finish();
    }


    @Override
    public void refreshView(OrderDetailsBean data) {
        orderDetailsBean = data;
        yuyueTime.setText(data.getCreateTime());
        yuyueShichang.setText(data.getDuration() + "分钟");
        yuyueZhandian.setText(data.getAddress());
        //yuyueRen.setText(data.getCustomerMobile());
    }

    private IChargeStartChargePresenterImpl startChargePresenterImpl;
    private OrderDetailsBean orderDetailsBean;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.iv_img)
    ImageView ivImg;
    @InjectView(R.id.ll_img)
    LinearLayout llImg;
    @InjectView(R.id.tv_text)
    TextView tvText;
    @InjectView(R.id.rl_txt)
    RelativeLayout rlTxt;
    private SancContentBean sancContentBean;

    @InjectView(R.id.start_charge_button)
    Button startChargeButton;
    @InjectView(R.id.yuyue_time)
    TextView yuyueTime;
    @InjectView(R.id.yuyue_shichang)
    TextView yuyueShichang;
    @InjectView(R.id.yuyue_zhandian)
    TextView yuyueZhandian;
    @InjectView(R.id.yuyue_ren)
    TextView yuyueRen;


    @OnClick({R.id.ll_back, R.id.start_charge_button})
    void allClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.start_charge_button:
                startChargePresenterImpl = new IChargeStartChargePresenterImpl(this, StartChargeActivity.this);
                startChargePresenterImpl.startCharge(chargeId, orderNumber);
                break;
            default:
                break;
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_charge_start_charge;
    }

    private Intent intent;
    private String chargeId;
    private String orderNumber;
    private IChargeOrderDetailsPresenterImpl iChargeOrderDetailsPresenterImpl;

    @Override
    public void initView() {
        tvTitle.setText("开始充电");
        intent = getIntent();
        chargeId = intent.getStringExtra("chargerId");
        orderNumber = intent.getStringExtra("orderId");
        iChargeOrderDetailsPresenterImpl = new IChargeOrderDetailsPresenterImpl(this, StartChargeActivity.this);
        iChargeOrderDetailsPresenterImpl.queryOrderDetails(orderNumber);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @Override
    protected void adaptiveSystemVersions() {
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= 18) {
            ivAdaptiveDownApi18.setVisibility(View.VISIBLE);
        } else {
            ivAdaptiveDownApi18.setVisibility(View.GONE);
        }
    }


    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(this);
    }
}
