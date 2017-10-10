package com.yusong.club.ui.shoppers.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.community_service.activity.ServiceOrderActivity;
import com.yusong.club.ui.supermarket.SuperMarketOrderActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 *         crated at on 2017/3/27.
 *         描述: 商品支付成功
 */

public class ShopPaySuccedActivity extends BaseActivity {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.pay_type_tv)
    TextView payTypeTv;
    @InjectView(R.id.pay_num_tv)
    TextView payNumTv;
    @InjectView(R.id.look_order_btn)
    Button lookOrderBtn;
    @InjectView(R.id.next_shop_btn)
    Button nextShopBtn;

    @OnClick({R.id.ll_back, R.id.look_order_btn, R.id.next_shop_btn})
    void allClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                break;
            case R.id.look_order_btn:
                if(shopType==1){
                    Intent intent = new Intent(this, ShopOrderActivity.class);
                    startActivity(intent);
                }else if(shopType==2){
                    Intent intent = new Intent(this, SuperMarketOrderActivity.class);
                    startActivity(intent);
                }else if(shopType == 3){
                    Intent intent = new Intent(this, ServiceOrderActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.next_shop_btn:
                break;
            default:
                break;
        }
        this.finish();
    }
    private int shopType;

    @Override
    protected int layoutId() {
        return R.layout.activity_shop_pay_succed;
    }

    @Override
    public void initData() {
        tvTitle.setText("支付成功");
        Intent intent = getIntent();
        String price = intent.getStringExtra("price");
        shopType = intent.getIntExtra("shopType",0);
        payNumTv.setText(price);
        int patType = intent.getIntExtra("payType", -1);
        switch (patType) {
            case 0:
                payTypeTv.setText("智联");
                break;
            case 1:
                payTypeTv.setText("支付宝");
                break;
            case 2:
                payTypeTv.setText("微信");
                break;
            default:
                payTypeTv.setText("其他");
                break;
        }
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
