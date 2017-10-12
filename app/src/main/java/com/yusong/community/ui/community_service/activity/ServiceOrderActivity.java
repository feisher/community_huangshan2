package com.yusong.community.ui.community_service.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.school.teacher.adapter.PhotoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 * @created at 2017-09-22.
 * @describe: 社区服务订单
 */

public class ServiceOrderActivity extends BaseActivity {
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

    @Override
    protected int layoutId() {
        return R.layout.activity_shop_order;
    }

    private List<String> tabNameList = new ArrayList<String>();
    private List<Fragment> fragmentList = new ArrayList<Fragment>();

    @Override
    public void initData() {
        tvTitle.setText("服务订单");
        String str[] = {"all", "payed", "wait_pay", "delivered", "canceled"};
        tabNameList.add("全部");
        tabNameList.add("已付款");
        tabNameList.add("未付款");
        tabNameList.add("已完成");
        tabNameList.add("已取消");
        tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置TabLayout的模式
        for (int i = 0; i < tabNameList.size(); i++) {
            ServiceOrderFragment superMarketFragment = new ServiceOrderFragment();
            superMarketFragment.setQueryType(str[i]);
            fragmentList.add(superMarketFragment);
            tabLayout.addTab(tabLayout.newTab().setText(tabNameList.get(i)));
        }
        viewPager.setAdapter(new PhotoAdapter(getSupportFragmentManager(), fragmentList, tabNameList));
        tabLayout.setupWithViewPager(viewPager);//TabLayout加载viewpager
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
