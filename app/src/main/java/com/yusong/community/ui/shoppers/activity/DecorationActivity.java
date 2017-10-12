package com.yusong.community.ui.shoppers.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.school.teacher.adapter.PhotoAdapter;
import com.yusong.community.ui.shoppers.bean.CommodityBean;
import com.yusong.community.ui.shoppers.bean.FenLeiBean;
import com.yusong.community.ui.shoppers.fragment.DecorationFragment;
import com.yusong.community.ui.shoppers.mvp.implView.ImplHomeView;
import com.yusong.community.ui.shoppers.mvp.presenter.impl.ImplJiaZhuanPersenterImpl;
import com.yusong.community.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 *         created at 2017/3/25 16:21.
 *         家装
 */
public class DecorationActivity extends BaseActivity implements ImplHomeView {

    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.jiazhuang_tab)
    TabLayout jiazhuangTab;
    @InjectView(R.id.jiazhuang_page)
    ViewPager jiazhuangPage;
    @InjectView(R.id.not_netword_layout)
    RelativeLayout notNetwordLayout;
    @InjectView(R.id.not_data_message)
    TextView notDataMessage;
    @InjectView(R.id.not_data_layout)
    RelativeLayout notDataLayout;
    @InjectView(R.id.decoretion_layout)
    LinearLayout decoretionLayout;

    @OnClick(R.id.ll_back)
    void backClick() {
        this.finish();
    }

    @OnClick(R.id.not_network_refresh)
    void click() {
        jiaZhuanPersenterImpl.queryHomeFenlei(2);
    }

    private ImplJiaZhuanPersenterImpl jiaZhuanPersenterImpl;
    private PhotoAdapter adapter;
    private List<Fragment> fragmentList;
    private List<String> titleList;


    @Override
    protected int layoutId() {
        return R.layout.activity_home_zhuang;
    }


    @Override
    public void initView() {
        tvTitle.setText("家装");
        fragmentList = new ArrayList<Fragment>();//页面列表
        titleList = new ArrayList<String>();  //将名称加载tab名字列表
        jiaZhuanPersenterImpl = new ImplJiaZhuanPersenterImpl(this, DecorationActivity.this);
        jiaZhuanPersenterImpl.queryHomeFenlei(2);
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
    public void close() {

    }

    @Override
    public void HomeFlSucced(List<FenLeiBean> datas) {//分类
        if (datas.size() > 1) {
            decoretionLayout.setVisibility(View.VISIBLE);
            notDataLayout.setVisibility(View.GONE);
            notNetwordLayout.setVisibility(View.GONE);
            if (datas.size() > 3) {
                jiazhuangTab.setTabMode(TabLayout.MODE_SCROLLABLE);
            } else {
                jiazhuangTab.setTabMode(TabLayout.MODE_FIXED);
            }
            for (int i = 0; i < datas.size(); i++) {
                FenLeiBean fenLeiBean = datas.get(i);
                DecorationFragment decorationFragment = new DecorationFragment();
                decorationFragment.setCategoryId(fenLeiBean.getId());
                fragmentList.add(decorationFragment);
                titleList.add(fenLeiBean.getCategoryName());
                jiazhuangTab.addTab(jiazhuangTab.newTab().setText(fenLeiBean.getCategoryName()));
            }
            adapter = new PhotoAdapter(getSupportFragmentManager(), fragmentList, titleList);
            jiazhuangPage.setAdapter(adapter);
            jiazhuangTab.setupWithViewPager(jiazhuangPage);//TabLayout加载viewpager
            jiazhuangTab.setVisibility(View.VISIBLE);
        } else if (datas.size() == 1) {
            decoretionLayout.setVisibility(View.VISIBLE);
            notDataLayout.setVisibility(View.GONE);
            notNetwordLayout.setVisibility(View.GONE);
            FenLeiBean fenLeiBean = datas.get(0);
            DecorationFragment decorationFragment = new DecorationFragment();
            decorationFragment.setCategoryId(fenLeiBean.getId());
            fragmentList.add(decorationFragment);
            titleList.add(fenLeiBean.getCategoryName());
            jiazhuangTab.addTab(jiazhuangTab.newTab().setText(fenLeiBean.getCategoryName()));
            adapter = new PhotoAdapter(getSupportFragmentManager(), fragmentList, titleList);
            jiazhuangPage.setAdapter(adapter);
            jiazhuangTab.setupWithViewPager(jiazhuangPage);//TabLayout加载viewpager
            jiazhuangTab.setVisibility(View.GONE);
        } else {
            decoretionLayout.setVisibility(View.GONE);
            notDataLayout.setVisibility(View.VISIBLE);
            notNetwordLayout.setVisibility(View.GONE);
            ToastUtils.showShort(this, "暂无分类");
        }
    }

    @Override
    public void HomeListSucced(List<CommodityBean> datas) {//列表

    }

    @Override
    public void showProgressDialog() {

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
