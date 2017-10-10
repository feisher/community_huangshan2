package com.yusong.club.ui.charge.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseFragment;
import com.yusong.club.ui.charge.activity.OrderDetailsActivity;
import com.yusong.club.ui.charge.adapter.WanchenOrderAdapter;
import com.yusong.club.ui.charge.bean.MyOrderBean;
import com.yusong.club.ui.charge.mvp.implView.ICharMyOrderView;
import com.yusong.club.ui.charge.mvp.presenter.impl.ICharMyOrderPresenterImpl;
import com.yusong.club.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.club.utils.bga.BGARefreshLayout;
import com.yusong.club.utils.bga.BGARefreshViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mr_Peng on 2017/1/3.
 */

public class YiwanchengFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate, ICharMyOrderView {
    @InjectView(R.id.order_list_view)
    ListView orderListView;
    @InjectView(R.id.order_refresh_layout)
    BGARefreshLayout orderRefreshLayout;
    private ICharMyOrderPresenterImpl myOrderPresenter;
    private WanchenOrderAdapter orderAdapter;
    private List<MyOrderBean> list = new ArrayList<MyOrderBean>();


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        list.clear();
        myOrderPresenter.queryMyOrder("complete", 0, 15);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        myOrderPresenter.queryMyOrder("complete", list.size(), 15);
        return true;
    }

    @Override
    public View initView() {
        return View.inflate(mContext, R.layout.fragment_charge_my_order, null);
    }

    @Override
    public void initData() {
        initRefreshLayout();
        orderAdapter = new WanchenOrderAdapter(mContext, list);
        orderListView.setAdapter(orderAdapter);
        myOrderPresenter = new ICharMyOrderPresenterImpl(this, getActivity());
        myOrderPresenter.queryMyOrder("complete", 0, 15);
        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyOrderBean bean = list.get(i);
                Intent intent = new Intent(getActivity(), OrderDetailsActivity.class);
                intent.putExtra("OrderBean", bean);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initListener() {

    }

    private void initRefreshLayout() {
        //设置代理
        orderRefreshLayout.setDelegate(this);
        //设置刷新风格
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(mContext, true);
        orderRefreshLayout.setRefreshViewHolder(mBGARefreshViewHolder);
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
    public void refreshView(List<MyOrderBean> data) {
        list.addAll(data);
        orderAdapter.notifyDataSetChanged();
        stopRefrsh();
    }

//    @Override
//    public void showLoading() {
//
//    }
//
//    @Override
//    public void hideLoading() {
//        stopRefrsh();
//    }
//
//    @Override
//    public void showMessage(String message) {
//        ToastUtils.showShort(getActivity().getApplicationContext(), message);
//        stopRefrsh();
//    }

    public void stopRefrsh() {
        if (orderRefreshLayout != null) {
            orderRefreshLayout.endRefreshing();
            orderRefreshLayout.endLoadingMore();
        }
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(getActivity());
    }
}
