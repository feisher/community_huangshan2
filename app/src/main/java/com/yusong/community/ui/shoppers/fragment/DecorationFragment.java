package com.yusong.community.ui.shoppers.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseFragment;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.shoppers.activity.CommodityDetailsActivity;
import com.yusong.community.ui.shoppers.adapter.DecorationAdapter;
import com.yusong.community.ui.shoppers.bean.CommodityBean;
import com.yusong.community.ui.shoppers.bean.FenLeiBean;
import com.yusong.community.ui.shoppers.mvp.implView.ImplHomeView;
import com.yusong.community.ui.shoppers.mvp.presenter.impl.ImplJiaZhuanPersenterImpl;
import com.yusong.community.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.community.utils.bga.BGARefreshLayout;
import com.yusong.community.utils.bga.BGARefreshViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 *         created at 2017/3/16 8:50.
 */

public class DecorationFragment extends BaseFragment implements ImplHomeView, BGARefreshLayout.BGARefreshLayoutDelegate {

    @InjectView(R.id.home_zhuang_recyclerView)
    RecyclerView homeZhuangRecyclerView;
    @InjectView(R.id.jiazhuang_refresh_layout)
    BGARefreshLayout jiazhuangRefreshLayout;
    @InjectView(R.id.not_network_message)
    TextView notNetworkMessage;
    @InjectView(R.id.not_network_refresh)
    Button notNetworkRefresh;
    @InjectView(R.id.not_netword_layout)
    RelativeLayout notNetwordLayout;
    @InjectView(R.id.not_data_message)
    TextView notDataMessage;
    @InjectView(R.id.not_data_layout)
    RelativeLayout notDataLayout;
    private int categoryId;
    private ImplJiaZhuanPersenterImpl jiaZhuanPersenterImpl;

    private List<CommodityBean> list = new ArrayList<CommodityBean>();
    private DecorationAdapter adapter;
    private boolean isClear = false;

    @OnClick(R.id.not_network_refresh)
    void click() {
        jiaZhuanPersenterImpl.queryHomeList(getCategoryId(), 0, 10);
        isClear = true;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public View initView() {
        return View.inflate(getActivity(), R.layout.fragment_jiazhuang, null);
    }

    @Override
    public void initData() {
        initRefreshLayout();
        adapter = new DecorationAdapter(list, mContext);
        homeZhuangRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        homeZhuangRecyclerView.setAdapter(adapter);
        jiaZhuanPersenterImpl = new ImplJiaZhuanPersenterImpl(this, getActivity());
        jiaZhuanPersenterImpl.queryHomeList(getCategoryId(), 0, 10);
    }

    @Override
    public void initListener() {
        adapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                Intent intent = new Intent(getActivity(), CommodityDetailsActivity.class);
                intent.putExtra("itemId", list.get(position).getId());
                intent.putExtra("isQG", 1);
                startActivity(intent);
            }
        });
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
        jiaZhuanPersenterImpl.queryHomeList(getCategoryId(), 0, 10);
        isClear = true;
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        jiaZhuanPersenterImpl.queryHomeList(getCategoryId(), list.size(), 10);
        isClear = false;
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void close() {
        notDataLayout.setVisibility(View.GONE);
        notNetwordLayout.setVisibility(View.VISIBLE);
        homeZhuangRecyclerView.setVisibility(View.GONE);
        closeRefresh();
    }

    @Override
    public void HomeFlSucced(List<FenLeiBean> datas) {

    }

    @Override
    public void HomeListSucced(List<CommodityBean> datas) {//列表
        if (isClear) {
            if (list.size() > 0) {
                list.clear();
            }
        }
        closeRefresh();
        if (datas.size() > 0) {
            notDataLayout.setVisibility(View.GONE);
            notNetwordLayout.setVisibility(View.GONE);
            homeZhuangRecyclerView.setVisibility(View.VISIBLE);
            list.addAll(datas);
            adapter.notifyDataSetChanged();
        } else {
            if (isClear) {
                notDataLayout.setVisibility(View.VISIBLE);
                notNetwordLayout.setVisibility(View.GONE);
                homeZhuangRecyclerView.setVisibility(View.GONE);
            }
        }
        isClear = false;
    }

    @Override
    public void showProgressDialog() {

    }

    /**
     * 初始化刷新
     */
    private void initRefreshLayout() {
        //设置代理
        jiazhuangRefreshLayout.setDelegate(this);
        //设置刷新风格
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(mContext, true);
        jiazhuangRefreshLayout.setRefreshViewHolder(mBGARefreshViewHolder);
    }

    public void closeRefresh() {
        jiazhuangRefreshLayout.endRefreshing();
        jiazhuangRefreshLayout.endLoadingMore();
    }

}
