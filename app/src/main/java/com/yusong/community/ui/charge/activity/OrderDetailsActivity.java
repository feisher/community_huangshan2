package com.yusong.community.ui.charge.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.charge.bean.MyOrderBean;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Mr_Peng on 2017/2/20.
 */

public class OrderDetailsActivity extends BaseActivity {
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
    @InjectView(R.id.charge_type_tv)
    TextView chargeTypeTv;
    @InjectView(R.id.chongdian_time_tv)
    TextView chongdianTimeTv;
    @InjectView(R.id.yuyue_dianliang_tv)
    TextView yuyueDianliangTv;
    @InjectView(R.id.yuyue_zhuangdian_tv)
    TextView yuyueZhuangdianTv;
    @InjectView(R.id.yuyue_dizhi_tv)
    TextView yuyueDizhiTv;
    @InjectView(R.id.xiadan_time_tv)
    TextView xiadanTimeTv;
    @InjectView(R.id.zhifu_jine_tv)
    TextView zhifuJineTv;
    @InjectView(R.id.pay_fang_shi_tv)
    TextView payFangShiTv;
    @InjectView(R.id.shi_fu_jine_tv)
    TextView shiFuJineTv;

    @OnClick(R.id.ll_back)
    void backClick() {
        this.finish();
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
    protected void initListener() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_charge_order_details;
    }

    @Override
    public void initView() {
        tvTitle.setText("订单详情");
        MyOrderBean bean = (MyOrderBean) getIntent().getSerializableExtra("OrderBean");
        chargeTypeTv.setText(bean.getChargerType() == 1 ? "快充" : "慢充");
        chongdianTimeTv.setText(String.format("%s分钟", bean.getDuration()));
        yuyueDianliangTv.setText(bean.getVolume()+"%");
        yuyueZhuangdianTv.setText(bean.getChargerName());
        yuyueDizhiTv.setText(bean.getAddress());
        xiadanTimeTv.setText(bean.getCreateTime());
        zhifuJineTv.setText("￥" + bean.getPrice());
        int payTyte = bean.getPayType();
        if (payTyte == 1) {
            payFangShiTv.setText("余额");
        } else if (payTyte == 2) {
            payFangShiTv.setText("微信");
        } else if (payTyte == 3) {
            payFangShiTv.setText("支付宝");
        } else {
            payFangShiTv.setText("无");
        }
        shiFuJineTv.setText("￥" + bean.getPrice());
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
}
