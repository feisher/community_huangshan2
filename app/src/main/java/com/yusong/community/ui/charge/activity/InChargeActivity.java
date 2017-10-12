package com.yusong.community.ui.charge.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.charge.bean.OrderDetailsBean;
import com.yusong.community.ui.charge.bean.SancContentBean;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Mr_Peng on 2017/1/4.
 */

public class InChargeActivity extends BaseActivity {

//    @InjectView(R.id.timer_view)
//    TimerView timerView;
    @InjectView(R.id.yuyue_time)
    TextView yuyueTime;
    @InjectView(R.id.yuyue_shichang)
    TextView yuyueShichang;
    @InjectView(R.id.yuyue_zhandian)
    TextView yuyueZhandian;
    @InjectView(R.id.yuyue_ren)
    TextView yuyueRen;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.find_et)
    EditText findEt;
    @InjectView(R.id.iv_img)
    ImageView ivImg;
    @InjectView(R.id.ll_img)
    LinearLayout llImg;
    @InjectView(R.id.tv_text)
    TextView tvText;
    @InjectView(R.id.rl_txt)
    RelativeLayout rlTxt;

    @OnClick(R.id.ll_back)
    void backClick() {
        this.finish();
    }

    @Override
    protected void initListener() {
        tvTitle.setText("正在充电");
//        timerView.setSecond(3400, 3500);
        SancContentBean sancContentBean=(SancContentBean)getIntent().getSerializableExtra("SancContentBean");
        OrderDetailsBean orderDetailsBean=(OrderDetailsBean)getIntent().getSerializableExtra("OrderDetailsBean");
        if(sancContentBean!=null){
            yuyueTime.setText(sancContentBean.getCreateTime());
            yuyueShichang.setText(sancContentBean.getDuration() + "分钟");
            yuyueZhandian.setText(sancContentBean.getAddress());
            yuyueRen.setText(sancContentBean.getCustomerMobile());
        }else if(orderDetailsBean!=null){
            yuyueTime.setText(orderDetailsBean.getCreateTime());
            yuyueShichang.setText(orderDetailsBean.getDuration() + "分钟");
            yuyueZhandian.setText(orderDetailsBean.getAddress());
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_charge_in_charge;
    }

    @Override
    public void initView() {

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
}
