package com.yusong.community.ui.supermarket;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
 * @created at 2017-09-06.
 * @describe: null
 */

public class SuperMarketOrderActivity extends BaseActivity {
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tabLayout)
    TabLayout tabLayout;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;

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
        tvTitle.setText("超市订单");
        String str[] = {"all", "payed", "wait_pay", "delivered", "canceled"};
        tabNameList.add("全部");
        tabNameList.add("已付款");
        tabNameList.add("未付款");
        tabNameList.add("已完成");
        tabNameList.add("已取消");
        tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置TabLayout的模式
        for (int i = 0; i < tabNameList.size(); i++) {
            SuperMarketOrderFragment superMarketFragment = new SuperMarketOrderFragment();
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
