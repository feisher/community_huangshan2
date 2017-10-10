package com.yusong.club.ui.school.teacher.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.school.mvp.implView.IHomeworkActivityView;
import com.yusong.club.ui.school.mvp.presenter.impl.IHomeworkActivityPresenterImpl;
import com.yusong.club.ui.school.teacher.adapter.HomeworkAdapter;
import com.yusong.club.ui.school.teacher.bean.ClassHomework;
import com.yusong.club.utils.ActivityConstants;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.DateUtil;
import com.yusong.club.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.club.utils.bga.BGARefreshLayout;
import com.yusong.club.utils.bga.BGARefreshViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作业发放
 */
public class HomeworkActivity extends BaseActivity implements View.OnClickListener, IHomeworkActivityView, BGARefreshLayout.BGARefreshLayoutDelegate {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.not_data_layout)
    RelativeLayout notDataLayout;
    private List<ClassHomework> data;
    private HomeworkAdapter adapter;
    @InjectView(R.id.linear_back)
    LinearLayout linearBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_addWord)
    TextView tvAddWord;
    @InjectView(R.id.lv_homework)
    ListView lvHomework;
    @InjectView(R.id.mBGA_layout)
    BGARefreshLayout mBGALayout;
    private Context mContext;
    private IHomeworkActivityPresenterImpl mPresent;
    private int roleFlag = 0;

    @Override
    protected void initListener() {
        linearBack.setOnClickListener(this);
        tvAddWord.setOnClickListener(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_teacher_homework;
    }

    @Override
    public void initView() {
        mContext = HomeworkActivity.this;
        initRefreshLayout();
    }

    @Override
    public void initData() {
        if (getIntent() != null && getIntent().getIntExtra("roleTag", 0) != 0) {
            roleFlag = getIntent().getIntExtra("roleTag", 0);
        }
        switch (roleFlag) {
            case 1:
                tvAddWord.setVisibility(View.VISIBLE);
                break;
            case 2:
                tvAddWord.setVisibility(View.GONE);
                break;
        }
        data = new ArrayList<>();
        mPresent = new IHomeworkActivityPresenterImpl(this, this);
        mPresent.searchClassHomework(CacheUtils.getToken(mContext), DateUtil.getDate());
        adapter = new HomeworkAdapter(data, this);
        lvHomework.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    /**
     * 初始化刷新布局
     */
    private void initRefreshLayout() {
        mBGALayout.setDelegate(this);//设置代理
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(mContext, true);
        mBGALayout.setRefreshViewHolder(mBGARefreshViewHolder);
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

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.linear_back:
                finish();
                break;
            case R.id.tv_addWord:
                intent = new Intent(this, AddworkActivity.class);
                startActivityForResult(intent, ActivityConstants.WORK_CODE);
                break;
        }
    }

    @Override
    public void getClassHomework(List<ClassHomework> datas) {
        if (datas != null && datas.size() != 0) {
            notDataLayout.setVisibility(View.GONE);
        } else {
            notDataLayout.setVisibility(View.VISIBLE);
        }
        data.clear();
        data.addAll(datas);
        adapter.notifyDataSetChanged();


    }

    @Override
    public void closeRefresh() {
        if (mBGALayout != null) {
            mBGALayout.endRefreshing();
        }
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);

    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mPresent.searchClassHomework(CacheUtils.getToken(mContext), DateUtil.getDate());

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ActivityConstants.WORK_CODE && resultCode == ActivityConstants.WORK_CODE) {
            mPresent.searchClassHomework(CacheUtils.getToken(mContext), DateUtil.getDate());
        }
    }
}
