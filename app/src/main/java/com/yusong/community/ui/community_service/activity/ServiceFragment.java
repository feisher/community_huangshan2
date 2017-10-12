package com.yusong.community.ui.community_service.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yusong.community.R;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.community_service.adapter.ServiceAdapter;
import com.yusong.community.ui.community_service.entity.ServiceBean;
import com.yusong.community.ui.community_service.entity.ServiceDetailBean;
import com.yusong.community.ui.community_service.mvp.ImplView.ServiceView;
import com.yusong.community.ui.community_service.mvp.presenter.ImplPresenter.ServicePresenterImpl;
import com.yusong.community.ui.shoppers.bean.FenLeiBean;
import com.yusong.community.utils.SpaceItemDecoration;
import com.yusong.community.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.community.utils.bga.BGARefreshLayout;
import com.yusong.community.utils.bga.BGARefreshViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.yusong.community.ui.base.BaseFragment;

/**
 * @author Mr_Peng
 * @created at 2017-09-22.
 * @describe: null
 */

public class ServiceFragment extends BaseFragment implements ServiceView, BGARefreshLayout.BGARefreshLayoutDelegate {

    @InjectView(R.id.sm_recycler_view)
    RecyclerView smRecyclerView;
    @InjectView(R.id.sm_refresh_layout)
    BGARefreshLayout smRefreshLayout;
    private int categoryId;

    private ServicePresenterImpl presenter;
    private List<ServiceBean> commodityBeanList = new ArrayList<ServiceBean>();
    private boolean isClear = true;
    private ServiceAdapter adapter;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public View initView() {
        return View.inflate(mContext, R.layout.fragment_service, null);
    }

    @Override
    public void initData() {
        initRefreshLayout();
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2) {
            @Override
            public boolean canScrollVertically() {
                return true;
            }
        };
        smRecyclerView.setLayoutManager(layoutManager);
        adapter = new ServiceAdapter(commodityBeanList, getActivity());
        smRecyclerView.setAdapter(adapter);
        smRecyclerView.addItemDecoration(new SpaceItemDecoration(10, 5));
        smRefreshLayout.setGridLayoutManager(commodityBeanList, layoutManager);
        adapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                Intent intent = new Intent(getActivity(), ServiceDetailsActivity.class);
                intent.putExtra("itemId", commodityBeanList.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter = new ServicePresenterImpl(this, getActivity());
        presenter.queryServiceList(categoryId, 0, 20);
    }

    private void initRefreshLayout() {
        //设置代理
        smRefreshLayout.setDelegate(this);
        //设置刷新风格
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(mContext, true);
        smRefreshLayout.setRefreshViewHolder(mBGARefreshViewHolder);
    }

    @Override
    public void showProgressDialog() {

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
    public void onDestroy() {
        presenter.onDestroy();
        commodityBeanList.clear();
        super.onDestroy();
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        isClear = true;
        presenter.queryServiceList(categoryId, 0, 20);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        isClear = false;
        presenter.queryServiceList(categoryId, commodityBeanList.size(), 20);
        return true;
    }

    @Override
    public void queryServiceSucces(ServiceDetailBean bean) {

    }

    @Override
    public void queryServiceCategorySucces(List<FenLeiBean> datas) {

    }

    @Override
    public void queryServiceListSucces(List<ServiceBean> datas) {
        if (isClear) {
            if (datas.size() > 0) {
                commodityBeanList.clear();
            }
        }
        commodityBeanList.addAll(datas);
        adapter.notifyDataSetChanged();
        smRefreshLayout.endLoadingMore();
        smRefreshLayout.endRefreshing();
    }

    @Override
    public void queryServiceListError() {
        smRefreshLayout.endLoadingMore();
        smRefreshLayout.endRefreshing();
    }
}
