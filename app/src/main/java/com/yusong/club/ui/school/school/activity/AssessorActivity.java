package com.yusong.club.ui.school.school.activity;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.school.mvp.entity.ApplyRole;
import com.yusong.club.ui.school.mvp.implView.AssessorActivityView;
import com.yusong.club.ui.school.mvp.presenter.impl.AssessorActivityPresenterImpl;
import com.yusong.club.ui.school.school.adapter.AssessorActivityAdapter;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.club.utils.bga.BGARefreshLayout;
import com.yusong.club.utils.bga.BGARefreshViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * 家长审核
 */
public class AssessorActivity extends BaseActivity implements AssessorActivityView, BGARefreshLayout.BGARefreshLayoutDelegate {

    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.rv_List)
    RecyclerView rvList;
    @InjectView(R.id.mBGA_layout)
    BGARefreshLayout mBGALayout;
    public Context mContext;
    public AssessorActivityPresenterImpl mPresenter;
    public boolean isRefresh = true;
    List<ApplyRole> roleApplyDatas = new ArrayList<>();
    public AssessorActivityAdapter mAdapter;
    public LinearLayoutManager mMLinearLayoutManager;

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
        return R.layout.activity_school_assessor;
    }

    @Override
    public void initView() {
        mContext = AssessorActivity.this;
        llBack.setVisibility(View.VISIBLE);
        tvTitle.setText("申请审核");
        initRefreshLayout();
        mPresenter = new AssessorActivityPresenterImpl(this, this);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.queryRoleApplyList(CacheUtils.getToken(mContext),0,10);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        isRefresh = true;
        mPresenter.queryRoleApplyList(CacheUtils.getToken(mContext),0,10);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        isRefresh = false;
        mPresenter.queryRoleApplyList(CacheUtils.getToken(mContext),0,10);
        return true;
    }

    @Override
    public void closeRefreshing() {
        if (mBGALayout != null) {
            mBGALayout.endRefreshing();
            mBGALayout.endLoadingMore();
        }
    }

    @Override
    public void queryRoleApplyListCallback(List<ApplyRole> data) {

        if (isRefresh == true) {
            roleApplyDatas.clear();
            roleApplyDatas.addAll(data);
        } else {
            roleApplyDatas.addAll(data);
        }
        if (mAdapter == null) {
            mMLinearLayoutManager = new LinearLayoutManager(mContext);
            mAdapter = new AssessorActivityAdapter(roleApplyDatas, mContext);
            rvList.setAdapter(mAdapter);
//            rvList.addItemDecoration(new SpaceItemDecoration(10, 0));
            rvList.setLayoutManager(mMLinearLayoutManager);
            mBGALayout.setLinearLayoutManager(roleApplyDatas, mMLinearLayoutManager);
            mAdapter.notifyDataSetChanged();
        } else {
            mAdapter.notifyDataSetChanged();
        }
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
    public void showProgressDialog() {

    }
}
