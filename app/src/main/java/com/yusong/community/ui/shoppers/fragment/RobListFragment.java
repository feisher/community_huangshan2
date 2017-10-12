package com.yusong.community.ui.shoppers.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseFragment;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.shoppers.activity.CommodityDetailsActivity;
import com.yusong.community.ui.shoppers.activity.RobShopDetailsActivity;
import com.yusong.community.ui.shoppers.adapter.QcommundityAdapter;
import com.yusong.community.ui.shoppers.bean.CommodityBean;
import com.yusong.community.ui.shoppers.mvp.implView.ImplQueryQianggouListView;
import com.yusong.community.ui.shoppers.mvp.presenter.impl.ImplQueryQianggouListPresenterImpl;
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
 *         created at 2017/3/8 14:35.
 *         抢购商品列表
 */
public class RobListFragment extends BaseFragment implements ImplQueryQianggouListView,
        BGARefreshLayout.BGARefreshLayoutDelegate {

    @InjectView(R.id.lv_limit)
    RecyclerView lvLimit;
    @InjectView(R.id.refresh_communtidy_layout)
    BGARefreshLayout refreshCommuntidyLayout;
    private int categoryId = -1;//分类id
    private ImplQueryQianggouListPresenterImpl qianggouListPresenter;
    private List<CommodityBean> list = new ArrayList<CommodityBean>();
    private QcommundityAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public void refreshQiangGouList(List<CommodityBean> data) {//抢购商品列表
        closeRefresh();
        list.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressDialog() {

    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public View initView() {
        return View.inflate(getActivity(), R.layout.fragment_shop_limit, null);
    }

    @Override
    public void initData() {
        initRefreshLayout();
        qianggouListPresenter = new ImplQueryQianggouListPresenterImpl(this, getActivity());
        qianggouListPresenter.queryQianggouList(RobShopDetailsActivity.QIANGGOU_ID, getCategoryId(), 0, 10);
        adapter = new QcommundityAdapter(list, mContext);
        linearLayoutManager = new LinearLayoutManager(mContext);
        lvLimit.setLayoutManager(linearLayoutManager);
        lvLimit.setAdapter(adapter);
        lvLimit.addItemDecoration(new SpaceItemDecoration(2, 0));
        refreshCommuntidyLayout.setLinearLayoutManager(list,linearLayoutManager);

    }

    @Override
    public void initListener() {
        adapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                Intent intent = new Intent(getActivity(), CommodityDetailsActivity.class);
                intent.putExtra("itemId", list.get(position).getId());
                intent.putExtra("isQG", 2);
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
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        if (list.size() > 0) {
            list.clear();
        }
        qianggouListPresenter.queryQianggouList(RobShopDetailsActivity.QIANGGOU_ID, getCategoryId(), 0, 10);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        qianggouListPresenter.queryQianggouList(RobShopDetailsActivity.QIANGGOU_ID, getCategoryId(), list.size(), 10);
        return true;
    }

    /**
     * 初始化刷新
     */
    private void initRefreshLayout() {
        //设置代理
        refreshCommuntidyLayout.setDelegate(this);
        //设置刷新风格
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(mContext, true);
        refreshCommuntidyLayout.setRefreshViewHolder(mBGARefreshViewHolder);
    }

    public void closeRefresh() {
        refreshCommuntidyLayout.endRefreshing();
        refreshCommuntidyLayout.endLoadingMore();
    }

}
