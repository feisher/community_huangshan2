package com.yusong.club.ui.shoppers.used.fragment;

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

import com.baidu.location.BDLocation;
import com.yusong.club.R;
import com.yusong.club.map.LocationService;
import com.yusong.club.ui.base.BaseFragment;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.shoppers.bean.FenLeiBean;
import com.yusong.club.ui.shoppers.used.activity.UsedDetailsActivity;
import com.yusong.club.ui.shoppers.used.adapter.UsedItemAdapter;
import com.yusong.club.ui.shoppers.used.bean.UsedBean;
import com.yusong.club.ui.shoppers.used.mvp.implView.ImplUsedView;
import com.yusong.club.ui.shoppers.used.mvp.presenter.impl.ImplUsedPresenterImpl;
import com.yusong.club.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.club.utils.bga.BGARefreshLayout;
import com.yusong.club.utils.bga.BGARefreshViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 *         crated at on 2017/3/20.
 *         描述:null
 */

public class UsedItemFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate,
        ImplUsedView {
    @InjectView(R.id.used_item_recyclerview)
    RecyclerView usedItemRecyclerview;
    @InjectView(R.id.used_item_bga_layout)
    BGARefreshLayout usedItemBgaLayout;
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
    private ImplUsedPresenterImpl implUsedPresenterImpl;
    private List<UsedBean> usedBeanList = new ArrayList<UsedBean>();
    private UsedItemAdapter usedItemAdapter;
    BDLocation mLocation = LocationService.mLocation;
    private boolean isRefresh = false;

    @OnClick(R.id.not_network_refresh)
    void click() {//断网刷新
        isRefresh = true;
        implUsedPresenterImpl.queryUsedList(getCategoryId(), mLocation.getLongitude(),
                mLocation.getLatitude(), 0, 15);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        isRefresh = true;
        implUsedPresenterImpl.queryUsedList(getCategoryId(), mLocation.getLongitude(),
                mLocation.getLatitude(), 0, 15);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        isRefresh = false;
        implUsedPresenterImpl.queryUsedList(getCategoryId(), mLocation.getLongitude(),
                mLocation.getLatitude(), usedBeanList.size(), 15);
        return true;
    }

    @Override
    public View initView() {
        return View.inflate(getActivity(), R.layout.fragment_used_item, null);
    }

    @Override
    public void initData() {
        initRefreshLayout();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        usedItemAdapter = new UsedItemAdapter(usedBeanList, getActivity());
        usedItemRecyclerview.setAdapter(usedItemAdapter);
        usedItemRecyclerview.setLayoutManager(linearLayoutManager);
        //usedItemRecyclerview.addItemDecoration(new SpaceItemDecoration(40, 0));
        usedItemBgaLayout.setLinearLayoutManager(usedBeanList, linearLayoutManager);
        implUsedPresenterImpl = new ImplUsedPresenterImpl(this, getActivity());
        implUsedPresenterImpl.queryUsedList(getCategoryId(), mLocation.getLongitude(),
                mLocation.getLatitude(), 0, 15);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void initListener() {
        usedItemAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                Intent intent = new Intent(getActivity(), UsedDetailsActivity.class);
                intent.putExtra("UsedBean", usedBeanList.get(position));
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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void colse() {
        closeRefresh();
        notDataLayout.setVisibility(View.GONE);
        notNetwordLayout.setVisibility(View.VISIBLE);
        usedItemBgaLayout.setVisibility(View.GONE);
        closeRefresh();
    }

    @Override
    public void queryFenleiSucced(List<FenLeiBean> data) {

    }

    @Override
    public void quertListSucced(List<UsedBean> data) {
        closeRefresh();
        if (isRefresh) {
            if (usedBeanList.size() > 0) {
                usedBeanList.clear();
            }
        }
        usedBeanList.addAll(data);
        if (usedBeanList.size() == 0) {
            notDataLayout.setVisibility(View.VISIBLE);
            notNetwordLayout.setVisibility(View.GONE);
            usedItemBgaLayout.setVisibility(View.GONE);
        } else {
            notDataLayout.setVisibility(View.GONE);
            notNetwordLayout.setVisibility(View.GONE);
            usedItemBgaLayout.setVisibility(View.VISIBLE);
        }
        usedItemAdapter.notifyDataSetChanged();
        isRefresh = false;
    }

    @Override
    public void showProgressDialog() {

    }

    /**
     * 初始化刷新
     */
    private void initRefreshLayout() {
        //设置代理
        usedItemBgaLayout.setDelegate(this);
        //设置刷新风格
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(mContext, true);
        usedItemBgaLayout.setRefreshViewHolder(mBGARefreshViewHolder);
    }

    public void closeRefresh() {
        usedItemBgaLayout.endRefreshing();
        usedItemBgaLayout.endLoadingMore();
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        implUsedPresenterImpl.onDestroy();
    }
}
