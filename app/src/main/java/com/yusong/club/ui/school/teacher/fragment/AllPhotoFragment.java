package com.yusong.club.ui.school.teacher.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseFragment;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.school.mvp.implView.IAllPhotoFragmentView;
import com.yusong.club.ui.school.mvp.presenter.impl.IAllPhotoFragmentPresenterImpl;
import com.yusong.club.ui.school.teacher.activity.PhotoDetailActivity;
import com.yusong.club.ui.school.teacher.adapter.AllPhotoAdapter;
import com.yusong.club.ui.school.teacher.bean.PhotoAlbum;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.club.utils.bga.BGARefreshLayout;
import com.yusong.club.utils.bga.BGARefreshViewHolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllPhotoFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate, IAllPhotoFragmentView {
    @InjectView(R.id.rv_List)
    RecyclerView rvList;
    @InjectView(R.id.mBGA_layout)
    BGARefreshLayout mBGALayout;
    @InjectView(R.id.not_data_layout)
    RelativeLayout notDataLayout;
    private boolean isRefresh = true;
    private IAllPhotoFragmentPresenterImpl mPresenter;
    private List<PhotoAlbum> photoDatas;
    private AllPhotoAdapter mAdapter;
    private LinearLayoutManager mMLinearLayoutManager;

    @Override
    public View initView() {
        return View.inflate(mContext, R.layout.fragment_all_photo, null);
    }

    @Override
    public void initData() {
        photoDatas = new ArrayList<>();
        initRefreshLayout();
        mPresenter = new IAllPhotoFragmentPresenterImpl(this, mContext);
        mMLinearLayoutManager = new LinearLayoutManager(mContext);
        mAdapter = new AllPhotoAdapter(photoDatas, mContext);
        rvList.setAdapter(mAdapter);
//      rvList.addItemDecoration(new SpaceItemDecoration(1, 0));
        rvList.setLayoutManager(mMLinearLayoutManager);
//        mBGALayout.setLinearLayoutManager(photoDatas, mMLinearLayoutManager);
        mAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                Intent intent = new Intent(mContext, PhotoDetailActivity.class);
                Bundle bundle = new Bundle();
                PhotoAlbum photoAlbum=  photoDatas.get(position);
                bundle.putSerializable("photoAlbum", (Serializable) photoAlbum);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        mPresenter.queryPhotoAlbumList(CacheUtils.getToken(mContext), 0, 10);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        isRefresh = true;
        mPresenter.queryPhotoAlbumList(CacheUtils.getToken(mContext), 0, 10);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        isRefresh = false;
        mPresenter.queryPhotoAlbumList(CacheUtils.getToken(mContext), photoDatas.size(), 10);
        return true;
    }

    @Override
    public void getPhotoAlbumList(List<PhotoAlbum> data) {
        if (isRefresh == true) {
            if (data != null && data.size() != 0) {
                notDataLayout.setVisibility(View.GONE);
            } else {
                notDataLayout.setVisibility(View.VISIBLE);
            }
            photoDatas.clear();
            photoDatas.addAll(data);
        } else {
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
}
