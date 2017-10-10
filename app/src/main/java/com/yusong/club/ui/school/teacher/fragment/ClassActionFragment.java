package com.yusong.club.ui.school.teacher.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseFragment;
import com.yusong.club.ui.school.mvp.implView.IClassActionFragmentView;
import com.yusong.club.ui.school.mvp.presenter.impl.IClassActionFragmentPresenterImpl;
import com.yusong.club.ui.school.school.bean.HuoType;
import com.yusong.club.ui.school.teacher.activity.CreateActionActivity;
import com.yusong.club.ui.school.teacher.adapter.ActionAdapter;
import com.yusong.club.utils.CacheUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 活动
 */
public class ClassActionFragment extends BaseFragment implements IClassActionFragmentView {

    @InjectView(R.id.tab_layout)
    TabLayout tabLayout;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.iv_img)
    ImageView ivImg;
    @InjectView(R.id.ll_img)
    LinearLayout llImg;
    @InjectView(R.id.app_title_layout)
    LinearLayout appTitleLayout;
    @InjectView(R.id.not_data_layout)
    RelativeLayout notDataLayout;
    private ActionAdapter adapter;
    private List<Fragment> fragmentList;
    private List<String> titleList;
    private IClassActionFragmentPresenterImpl mPresenter;
    private String SchoolId;
    private int roleTag;

    @Override
    public View initView() {
        return View.inflate(getActivity(), R.layout.fragment_teacher_class_action, null);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        int sdkInt = Build.VERSION.SDK_INT;
        llBack.setVisibility(View.GONE);
        if (sdkInt >= 18) {
            ivAdaptiveDownApi18.setVisibility(View.VISIBLE);
        } else {
            ivAdaptiveDownApi18.setVisibility(View.GONE);
        }
        if (getArguments() != null && getArguments().getSerializable("SchoolId") != null) {
            SchoolId = (String) getArguments().getSerializable("SchoolId");
        }
        if (getArguments() != null && getArguments().getInt("roleTag", 0) != 0) {
            roleTag = (int) getArguments().getInt("roleTag", 0);
        }
        if (roleTag==1){
            ivImg.setImageResource(R.mipmap.add);
            ivImg.setVisibility(View.VISIBLE);
            llImg.setVisibility(View.VISIBLE);
        }else if (roleTag==2){
            ivImg.setVisibility(View.GONE);
            llImg.setVisibility(View.GONE);
        }
        mPresenter = new IClassActionFragmentPresenterImpl(this, mContext);
        tvTitle.setText("活动");

        fragmentList = new ArrayList<>();
    }
    public void setFragments(List<HuoType> data) {
        for (int i = 0; i < data.size(); i++) {
            fragmentList.add(ActionOneFragment.newInstance(data.get(i), (SchoolId), roleTag));
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
        adapter = new ActionAdapter(getChildFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(adapter);
        //TabLayout加载viewpager
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.categoryList(CacheUtils.getToken(mContext));
    }

    @Override
    public void getcategoryList(List<HuoType> data) {
        if (data != null && data.size() != 0) {
            notDataLayout.setVisibility(View.GONE);
            setFragments(data);
        } else {
            notDataLayout.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);

    }

    @OnClick({R.id.ll_img})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_img:
                startActivity(new Intent(mContext, CreateActionActivity.class));
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
