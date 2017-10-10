package com.yusong.club.ui.supermarket;

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
import com.yusong.club.ui.shoppers.activity.FindCommodityActivity;
import com.yusong.club.ui.shoppers.bean.FenLeiBean;
import com.yusong.club.ui.supermarket.entity.SMCommodityBean;
import com.yusong.club.ui.supermarket.entity.SuperMarketDetailsBean;
import com.yusong.club.ui.supermarket.mvp.ImolView.QuerySMView;
import com.yusong.club.ui.supermarket.mvp.presenter.impl.QuerySMPresenterImpl;
import com.yusong.club.utils.ToastUtils;
import com.yusong.club.utils.glide.GlideImgManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 * @created at 2017-08-26.
 * @describe: 社区超市
 */

public class SupermarketActivity extends BaseActivity implements QuerySMView {
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
    private QuerySMPresenterImpl presenter;
    private List<String> tabList = new ArrayList<String>();
    private List<Fragment> fragmentList = new ArrayList<Fragment>();

    @OnClick({R.id.ll_back, R.id.rl_txt, R.id.find_commodity_layout})
    void click(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.rl_txt:
                startActivity(new Intent(this, AboutSupermarketActivity.class));
                break;
            case R.id.find_commodity_layout:
                Intent intent = new Intent(this, FindCommodityActivity.class);
                intent.putExtra("findType", 2);
                startActivity(intent);
                break;

        }
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_supermarket;
    }

    @Override
    public void initView() {
        tvTitle.setVisibility(View.GONE);
        rlTxt.setVisibility(View.VISIBLE);
        findCommodityLayout.setVisibility(View.VISIBLE);
        tvText.setText("关于");
    }

    @Override
    public void queryCommoditySucces(List<SMCommodityBean> been) {

    }

    @Override
    public void initData() {
        presenter = new QuerySMPresenterImpl(this, this);
        presenter.queryFenlei();
        presenter.querySuperMarket();
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
    public void querySMSucces(SuperMarketDetailsBean data) {
        if (data != null) {
            GlideImgManager.loadImage(this, data.getShopLogo(), logoImage);
            smTv.setText(data.getShopName());
            memoTv.setText(data.getMemo());
        }
    }

    @Override
    public void queryCommodityError() {

    }

    @Override
    public void querFenleiSucces(List<FenLeiBean> been) {
        if (been.size() > 0) {
            if (been.size() > 5) {
                tlCommunityFragment.setTabMode(TabLayout.MODE_SCROLLABLE);
            } else {
                tlCommunityFragment.setTabMode(TabLayout.MODE_FIXED);
            }
            for (FenLeiBean fenLeiBean : been) {
                tabList.add(fenLeiBean.getCategoryName());
                SuperMarketFragment fragment = new SuperMarketFragment();
                fragment.setCategoryId(fenLeiBean.getId());
                fragmentList.add(fragment);
            }
            vpCommunityFragment.setAdapter(new PhotoAdapter(getSupportFragmentManager(), fragmentList, tabList));
            tlCommunityFragment.setupWithViewPager(vpCommunityFragment);//TabLayout加载viewpager
        } else {
            ToastUtils.showShort(this, "暂无分类");
        }
    }
}
