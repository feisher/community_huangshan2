package com.yusong.community.ui.home.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
import com.yusong.community.ui.home.fragment.MsgFragment;
import com.yusong.community.ui.school.teacher.adapter.PhotoAdapter;
import com.yusong.community.ui.shoppers.fragment.RobListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 跳蚤市场
 */
public class FleaActivity extends BaseActivity {
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
    RadioButton weifukuanRadio;
    @InjectView(R.id.center_radio)
    RadioButton yifukuanRadio;
    @InjectView(R.id.right_radio)
    RadioButton yiwanchnengRadio;
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
    @InjectView(R.id.tab_flea)
    TabLayout tabFlea;
    @InjectView(R.id.vp_flea)
    ViewPager vpFlea;
    private PhotoAdapter adapter;
    private List<Fragment> fragmentList;
    private List<String> titleList;

    @Override
    protected void adaptiveSystemVersions() {

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
        return R.layout.activity_home_flea;
    }

    @Override
    public void initView() {
        tvTitle.setText("跳蚤市场");
    }

    @Override
    public void initData() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new RobListFragment());
        fragmentList.add(new MsgFragment());
        fragmentList.add(new MsgFragment());
        fragmentList.add(new MsgFragment());
        fragmentList.add(new MsgFragment());
        fragmentList.add(new MsgFragment());
        fragmentList.add(new MsgFragment());

        //将名称加载tab名字列表
        titleList = new ArrayList<>();
        titleList.add("超值");
        titleList.add("二手车");
        titleList.add("二手房");
        titleList.add("二手书");
        titleList.add("二手家具");
        titleList.add("二手手机");
        titleList.add("百货");

        //设置TabLayout的模式
        tabFlea.setTabMode(TabLayout.MODE_SCROLLABLE);
        //为TabLayout添加tab名称
        tabFlea.addTab(tabFlea.newTab().setText(titleList.get(0)));
        tabFlea.addTab(tabFlea.newTab().setText(titleList.get(1)));
        tabFlea.addTab(tabFlea.newTab().setText(titleList.get(2)));
        tabFlea.addTab(tabFlea.newTab().setText(titleList.get(3)));
        tabFlea.addTab(tabFlea.newTab().setText(titleList.get(4)));
        tabFlea.addTab(tabFlea.newTab().setText(titleList.get(5)));
        tabFlea.addTab(tabFlea.newTab().setText(titleList.get(6)));

        adapter = new PhotoAdapter(getSupportFragmentManager(), fragmentList, titleList);
        vpFlea.setAdapter(adapter);
        //TabLayout加载viewpager
        tabFlea.setupWithViewPager(vpFlea);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
