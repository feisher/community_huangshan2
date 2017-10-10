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

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseFragment;
import com.yusong.club.ui.charge.view.BaseDialog;
import com.yusong.club.ui.shoppers.used.activity.IssueActivity;
import com.yusong.club.ui.shoppers.used.adapter.MyUsedAdapter;
import com.yusong.club.ui.shoppers.used.bean.MyUsedBean;
import com.yusong.club.ui.shoppers.used.event.EventMyUsed;
import com.yusong.club.ui.shoppers.used.mvp.implView.ImplMyUsedView;
import com.yusong.club.ui.shoppers.used.mvp.presenter.impl.ImplMyUsedPersenterImpl;
import com.yusong.club.utils.SpaceItemDecoration;
import com.yusong.club.utils.ToastUtils;
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
 *         created at 2017/3/16 19:15.
 *         我发布的
 */

public class MyUsedFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate
        , ImplMyUsedView {
    @InjectView(R.id.my_used_recyclerview)
    RecyclerView myUsedRecyclerview;
    @InjectView(R.id.my_used_bga_refresh_layout)
    BGARefreshLayout myUsedBgaRefreshLayout;
    @InjectView(R.id.not_data_layout)
    RelativeLayout notDataLayout;
    @InjectView(R.id.not_network_message)
    TextView notNetworkMessage;
    @InjectView(R.id.not_network_refresh)
    Button notNetworkRefresh;
    @InjectView(R.id.not_netword_layout)
    RelativeLayout notNetwordLayout;

    @OnClick(R.id.not_network_refresh)
    void refresh() {//无网络情况下刷新
        isRefresh = true;
        myUsedPersenterImpl.queryMyUsed(0, 10);
    }

    private List<MyUsedBean> myUsedBeanList = new ArrayList<MyUsedBean>();
    private ImplMyUsedPersenterImpl myUsedPersenterImpl;
    private MyUsedAdapter myUsedAdapter;
    private boolean isRefresh = false;

    @Override
    public View initView() {
        return View.inflate(getActivity(), R.layout.fragment_me_used, null);
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        initRefreshLayout();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        myUsedAdapter = new MyUsedAdapter(myUsedBeanList, getActivity());
        myUsedRecyclerview.setAdapter(myUsedAdapter);
        myUsedRecyclerview.setLayoutManager(linearLayoutManager);
        myUsedRecyclerview.addItemDecoration(new SpaceItemDecoration(10, 0));
        myUsedBgaRefreshLayout.setLinearLayoutManager(myUsedBeanList, linearLayoutManager);
        myUsedPersenterImpl = new ImplMyUsedPersenterImpl(this, getActivity());
        myUsedPersenterImpl.queryMyUsed(0, 10);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        isRefresh = true;
        myUsedPersenterImpl.queryMyUsed(0, 10);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        isRefresh = false;
        myUsedPersenterImpl.queryMyUsed(myUsedBeanList.size(), 10);
        return true;
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

    /**
     * 初始化刷新
     */
    private void initRefreshLayout() {
        //设置代理
        myUsedBgaRefreshLayout.setDelegate(this);
        //设置刷新风格
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(mContext, true);
        myUsedBgaRefreshLayout.setRefreshViewHolder(mBGARefreshViewHolder);
    }

    public void closeRefresh() {
        myUsedBgaRefreshLayout.endRefreshing();
        myUsedBgaRefreshLayout.endLoadingMore();
    }

    @Override
    public void close() {
        closeRefresh();
        myUsedBgaRefreshLayout.setVisibility(View.GONE);
        notNetwordLayout.setVisibility(View.VISIBLE);
        notDataLayout.setVisibility(View.GONE);

    }

    @Override
    public void myUsedSucced(List<MyUsedBean> data) {//列表获取成功
        closeRefresh();
        if (isRefresh) {
            if (myUsedBeanList.size() > 0) {
                myUsedBeanList.clear();
            }
        }
        myUsedBeanList.addAll(data);
        if (myUsedBeanList.size() == 0) {
            myUsedBgaRefreshLayout.setVisibility(View.GONE);
            notNetwordLayout.setVisibility(View.GONE);
            notDataLayout.setVisibility(View.VISIBLE);
        } else {
            myUsedBgaRefreshLayout.setVisibility(View.VISIBLE);
            notNetwordLayout.setVisibility(View.GONE);
            notDataLayout.setVisibility(View.GONE);
        }
        myUsedAdapter.notifyDataSetChanged();
        isRefresh = false;
    }

    @Override
    public void outSucced() {//下架成功
        ToastUtils.showShort(getActivity(), "下架成功");
        isRefresh = true;
        myUsedPersenterImpl.queryMyUsed(0, myUsedBeanList.size());
    }

    @Override
    public void deleteSucced() {//删除成功
        ToastUtils.showShort(getActivity(), "删除成功");
        isRefresh = true;
        myUsedPersenterImpl.queryMyUsed(0, myUsedBeanList.size());
    }

    @Override
    public void showProgressDialog() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMyUsedEvent(final EventMyUsed event) {
        if (event.getType() == 1) {
            Intent intent = new Intent(getActivity(), IssueActivity.class);
            intent.putExtra("MyUsedBean", event.getBean());
            startActivity(intent);
        } else {
            final BaseDialog dialog = new BaseDialog(getActivity());
            dialog.setTitle("管家提示");
            switch (event.getType()) {
                case 2:
                    dialog.setMessage("下架之后无法上架的哦!");
                    break;
                case 3:
                    dialog.setMessage("删除后无法恢复的哦！");
                    break;
                default:
                    break;
            }
            dialog.setPositiveButton("继续", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (event.getType() == 2) {
                        myUsedPersenterImpl.outUsed(event.getBean().getId());
                    } else if (event.getType() == 3) {
                        myUsedPersenterImpl.deleteUsed(event.getBean().getId());
                    }
                    dialog.dismiss();
                }
            });
            dialog.setNegativeButton("不了", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
        }


    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        myUsedPersenterImpl.onDestroy();
        super.onDestroy();
    }
}
