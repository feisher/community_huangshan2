package com.yusong.community.ui.school.teacher.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseFragment;
import com.yusong.community.ui.school.mvp.entity.LatestPhoto;
import com.yusong.community.ui.school.mvp.implView.ILatestPhotoFragmentView;
import com.yusong.community.ui.school.mvp.presenter.impl.ILatestPhotoFragmentPresenterImpl;
import com.yusong.community.ui.school.teacher.adapter.LatestPhotoAdapter;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.community.utils.bga.BGARefreshLayout;
import com.yusong.community.utils.bga.BGARefreshViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class LatestPhotoFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate, ILatestPhotoFragmentView {
    @InjectView(R.id.rv_List)
    RecyclerView rvList;
    @InjectView(R.id.mBGA_layout)
    BGARefreshLayout mBGALayout;
    @InjectView(R.id.not_data_layout)
    RelativeLayout notDataLayout;
    private boolean isRefresh = true;
    private ILatestPhotoFragmentPresenterImpl mPresenter;
    private List<LatestPhoto> photoDatas;
    private LatestPhotoAdapter mAdapter;
    private LinearLayoutManager mMLinearLayoutManager;

    @Override
    public View initView() {
        return View.inflate(mContext, R.layout.fragment_latest_photo, null);
    }

    @Override
    public void initData() {
        photoDatas = new ArrayList<>();
        mPresenter = new ILatestPhotoFragmentPresenterImpl(this, mContext);
        initRefreshLayout();
        mMLinearLayoutManager = new LinearLayoutManager(mContext);
        mAdapter = new LatestPhotoAdapter(photoDatas, mContext);
        rvList.setAdapter(mAdapter);
        rvList.setLayoutManager(mMLinearLayoutManager);
        mBGALayout.setLinearLayoutManager(photoDatas, mMLinearLayoutManager);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getLatestPhoto(CacheUtils.getToken(mContext), 0, 10);
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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        isRefresh = true;
        mPresenter.getLatestPhoto(CacheUtils.getToken(mContext), 0, 10);

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        isRefresh = false;
        mPresenter.getLatestPhoto(CacheUtils.getToken(mContext), photoDatas.size(), 10);
        return true;
    }

    @Override
    public void getLatestPhotoList(List<LatestPhoto> data) {
        if (isRefresh == true) {
            if (data != null && data.size() != 0) {
                notDataLayout.setVisibility(View.GONE);
            } else {
                notDataLayout.setVisibility(View.VISIBLE);
            }
            photoDatas.clear();
            photoDatas.addAll(data);
        } else {
            notDataLayout.setVisibility(View.GONE);
            photoDatas.addAll(data);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void closeRefreshing() {
        if (mBGALayout != null) {
            mBGALayout.endRefreshing();
            mBGALayout.endLoadingMore();
        }
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }
}
