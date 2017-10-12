package com.yusong.community.ui.supermarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseFragment;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.shoppers.activity.CommodityDetailsActivity;
import com.yusong.community.ui.shoppers.bean.FenLeiBean;
import com.yusong.community.ui.supermarket.adpter.SMAdapter;
import com.yusong.community.ui.supermarket.entity.SMCommodityBean;
import com.yusong.community.ui.supermarket.entity.SuperMarketDetailsBean;
import com.yusong.community.ui.supermarket.mvp.ImolView.QuerySMView;
import com.yusong.community.ui.supermarket.mvp.presenter.impl.QuerySMPresenterImpl;
import com.yusong.community.utils.SpaceItemDecoration;
import com.yusong.community.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.community.utils.bga.BGARefreshLayout;
import com.yusong.community.utils.bga.BGARefreshViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Mr_Peng
 * @created at 2017-09-05.
 * @describe: null
 */

public class SuperMarketFragment extends BaseFragment implements QuerySMView, BGARefreshLayout.BGARefreshLayoutDelegate {
    @InjectView(R.id.sm_recycler_view)
    RecyclerView smRecyclerView;
    @InjectView(R.id.sm_refresh_layout)
    BGARefreshLayout smRefreshLayout;
    private int categoryId;

    private QuerySMPresenterImpl presenter;
    private List<SMCommodityBean> commodityBeanList = new ArrayList<SMCommodityBean>();
    private boolean isClear = true;
    private SMAdapter adapter;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public View initView() {
        return View.inflate(mContext, R.layout.fragment_super_market, null);
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
        adapter = new SMAdapter(commodityBeanList, getActivity());
        smRecyclerView.setAdapter(adapter);
        smRecyclerView.addItemDecoration(new SpaceItemDecoration(10, 5));
        smRefreshLayout.setGridLayoutManager(commodityBeanList, layoutManager);
        adapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                Intent intent = new Intent(getActivity(), CommodityDetailsActivity.class);
                intent.putExtra("itemId", commodityBeanList.get(position).getId());
                intent.putExtra("isQG", 3);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter = new QuerySMPresenterImpl(this, getActivity());
        presenter.queryCommodity(categoryId, 0, 20);
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
    public void querySMSucces(SuperMarketDetailsBean data) {

    }

    @Override
    public void querFenleiSucces(List<FenLeiBean> been) {

    }

    @Override
    public void queryCommoditySucces(List<SMCommodityBean> been) {
        if (isClear) {
            if (been.size() > 0) {
                commodityBeanList.clear();
            }
        }
        commodityBeanList.addAll(been);
        adapter.notifyDataSetChanged();
        smRefreshLayout.endLoadingMore();
        smRefreshLayout.endRefreshing();
    }

    @Override
    public void queryCommodityError() {
        smRefreshLayout.endLoadingMore();
        smRefreshLayout.endRefreshing();
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
        presenter.queryCommodity(categoryId, 0, 20);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        isClear = false;
        presenter.queryCommodity(categoryId, commodityBeanList.size(), 20);
        return true;
    }
}
