package com.yusong.community.ui.school.teacher.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.school.mvp.implView.IClassActionActivityView;
import com.yusong.community.ui.school.mvp.presenter.impl.IClassActionActivityPresenterImpl;
import com.yusong.community.ui.school.school.bean.HuoType;
import com.yusong.community.ui.school.teacher.adapter.ActionAdapter;
import com.yusong.community.ui.school.teacher.fragment.ActionOneFragment;
import com.yusong.community.utils.CacheUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * 班级活动
 */
public class ClassActionActivity extends BaseActivity implements IClassActionActivityView, View.OnClickListener {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    private ActionAdapter adapter;
    private List<Fragment> fragmentList;
    private List<String> titleList;
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
    @InjectView(R.id.tab_layout)
    TabLayout tabLayout;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    private IClassActionActivityPresenterImpl mPresenter;
    private Context mContext;
    private String SchoolId = "";
    private int roleTag = 0;

    @Override
    protected void initListener() {
        linearBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_teacher_class_action;
    }

    @Override
    public void initView() {
        tvTitle.setText("活动");
        if (getIntent() != null && getIntent().getSerializableExtra("SchoolId") != null) {
            SchoolId = (String) getIntent().getSerializableExtra("SchoolId");
        }
        if (getIntent() != null && getIntent().getSerializableExtra("roleTag") != null) {
            roleTag = (int) getIntent().getSerializableExtra("roleTag");
        }

        imMsg.setImageResource(R.mipmap.add);
        mContext = ClassActionActivity.this;
        imMsg.setOnClickListener(this);
        mPresenter = new IClassActionActivityPresenterImpl(this, this);
        mPresenter.categoryList(CacheUtils.getToken(mContext));
    }

    @Override
    public void initData() {
        fragmentList = new ArrayList<>();
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
    public void getcategoryList(List<HuoType> data) {
        if (data != null && data.size() != 0) {
            imMsg.setVisibility(View.VISIBLE);
            setFragments(data);
        } else {
            imMsg.setVisibility(View.INVISIBLE);
        }

    }

    public void setFragments(List<HuoType> data) {
        for (int i = 0; i < data.size(); i++) {
            fragmentList.add(ActionOneFragment.newInstance(data.get(i), SchoolId, roleTag));
        }
        //将名称加载tab名字列表
        titleList = new ArrayList<>();
        for (int j = 0; j < data.size(); j++) {
            titleList.add(data.get(j).getCategoryName());
        }
        //设置TabLayout的模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        for (int k = 0; k < data.size(); k++) {
            tabLayout.addTab(tabLayout.newTab().setText(titleList.get(k)));
        }
        adapter = new ActionAdapter(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(adapter);
        //TabLayout加载viewpager
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.im_msg:
                startActivity(new Intent(mContext, CreateActionActivity.class));
                break;

        }

    }

}
