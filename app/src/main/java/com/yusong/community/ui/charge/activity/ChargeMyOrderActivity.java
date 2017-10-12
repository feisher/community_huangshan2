package com.yusong.community.ui.charge.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.map.LocationService;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.charge.fragment.WeifuFragment;
import com.yusong.community.ui.charge.fragment.YifuFragment;
import com.yusong.community.ui.charge.fragment.YiwanchengFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Mr_Peng on 2016/12/30.
 */

public class ChargeMyOrderActivity extends BaseActivity {
    Fragment fragment = null;

    @InjectView(R.id.left_radio)
    RadioButton weifukuanRadio;
    @InjectView(R.id.center_radio)
    RadioButton yifukuanRadio;
    @InjectView(R.id.right_radio)
    RadioButton yiwanchnengRadio;
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
    @InjectView(R.id.radio_group)
    RadioGroup radioGroup;
    @InjectView(R.id.iv_img)
    ImageView ivImg;
    @InjectView(R.id.ll_img)
    LinearLayout llImg;
    @InjectView(R.id.tv_text)
    TextView tvText;
    @InjectView(R.id.rl_txt)
    RelativeLayout rlTxt;
    @InjectView(R.id.order_fragment_layout)
    FrameLayout orderFragmentLayout;

    @Override
    protected int layoutId() {
        return R.layout.activity_charge_myorder;
    }

    @Override
    public void initView() {
        radioGroup.setVisibility(View.VISIBLE);
        tvTitle.setText("我的订单");
        weifukuanRadio.setChecked(true);
        fragment = new WeifuFragment();
        jumpFragment();
        mService = new LocationService(this);

    }

    @OnClick({R.id.left_radio, R.id.center_radio, R.id.right_radio})
    void radioClick(View view) {
        switch (view.getId()) {
            case R.id.left_radio:
                if (!(fragment instanceof WeifuFragment)) {
                    fragment = new WeifuFragment();
                    jumpFragment();
                }
                break;
            case R.id.center_radio:
                if (!(fragment instanceof YifuFragment)) {
                    fragment = new YifuFragment();
                    jumpFragment();
                }
                break;
            case R.id.right_radio:
                if (!(fragment instanceof YiwanchengFragment)) {
                    fragment = new YiwanchengFragment();
                    jumpFragment();
                }
                break;
            default:
        }

    }

    @OnClick(R.id.ll_back)
    void backClick() {
        this.finish();
    }

    private LocationService mService;

    @Override
    protected void initListener() {

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

    private void jumpFragment() {
        if (fragment == null) {
            return;
        }
        //使用指定的fragment切换当前页面
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.order_fragment_layout, fragment)
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mService.stop();
    }
}
