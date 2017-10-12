package com.yusong.community.ui.home.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.home.fragment.WebFragment;
import com.yusong.community.ui.school.teacher.adapter.PhotoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 * @created at 2017-07-27.
 * @describe: 智慧旅游
 */

public class GiraActivity extends BaseActivity {
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
    @InjectView(R.id.find_layout)
    RelativeLayout findLayout;
    @InjectView(R.id.left_radio)
    RadioButton leftRadio;
    @InjectView(R.id.center_radio)
    RadioButton centerRadio;
    @InjectView(R.id.right_radio)
    RadioButton rightRadio;
    @InjectView(R.id.radio_group)
    RadioGroup radioGroup;
    @InjectView(R.id.left_radio_address)
    RadioButton leftRadioAddress;
    @InjectView(R.id.right_radio_address)
    RadioButton rightRadioAddress;
    @InjectView(R.id.radio_group_address)
    RadioGroup radioGroupAddress;
    @InjectView(R.id.iv_img)
    ImageView ivImg;
    @InjectView(R.id.ll_img)
    LinearLayout llImg;
    @InjectView(R.id.tv_text)
    TextView tvText;
    @InjectView(R.id.rl_txt)
    RelativeLayout rlTxt;
    @InjectView(R.id.app_title_layout)
    LinearLayout appTitleLayout;
    @InjectView(R.id.tabLayout)
    TabLayout tabLayout;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;

    @OnClick(R.id.ll_back)
    void backClick() {
        this.finish();
    }

    private List<String> tabNameList = new ArrayList<String>();
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    private WebFragment fragment, fragment1;
    String scenicTicketUrl="";
    String hotelUrl="";

    @Override
    protected int layoutId() {
        return R.layout.activity_gira;
    }

    @Override
    public void initView() {
        scenicTicketUrl = getIntent().getStringExtra("scenicTicketUrl");
        hotelUrl = getIntent().getStringExtra("hotelUrl");
    }

    @Override
    public void initData() {
        tvTitle.setText("智慧旅游");
        tabNameList.add("景点");
        tabNameList.add("酒店");
        fragment = new WebFragment();
        fragment.setUrl(scenicTicketUrl);
        fragmentList.add(fragment);
        fragment1 = new WebFragment();
        fragment1.setUrl(hotelUrl);
        fragmentList.add(fragment1);
        viewPager.setAdapter(new PhotoAdapter(getSupportFragmentManager(), fragmentList, tabNameList));
        tabLayout.setupWithViewPager(viewPager);//TabLayout加载viewpager
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        int nowPager = viewPager.getCurrentItem();
        if (nowPager == 0 && fragment.webView.canGoBack()) {
            fragment.webView.goBack();
            return true;
        }
        if (nowPager == 1 && fragment1.webView.canGoBack()) {
            fragment1.webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
