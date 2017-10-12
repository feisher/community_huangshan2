package com.yusong.community.ui.school.teacher.activity;

import android.content.Context;
import android.content.Intent;
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
import com.yusong.community.ui.school.teacher.fragment.FirstItemFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
/**
 * 学生评语
 */
public class EvaluateActivity extends BaseActivity implements View.OnClickListener {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.im_back)
    ImageView imBack;
    @InjectView(R.id.tv_back)
    TextView tvBack;
    @InjectView(R.id.linear_back)
    LinearLayout linearBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.im_msg)
    ImageView imMsg;
    @InjectView(R.id.tabLayout)
    TabLayout tabLayout;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    private int roleFlag=0;
    private Context mContext;
    private PhotoAdapter adapter;
    private List<Fragment> fragmentList;
    private List<String> titleList;
    private List<String> menuList;

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
    protected void initListener() {
        linearBack.setOnClickListener(this);
        imMsg.setOnClickListener(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_teacher_evaluate;
    }

    @Override
    public void initView() {
        tvTitle.setText("学生评语");
        imMsg.setImageResource(R.mipmap.school_add);
        mContext = EvaluateActivity.this;
        if (getIntent()!=null&&getIntent().getIntExtra("roleTag",0)!=0){
            roleFlag=getIntent().getIntExtra("roleTag",0);
        }
        switch (roleFlag){
            case 1:
                imMsg.setVisibility(View.VISIBLE);
                break;
            case 2:
                imMsg.setVisibility(View.GONE);
                break;
        }
    }


    @Override
    public void initData() {
        fragmentList = new ArrayList<>();
        fragmentList.add(FirstItemFragment.newInstance(1));
        fragmentList.add(FirstItemFragment.newInstance(2));
        //将名称加载tab名字列表
        titleList = new ArrayList<>();
        titleList.add("第一学期");
        titleList.add("第二学期");
        //设置TabLayout的模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(titleList.get(1)));
        adapter = new PhotoAdapter(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(adapter);
        //TabLayout加载viewpager
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_back:
                finish();
                break;
            case R.id.im_msg:
                Intent intent = new Intent(this, EditEvaluateActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

}
