package com.yusong.club.ui.shoppers.activity;

import android.content.Intent;
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

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.school.teacher.adapter.PhotoAdapter;
import com.yusong.club.ui.shoppers.bean.QiangGouDaleiBean;
import com.yusong.club.ui.shoppers.fragment.RobListFragment;
import com.yusong.club.ui.shoppers.mvp.implView.ImplQueryQianggouFenleiView;
import com.yusong.club.ui.shoppers.mvp.presenter.impl.ImplQueryQianggouFenleiPresenterImpl;
import com.yusong.club.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Mr_Peng
 *         created at 2017/3/6 10:01.
 *         抢购
 */
public class RobShopDetailsActivity extends BaseActivity implements ImplQueryQianggouFenleiView {


    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
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
    private PhotoAdapter adapter;
    private List<Fragment> fragmentList;
    private List<String> titleList;

    public static int QIANGGOU_ID = -1;
    private ImplQueryQianggouFenleiPresenterImpl qianggouFenleiPresenterImpl;//抢购分类


    @Override
    public void refreshQianggoufenlei(List<QiangGouDaleiBean> data) {//抢购分类
        if (data.size() > 1) {
            for (int i = 0; i < data.size(); i++) {
                QiangGouDaleiBean qiangGouDaleiBean = data.get(i);
                RobListFragment qCommunityListFragment = new RobListFragment();
                qCommunityListFragment.setCategoryId(qiangGouDaleiBean.getId());
                fragmentList.add(qCommunityListFragment);
                titleList.add(qiangGouDaleiBean.getCategoryName());
                tabLayout.addTab(tabLayout.newTab().setText(qiangGouDaleiBean.getCategoryName()));
            }
            adapter = new PhotoAdapter(getSupportFragmentManager(), fragmentList, titleList);
            viewPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewPager);//TabLayout加载viewpager
            tabLayout.setVisibility(View.VISIBLE);
        } else if (data.size() == 1) {
            QiangGouDaleiBean qiangGouDaleiBean = data.get(0);
            RobListFragment qCommunityListFragment = new RobListFragment();
            qCommunityListFragment.setCategoryId(qiangGouDaleiBean.getId());
            fragmentList.add(qCommunityListFragment);
            titleList.add(qiangGouDaleiBean.getCategoryName());
            tabLayout.addTab(tabLayout.newTab().setText(qiangGouDaleiBean.getCategoryName()));
            adapter = new PhotoAdapter(getSupportFragmentManager(), fragmentList, titleList);
            viewPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewPager);//TabLayout加载viewpager
            tabLayout.setVisibility(View.GONE);
        } else {
            ToastUtils.showShort(this, "暂无分类");
        }
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    protected void initListener() {
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_shop_limit;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        String titleName = intent.getStringExtra("titleName");
        QIANGGOU_ID = intent.getIntExtra("id", QIANGGOU_ID);
        tvTitle.setText(titleName);
        if (QIANGGOU_ID != -1) {
            qianggouFenleiPresenterImpl = new ImplQueryQianggouFenleiPresenterImpl(this, RobShopDetailsActivity.this);
            qianggouFenleiPresenterImpl.queryQianggouFenlei(QIANGGOU_ID);
        }
    }

    @Override
    public void initData() {
        fragmentList = new ArrayList<>();//页面列表
        titleList = new ArrayList<>();  //将名称加载tab名字列表
        tabLayout.setTabMode(TabLayout.MODE_FIXED);//设置TabLayout的模式
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
