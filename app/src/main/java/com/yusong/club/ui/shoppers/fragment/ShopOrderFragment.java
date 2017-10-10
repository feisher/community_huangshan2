package com.yusong.club.ui.shoppers.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseFragment;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.shoppers.activity.OrderDetailsActivity;
import com.yusong.club.ui.shoppers.adapter.ShopOrderAdapter;
import com.yusong.club.ui.shoppers.bean.OrderShopBean;
import com.yusong.club.ui.shoppers.mvp.implView.ImplShopOrderView;
import com.yusong.club.ui.shoppers.mvp.presenter.impl.ImplShopOrderPersenterImpl;
import com.yusong.club.ui.shoppers.used.event.EventCancel;
import com.yusong.club.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.club.utils.bga.BGARefreshLayout;
import com.yusong.club.utils.bga.BGARefreshViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 *         created at 2017/3/8 19:30.
 *         商城订单fragment
 */

public class ShopOrderFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate,
        ImplShopOrderView {
    @InjectView(R.id.shop_order_recyclerview)
    RecyclerView shopOrderRecyclerview;
    @InjectView(R.id.shop_order_refresh_layout)
    BGARefreshLayout shopOrderRefreshLayout;
    @InjectView(R.id.not_network_refresh)
    Button notNetworkRefresh;
    @InjectView(R.id.not_netword_layout)
    RelativeLayout notNetwordLayout;
    @InjectView(R.id.not_data_layout)
    RelativeLayout notDataLayout;
    private String queryType = null;
    private ImplShopOrderPersenterImpl shopOrderPersenterImpl;
    private List<OrderShopBean> orderList = new ArrayList<OrderShopBean>();
    private ShopOrderAdapter orderAdapter;

    @OnClick(R.id.not_network_refresh)
    void refresh() {
        if (orderList.size() > 0) {
            orderList.clear();
        }
        shopOrderPersenterImpl.queryShopOrder(getQueryType(), 0, 10);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        if (orderList.size() > 0) {
            orderList.clear();
        }
        shopOrderPersenterImpl.queryShopOrder(getQueryType(), 0, 10);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        shopOrderPersenterImpl.queryShopOrder(getQueryType(), orderList.size(), 10);
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventCancel(EventCancel event) {
        if (shopOrderPersenterImpl != null) {
            if (orderList.size() > 0) {
                orderList.clear();
            }
            shopOrderPersenterImpl.queryShopOrder(getQueryType(), 0, 10);
        }
    }

    @Override
    public void onStart() {
        if (shopOrderPersenterImpl != null) {
            if (orderList.size() > 0) {
                orderList.clear();
            }
            shopOrderPersenterImpl.queryShopOrder(getQueryType(), 0, 10);
        }
        super.onStart();
    }

    @Override
    public View initView() {
        EventBus.getDefault().register(this);
        return View.inflate(getActivity(), R.layout.fragment_shop_order, null);
    }

    @Override
    public void initData() {
        initRefreshLayout();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        orderAdapter = new ShopOrderAdapter(orderList, getActivity());
        shopOrderRecyclerview.setAdapter(orderAdapter);
        shopOrderRecyclerview.setLayoutManager(linearLayoutManager);
        shopOrderRefreshLayout.setLinearLayoutManager(orderList, linearLayoutManager);
        shopOrderPersenterImpl = new ImplShopOrderPersenterImpl(this, getActivity());
//        shopOrderPersenterImpl.queryShopOrder(getQueryType(), 0, 10);
        orderAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {//订单详情
                Intent intent = new Intent(getActivity(), OrderDetailsActivity.class);
                intent.putExtra("OrderShopBean", orderList.get(position));
                startActivity(intent);
            }
        });
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
        EventBus.getDefault().unregister(this);
    }

    /**
     * 初始化刷新
     */
    private void initRefreshLayout() {
        //设置代理
        shopOrderRefreshLayout.setDelegate(this);
        //设置刷新风格
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(mContext, true);
        shopOrderRefreshLayout.setRefreshViewHolder(mBGARefreshViewHolder);
    }

    @Override
    public void orderSucced(List<OrderShopBean> data) {
        shopOrderRefreshLayout.endRefreshing();
        shopOrderRefreshLayout.endLoadingMore();
        orderList.addAll(data);
        if (orderList.size() == 0) {
            notDataLayout.setVisibility(View.VISIBLE);
            shopOrderRecyclerview.setVisibility(View.GONE);
            notNetwordLayout.setVisibility(View.GONE);
        } else {
            notDataLayout.setVisibility(View.GONE);
            shopOrderRecyclerview.setVisibility(View.VISIBLE);
            notNetwordLayout.setVisibility(View.GONE);
        }
        orderAdapter.notifyDataSetChanged();
    }

    @Override
    public void orderClose() {
        notDataLayout.setVisibility(View.GONE);
        shopOrderRecyclerview.setVisibility(View.GONE);
        notNetwordLayout.setVisibility(View.VISIBLE);
        shopOrderRefreshLayout.endRefreshing();
        shopOrderRefreshLayout.endLoadingMore();
    }

    @Override
    public void showProgressDialog() {

    }
}
