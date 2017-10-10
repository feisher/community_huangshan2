package com.yusong.club.ui.shoppers.used.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.map.LocationService;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.shoppers.used.fragment.MyUsedFragment;
import com.yusong.club.ui.shoppers.used.fragment.UsedFragment;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 *         created at 2017/3/16 16:28.
 *         二手市场主页
 */

public class UsedHomeActivity extends BaseActivity {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.frame_layout)
    FrameLayout frameLayout;
    @InjectView(R.id.ershoushichang_radio)
    RadioButton ershoushichangRadio;
    @InjectView(R.id.ershou_wo_radio)
    RadioButton ershouWoRadio;
    @InjectView(R.id.ershou_tab)
    RadioGroup ershouTab;
    @InjectView(R.id.ershou_fabu_button)
    ImageView ershouFabuButton;

    private LocationService locationService;
    private Fragment fragment = null;

    @OnClick({R.id.ll_back, R.id.ershou_fabu_button, R.id.ershou_wo_radio, R.id.ershoushichang_radio})
    void allClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.ershoushichang_radio://二手市场
                jumpFragment(R.id.ershoushichang_radio);
                break;
            case R.id.ershou_wo_radio://我发布的
                jumpFragment(R.id.ershou_wo_radio);
                break;
            case R.id.ershou_fabu_button://发布
                Intent intent = new Intent(this, IssueActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_used_home;
    }

    @Override
    public void initView() {
        tvTitle.setText("二手市场");
        ershoushichangRadio.setChecked(true);
        locationService = new LocationService(this);
        jumpFragment(R.id.ershoushichang_radio);
    }

    private void jumpFragment(int checkedId) {
        String tag = "";
        switch (checkedId) {
            case R.id.ershoushichang_radio:
                fragment = new UsedFragment();
                tvTitle.setText("跳蚤市场");
                tag = "UsedFragment";
                break;
            case R.id.ershou_wo_radio:
                fragment = new MyUsedFragment();
                tvTitle.setText("我发布的");
                tag = "MyUsedFragment";
                break;
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, fragment, tag)
                .commit();
    }

    @Override
    public void initData() {

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
    protected void onDestroy() {
        locationService.stop();
        super.onDestroy();
    }
}
