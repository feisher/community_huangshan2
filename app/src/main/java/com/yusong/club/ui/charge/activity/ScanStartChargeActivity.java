package com.yusong.club.ui.charge.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.charge.adapter.YuYueOrderAdapter;
import com.yusong.club.ui.charge.bean.SancContentBean;
import com.yusong.club.ui.charge.mvp.implView.ICharStartChargeView;
import com.yusong.club.ui.charge.mvp.presenter.impl.IChargeStartChargePresenterImpl;
import com.yusong.club.ui.charge.view.AnimationImageView;
import com.yusong.club.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Mr_Peng on 2017/1/10.
 */

public class ScanStartChargeActivity extends BaseActivity implements ICharStartChargeView {


    @Override
    public void startChargeSucced() {
        Intent intent = new Intent(this, InChargeActivity.class);
        intent.putExtra("SancContentBean", list.get(listPosition));
        startActivity(intent);
        this.finish();
    }

    @InjectView(R.id.start_charge_image)
    AnimationImageView startChargeImage;
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
    @InjectView(R.id.iv_img)
    ImageView ivImg;
    @InjectView(R.id.ll_img)
    LinearLayout llImg;
    @InjectView(R.id.tv_text)
    TextView tvText;
    @InjectView(R.id.rl_txt)
    RelativeLayout rlTxt;
    @InjectView(R.id.yuyue_order_recyclerivew)
    RecyclerView yuyueOrderRecyclerivew;
    @InjectView(R.id.order_details_layout)
    LinearLayout orderDetailsLayout;
    private IChargeStartChargePresenterImpl startChargePresenterImpl;

    @OnClick(R.id.ll_back)
    void allClick(View view) {
        this.finish();
    }


    @Override
    protected void initListener() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_charge_scan_startcharge;
    }

    private Intent intent;
    private String sancContentBean;
    private List<SancContentBean> list = new ArrayList<SancContentBean>();
    private YuYueOrderAdapter yuYueOrderAdapter;
    private String chargeId = null;
    private String orderNumber = null;
    private int listPosition = 0;

    @Override
    public void initView() {
        tvTitle.setText("开始充电");
        intent = getIntent();
        String sancContentBean = intent.getStringExtra("SancContentBean");
        list = new Gson().fromJson(sancContentBean, new TypeToken<List<SancContentBean>>() {
        }.getType());
        if (list.size() > 1) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            yuYueOrderAdapter = new YuYueOrderAdapter(list, this);
            yuyueOrderRecyclerivew.setAdapter(yuYueOrderAdapter);
            yuyueOrderRecyclerivew.setLayoutManager(linearLayoutManager);
            yuyueOrderRecyclerivew.scrollToPosition(0);
            yuYueOrderAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, Object data, int position) {
                    listPosition = position;
                    SancContentBean bean = (SancContentBean) data;
                    chargeId = bean.getChargerId();
                    orderNumber = bean.getId();
                    yuyueOrderRecyclerivew.setVisibility(View.GONE);
                    yuyueTime.setText(bean.getCreateTime());
                    yuyueShichang.setText(bean.getDuration() + "分钟");
                    yuyueZhandian.setText(bean.getAddress());
                    yuyueRen.setText(bean.getCustomerMobile());
                    orderDetailsLayout.setVisibility(View.VISIBLE);
                }
            });
        } else if (list.size() == 1) {
            SancContentBean bean = list.get(0);
            chargeId = bean.getChargerId();
            orderNumber = bean.getId();
            yuyueOrderRecyclerivew.setVisibility(View.GONE);
            yuyueTime.setText(bean.getCreateTime());
            yuyueShichang.setText(bean.getDuration() + "分钟");
            yuyueZhandian.setText(bean.getAddress());
            yuyueRen.setText(bean.getCustomerMobile());
            orderDetailsLayout.setVisibility(View.VISIBLE);
        }
        startChargeImage.setClickListener(new AnimationImageView.ClickListener() {
            @Override
            public void onClick() {
                if (TextUtils.isEmpty(chargeId) || TextUtils.isEmpty(orderNumber)) {
                    ToastUtils.showShort(getApplicationContext(), "没有可用订单");
                    return;
                }
                startChargePresenterImpl = new IChargeStartChargePresenterImpl(ScanStartChargeActivity.this, ScanStartChargeActivity.this);
                startChargePresenterImpl.startCharge(chargeId, orderNumber);
            }
        });
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
