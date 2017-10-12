package com.yusong.community.ui.charge.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseFragment;
import com.yusong.community.ui.charge.activity.OrderDetailsActivity;
import com.yusong.community.ui.charge.adapter.YifuOrderAdapter;
import com.yusong.community.ui.charge.bean.MyOrderBean;
import com.yusong.community.ui.charge.mvp.implView.ICharMyOrderView;
import com.yusong.community.ui.charge.mvp.implView.IChargeCancelOrderView;
import com.yusong.community.ui.charge.mvp.presenter.impl.ICharMyOrderPresenterImpl;
import com.yusong.community.ui.charge.mvp.presenter.impl.IChargeCancelOrderPresenterImpl;
import com.yusong.community.utils.ToastUtils;
import com.yusong.community.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.community.utils.bga.BGARefreshLayout;
import com.yusong.community.utils.bga.BGARefreshViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mr_Peng on 2017/1/3.
 */

public class YifuFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate,
        ICharMyOrderView ,IChargeCancelOrderView {
    @InjectView(R.id.order_list_view)
    ListView orderListView;
    @InjectView(R.id.order_refresh_layout)
    BGARefreshLayout orderRefreshLayout;
    private ICharMyOrderPresenterImpl myOrderPresenter;
    private YifuOrderAdapter yifuOrderAdapter;
    private List<MyOrderBean> yifuList = new ArrayList<MyOrderBean>();
    private IChargeCancelOrderPresenterImpl cancelOrderPresenterImpl;


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        yifuList.clear();
        myOrderPresenter.queryMyOrder("payed", 0, 15);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        myOrderPresenter.queryMyOrder("payed", yifuList.size(), 15);
        return true;
    }

    @Override
    public View initView() {
        return View.inflate(mContext, R.layout.fragment_charge_my_order, null);
    }

    @Override
    public void initData() {
        initRefreshLayout();
        cancelOrderPresenterImpl = new IChargeCancelOrderPresenterImpl(this, getActivity());
        yifuOrderAdapter = new YifuOrderAdapter(mContext, yifuList,cancelOrderPresenterImpl);
        orderListView.setAdapter(yifuOrderAdapter);
        myOrderPresenter = new ICharMyOrderPresenterImpl(this, getActivity());
        myOrderPresenter.queryMyOrder("payed", 0, 15);
        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyOrderBean bean = yifuList.get(i);
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
        yifuList.clear();
        ButterKnife.reset(this);
    }

    @Override
    public void refreshView(List<MyOrderBean> data) {
        yifuList.addAll(data);
        yifuOrderAdapter.notifyDataSetChanged();
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

//    @Override
//    public void showMessage(String message) {
//        ToastUtils.showShort(getActivity().getApplicationContext(),message);
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

    @Override
    public void cancelMessage(String message) {
        ToastUtils.showShort(getActivity().getApplicationContext(), message);
        if (message.equals("取消成功")) {
            myOrderPresenter.queryMyOrder("payed", 0, yifuList.size());
            yifuList.clear();
        }
        stopRefrsh();
    }
}
