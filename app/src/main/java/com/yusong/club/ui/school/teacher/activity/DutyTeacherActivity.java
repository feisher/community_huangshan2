package com.yusong.club.ui.school.teacher.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.school.mvp.implView.IDutyTeacherActivityView;
import com.yusong.club.ui.school.mvp.presenter.impl.IDutyTeacherPresenterImpl;
import com.yusong.club.ui.school.teacher.adapter.DutyAdapter;
import com.yusong.club.ui.school.teacher.bean.AllTeacher;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.ToastUtils;
import com.yusong.club.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.club.utils.bga.BGARefreshLayout;
import com.yusong.club.utils.bga.BGARefreshViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 值班老师
 */
public class DutyTeacherActivity extends BaseActivity implements IDutyTeacherActivityView, DutyAdapter.SetDuty, BGARefreshLayout.BGARefreshLayoutDelegate {
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
    @InjectView(R.id.lv_duty)
    ListView lvDuty;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.mBGA_layout)
    BGARefreshLayout mBGALayout;
    @InjectView(R.id.not_data_layout)
    RelativeLayout notDataLayout;
    @InjectView(R.id.tv_name)
    TextView tvName;
    private List<AllTeacher> data;
    private DutyAdapter adapter;
    public Context mContext;
    private boolean isRefresh = false;
    IDutyTeacherPresenterImpl mPresenter;

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
        return R.layout.activity_teacher_duty;
    }

    @Override
    public void initView() {
        mContext = DutyTeacherActivity.this;
        tvBack.setVisibility(View.GONE);
        imMsg.setVisibility(View.GONE);
        tvTitle.setText("设置值班老师");
        initRefreshLayout();
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
    public void initData() {
        data = new ArrayList<>();
        mPresenter = new IDutyTeacherPresenterImpl(this, this);
        mPresenter.searchTeacherList(CacheUtils.getToken(mContext));
        adapter = new DutyAdapter(data, this);
        adapter.setmSetDuty(this);
        lvDuty.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);
    }

    @Override
    public void getAllTeacher(List<AllTeacher> datas) {
        if (datas != null && datas.size() != 0) {
            notDataLayout.setVisibility(View.GONE);
            tvName.setVisibility(View.VISIBLE);
            data.clear();
            data.addAll(datas);
            adapter.notifyDataSetChanged();
        } else {
            notDataLayout.setVisibility(View.VISIBLE);
            tvName.setVisibility(View.GONE);
        }
    }

    @Override
    public void setDuty(int pos) {
        mPresenter.setOndutyTeacher(CacheUtils.getToken(mContext), data.get(pos).getId());

    }

    @Override
    public void setOndutyTeacher(String data) {
        ToastUtils.show(mContext, "设置成功", Toast.LENGTH_LONG);

    }

    @Override
    public void closeRefresh() {
        if (mBGALayout != null) {
            mBGALayout.endRefreshing();
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mPresenter.searchTeacherList(CacheUtils.getToken(mContext));

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}
