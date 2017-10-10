package com.yusong.club.ui.supermarket;

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
import com.yusong.club.ui.shoppers.adapter.ShopOrderAdapter;
import com.yusong.club.ui.shoppers.bean.CreataOrderBean;
import com.yusong.club.ui.shoppers.bean.OrderShopBean;
import com.yusong.club.ui.supermarket.mvp.ImolView.SuperMarketOrderView;
import com.yusong.club.ui.supermarket.mvp.presenter.impl.SuperMarketPresenterImpl;
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
 * @created at 2017-09-06.
 * @describe: null
 */

public class SuperMarketOrderFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate, SuperMarketOrderView {
    @InjectView(R.id.sm_order_recyclerview)
    RecyclerView smOrderRecyclerview;
    @InjectView(R.id.sm_order_refresh_layout)
    BGARefreshLayout smOrderRefreshLayout;
    @InjectView(R.id.not_network_refresh)
    Button notNetworkRefresh;
    @InjectView(R.id.not_netword_layout)
    RelativeLayout notNetwordLayout;
    @InjectView(R.id.not_data_layout)
    RelativeLayout notDataLayout;
    private String queryType = null;
    private SuperMarketPresenterImpl superMarketPresenter;
    private List<OrderShopBean> orderList = new ArrayList<OrderShopBean>();
    private ShopOrderAdapter orderAdapter;

    @OnClick(R.id.not_network_refresh)
    void refresh() {
        if (orderList.size() > 0) {
            orderList.clear();
        }
        superMarketPresenter.querySMOrder(getQueryType(), 0, 10);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        if (orderList.size() > 0) {
            orderList.clear();
        }
        superMarketPresenter.querySMOrder(getQueryType(), 0, 10);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        superMarketPresenter.querySMOrder(getQueryType(), orderList.size(), 10);
        return true;
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onEventCancel(EventCancel event) {
//        if (superMarketPresenter != null) {
//            if (orderList.size() > 0) {
//                orderList.clear();
//            }
//            superMarketPresenter.querySMOrder(getQueryType(), 0, 10);
//        }
//    }

    @Override
    public void onStart() {
        if (superMarketPresenter != null) {
            if (orderList.size() > 0) {
                orderList.clear();
            }
            superMarketPresenter.querySMOrder(getQueryType(), 0, 10);
        }
        super.onStart();
    }

    @Override
    public View initView() {
        return View.inflate(getActivity(), R.layout.fragment_super_market_order, null);
    }

    @Override
    public void initData() {
        initRefreshLayout();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        orderAdapter = new ShopOrderAdapter(orderList, getActivity());
        smOrderRecyclerview.setAdapter(orderAdapter);
        smOrderRecyclerview.setLayoutManager(linearLayoutManager);
        smOrderRefreshLayout.setLinearLayoutManager(orderList, linearLayoutManager);
        superMarketPresenter = new SuperMarketPresenterImpl(this, getActivity());
//        shopOrderPersenterImpl.queryShopOrder(getQueryType(), 0, 10);
        orderAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {//订单详情
                Intent intent = new Intent(getActivity(), SmOrderDetailsActivity.class);
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
    }

    /**
     * 初始化刷新
     */
    private void initRefreshLayout() {
        //设置代理
        smOrderRefreshLayout.setDelegate(this);
        //设置刷新风格
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(mContext, true);
        smOrderRefreshLayout.setRefreshViewHolder(mBGARefreshViewHolder);
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void createSucced(CreataOrderBean bean) {

    }

    @Override
    public void querySMOrderSucced(List<OrderShopBean> data) {
        smOrderRefreshLayout.endRefreshing();
        smOrderRefreshLayout.endLoadingMore();
        orderList.addAll(data);
        if (orderList.size() == 0) {
            notDataLayout.setVisibility(View.VISIBLE);
            smOrderRecyclerview.setVisibility(View.GONE);
            notNetwordLayout.setVisibility(View.GONE);
        } else {
            notDataLayout.setVisibility(View.GONE);
            smOrderRecyclerview.setVisibility(View.VISIBLE);
            notNetwordLayout.setVisibility(View.GONE);
        }
        orderAdapter.notifyDataSetChanged();
    }

    @Override
    public void querySMOrderError() {
        notDataLayout.setVisibility(View.GONE);
        smOrderRecyclerview.setVisibility(View.GONE);
        notNetwordLayout.setVisibility(View.VISIBLE);
        smOrderRefreshLayout.endRefreshing();
        smOrderRefreshLayout.endLoadingMore();
    }
}
