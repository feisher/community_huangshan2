package com.yusong.community.ui.community_service.activity;

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

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.community_service.entity.ServiceBean;
import com.yusong.community.ui.community_service.entity.ServiceDetailBean;
import com.yusong.community.ui.community_service.mvp.ImplView.ServiceView;
import com.yusong.community.ui.community_service.mvp.presenter.ImplPresenter.ServicePresenterImpl;
import com.yusong.community.ui.school.teacher.adapter.PhotoAdapter;
import com.yusong.community.ui.shoppers.activity.FindCommodityActivity;
import com.yusong.community.ui.shoppers.bean.FenLeiBean;
import com.yusong.community.utils.ToastUtils;
import com.yusong.community.utils.glide.GlideImgManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 * @created at 2017-09-22.
 * @describe: null
 */

public class CommunityServiceActivity extends BaseActivity implements ServiceView {
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
    @InjectView(R.id.app_title_layout)
    LinearLayout appTitleLayout;
    @InjectView(R.id.tl_community_fragment)
    TabLayout tlCommunityFragment;
    @InjectView(R.id.vp_community_fragment)
    ViewPager vpCommunityFragment;
    @InjectView(R.id.beijin_2)
    ImageView beijin2;
    @InjectView(R.id.beijin_1)
    ImageView beijin1;
    @InjectView(R.id.logo_image)
    ImageView logoImage;
    @InjectView(R.id.sm_tv)
    TextView smTv;
    @InjectView(R.id.memo_tv)
    TextView memoTv;
    @InjectView(R.id.find_commodity_layout)
    RelativeLayout findCommodityLayout;
    private ServicePresenterImpl presenter;
    private List<String> tabList = new ArrayList<String>();
    private List<Fragment> fragmentList = new ArrayList<Fragment>();

    @OnClick({R.id.ll_back, R.id.rl_txt, R.id.find_commodity_layout})
    void click(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.rl_txt:
                startActivity(new Intent(this, AboutServiceActivity.class));
                break;
            case R.id.find_commodity_layout:
                Intent intent = new Intent(this, FindCommodityActivity.class);
                intent.putExtra("findType", 3);
                startActivity(intent);
                break;

        }
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_community_service;
    }

    @Override
    public void initView() {
        tvTitle.setVisibility(View.GONE);
        rlTxt.setVisibility(View.VISIBLE);
        findCommodityLayout.setVisibility(View.VISIBLE);
        tvText.setText("关于");
    }


    @Override
    public void initData() {
        presenter = new ServicePresenterImpl(this, this);
        presenter.queryServiceDetail();
        presenter.queryServiceCategory();
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
            beijin1.setVisibility(View.VISIBLE);
            beijin2.setVisibility(View.GONE);
        } else {
            ivAdaptiveDownApi18.setVisibility(View.GONE);
            beijin1.setVisibility(View.GONE);
            beijin2.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showProgressDialog() {

    }


    @Override
    public void queryServiceSucces(ServiceDetailBean bean) {
        if (bean != null) {
            GlideImgManager.loadImage(this, bean.getShopLogo(), logoImage);
            smTv.setText(bean.getShopName());
            memoTv.setText(bean.getMemo());
        }
    }

    @Override
    public void queryServiceCategorySucces(List<FenLeiBean> datas) {
        if (datas != null && datas.size() > 0) {
            if (datas.size() > 5) {
                tlCommunityFragment.setTabMode(TabLayout.MODE_SCROLLABLE);
            } else {
                tlCommunityFragment.setTabMode(TabLayout.MODE_FIXED);
            }
            for (FenLeiBean fenLeiBean : datas) {
                tabList.add(fenLeiBean.getCategoryName());
                ServiceFragment fragment = new ServiceFragment();
                fragment.setCategoryId(fenLeiBean.getId());
                fragmentList.add(fragment);
            }
            vpCommunityFragment.setAdapter(new PhotoAdapter(getSupportFragmentManager(), fragmentList, tabList));
            tlCommunityFragment.setupWithViewPager(vpCommunityFragment);//TabLayout加载viewpager
        } else {
            ToastUtils.showShort(this, "暂无分类");
        }
    }

    @Override
    public void queryServiceListSucces(List<ServiceBean> datas) {

    }

    @Override
    public void queryServiceListError() {

    }
}
