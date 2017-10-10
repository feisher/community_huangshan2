package com.yusong.club.ui.community_service.activity;

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
import com.yusong.club.ui.community_service.adapter.ServiceOrderAdapter;
import com.yusong.club.ui.community_service.entity.ServiceOrderBean;
import com.yusong.club.ui.community_service.mvp.ImplView.ServiceOrderView;
import com.yusong.club.ui.community_service.mvp.presenter.ImplPresenter.ServiceOrderPresenterImpl;
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
 * @created at 2017-09-23.
 * @describe: null
 */

public class ServiceOrderFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate,ServiceOrderView {
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
    private ServiceOrderPresenterImpl mPresenter;
    private List<ServiceOrderBean> orderList = new ArrayList<ServiceOrderBean>();
    private ServiceOrderAdapter orderAdapter;

    @OnClick(R.id.not_network_refresh)
    void refresh() {
        if (orderList.size() > 0) {
            orderList.clear();
        }
        mPresenter.queryServiceOrderList(getQueryType(), 0, 10);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        if (orderList.size() > 0) {
            orderList.clear();
        }
        mPresenter.queryServiceOrderList(getQueryType(), 0, 10);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mPresenter.queryServiceOrderList(getQueryType(), orderList.size(), 10);
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
        if (mPresenter != null) {
            if (orderList.size() > 0) {
                orderList.clear();
            }
            mPresenter.queryServiceOrderList(getQueryType(), 0, 10);
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
        orderAdapter = new ServiceOrderAdapter(orderList, getActivity());
        smOrderRecyclerview.setAdapter(orderAdapter);
        smOrderRecyclerview.setLayoutManager(linearLayoutManager);
        smOrderRefreshLayout.setLinearLayoutManager(orderList, linearLayoutManager);
        mPresenter = new ServiceOrderPresenterImpl(this, getActivity());
        orderAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {//订单详情
                Intent intent = new Intent(getActivity(), ServiceOrderDetailsActivity.class);
                intent.putExtra("ServiceOrderBean", orderList.get(position));
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
    public void queryOrderSucces(List<ServiceOrderBean> beanList) {
        smOrderRefreshLayout.endRefreshing();
        smOrderRefreshLayout.endLoadingMore();
        orderList.addAll(beanList);
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
    public void queryOrderError() {
        notDataLayout.setVisibility(View.GONE);
        smOrderRecyclerview.setVisibility(View.GONE);
        notNetwordLayout.setVisibility(View.VISIBLE);
        smOrderRefreshLayout.endRefreshing();
        smOrderRefreshLayout.endLoadingMore();
    }

    @Override
    public void commitServiceCommentSucces() {

    }

    @Override
    public void cancelServiceOrderSucces() {

    }

    @Override
    public void confirmOrderSucces() {

    }
}
