package com.yusong.club.ui.express.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.pay.CommonPayActivity;
import com.yusong.club.ui.base.BaseFragment;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.express.activity.GetOrderDetailsActivity;
import com.yusong.club.ui.express.activity.LogisticsInfoActivity;
import com.yusong.club.ui.express.activity.SenderOrderDetailsActivity;
import com.yusong.club.ui.express.adapter.GetOrderAdapter;
import com.yusong.club.ui.express.adapter.SaveOrderAdapter;
import com.yusong.club.ui.express.adapter.SenderOrderAdapter;
import com.yusong.club.ui.express.event.OpenBox;
import com.yusong.club.ui.express.mvp.entity.GetOrderInfo;
import com.yusong.club.ui.express.mvp.entity.SaveOrderInfo;
import com.yusong.club.ui.express.mvp.entity.ScanOrder;
import com.yusong.club.ui.express.mvp.entity.SenderOrderInfo;
import com.yusong.club.ui.express.mvp.implView.IOrderView;
import com.yusong.club.ui.express.mvp.presenter.impl.IOrderPresenterIpml;
import com.yusong.club.utils.ActivityConstants;
import com.yusong.club.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.club.utils.bga.BGARefreshLayout;
import com.yusong.club.utils.bga.BGARefreshViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.InjectView;

/**
 * <ul>
 * <li>功能说明：取件、寄件、存包订单列表</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */
public class SimpleOrderFragment extends BaseFragment implements IOrderView, BGARefreshLayout.BGARefreshLayoutDelegate {

    @InjectView(R.id.rl_order)
    RecyclerView mRlOrder;
    @InjectView(R.id.rl_modulename_refresh)
    BGARefreshLayout mRlModulenameRefresh;
    private IOrderPresenterIpml mPresenter;
    private String type;
    int requestId;//请求apiId
    int offset = 0;
    int limit = 10;
    private List<SaveOrderInfo> mSaveOrderInfoList;
    private List<SenderOrderInfo> mSenderOrderInfoList;
    private List<GetOrderInfo> mGetOrderInfoList;
    private SaveOrderAdapter mSaveOrderAdapter;
    private SenderOrderAdapter mSenderOrderAdapter;
    private GetOrderAdapter mGetOrderAdapter;

    public static SimpleOrderFragment newInstance(String type, int requestId) {
        Bundle bundle = new Bundle();
        bundle.putInt("requestId", requestId);
        bundle.putString("type", type);
        SimpleOrderFragment mSimpleTaskFragment = new SimpleOrderFragment();
        mSimpleTaskFragment.setArguments(bundle);

        return mSimpleTaskFragment;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    /**
     * 进行下拉刷新联网操作
     */
    @Override
    public void onBGARefreshLayoutBeginRefreshing(final BGARefreshLayout refreshayout) {

        offset = 0;

        if (requestId == 1) {//请求收件
            mPresenter.requestGetOrder(type, offset, limit);
        } else if (requestId == 2) {//请求寄件
            mPresenter.requestSenderOrder(type, offset, limit);
        } else if (requestId == 3) {//请求存包
            mPresenter.requestSaveOrder(type, offset, limit);
        }

    }

    /**
     * 进行上拉加载联网操作
     *
     * @param refreshLayout
     * @return
     */
    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(final BGARefreshLayout refreshLayout) {

        offset += 10;

        if (requestId == 1) {//请求收件
            mPresenter.requestGetOrder(type, offset, limit);
        } else if (requestId == 2) {//请求寄件
            mPresenter.requestSenderOrder(type, offset, limit);
        } else if (requestId == 3) {//请求存包
            mPresenter.requestSaveOrder(type, offset, limit);
        }
        
        return true;
    }

    //存包订单列表
    @Override
    public void setSaveOrderAdapter(List<SaveOrderInfo> data) {

        hideRefresh();

        if (mSaveOrderAdapter == null) {
            mSaveOrderInfoList = data;
            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext);
            mSaveOrderAdapter = new SaveOrderAdapter(mSaveOrderInfoList, mContext);
            mRlOrder.setAdapter(mSaveOrderAdapter);
            mRlOrder.setLayoutManager(mLinearLayoutManager);
            mSaveOrderAdapter.setOnBtnClickListener(new SaveOrderAdapter.OnBtnClickListener() {
                @Override
                public void onClick(String token, int type, String id) {
                    EventBus.getDefault().post(
                            new OpenBox(token, type, id));
                }
            });
            mRlModulenameRefresh.setLinearLayoutManager(data, mLinearLayoutManager);
        } else {
            if (data != null && mSaveOrderInfoList != null) {
                int dataSize = data.size();
                if (offset != 0) {
                    for (int i = 0; i < dataSize; i++) {
                        mSaveOrderInfoList.add(data.get(i));
                        mSaveOrderAdapter.notifyItemChanged(i);
                    }
                } else {
                    mSaveOrderInfoList.clear();
                    mSaveOrderInfoList.addAll(data);
                    mSaveOrderAdapter.notifyDataSetChanged();
                }
            }
        }

    }

    //寄件订单列表
    @Override
    public void setSenderOrderAdapter(List<SenderOrderInfo> data) {
        hideRefresh();

        if (mSenderOrderAdapter == null) {
            mSenderOrderInfoList = data;
            final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext);
            mSenderOrderAdapter = new SenderOrderAdapter(mSenderOrderInfoList, mContext);
            mRlOrder.setAdapter(mSenderOrderAdapter);
            mRlOrder.setLayoutManager(mLinearLayoutManager);
            mSenderOrderAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, Object data, int position) {
                    SenderOrderInfo mSenderOrderInfo = (SenderOrderInfo) data;
                    Intent mIntent = new Intent(getActivity(), SenderOrderDetailsActivity.class);
                    mIntent.putExtra("id", mSenderOrderInfo.getId());
                    startActivity(mIntent);
                }
            });
            mRlModulenameRefresh.setLinearLayoutManager(data, mLinearLayoutManager);

            mSenderOrderAdapter.setBtnClickLisener(new SenderOrderAdapter.BtnClickLisener() {
                @Override
                public void btnClick(String id, int position) {
                    mPresenter.cancleOrder(id, position);
                }
            });

        } else {
            if (data != null && mSenderOrderInfoList != null) {
                int dataSize = data.size();
                if (offset != 0) {
                    for (int i = 0; i < dataSize; i++) {
                        mSenderOrderInfoList.add(data.get(i));
                        mSenderOrderAdapter.notifyItemChanged(i);
                    }
                } else {
                    mSenderOrderInfoList.clear();
                    mSenderOrderInfoList.addAll(data);
                    mSenderOrderAdapter.notifyDataSetChanged();
                }
            }
        }

    }

    //取件订单列表
    @Override
    public void setGetOrderAdapter(List<GetOrderInfo> data) {
        hideRefresh();

        if (mGetOrderAdapter == null) {
            mGetOrderInfoList = data;
            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext);
            mGetOrderAdapter = new GetOrderAdapter(data, mContext);
            mRlOrder.setAdapter(mGetOrderAdapter);
            mRlOrder.setLayoutManager(mLinearLayoutManager);
            mGetOrderAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, Object data, int position) {
                    GetOrderInfo mGetOrderInfo = (GetOrderInfo) data;
                    Intent mIntent = new Intent(getActivity(), GetOrderDetailsActivity.class);
                    mIntent.putExtra("id", mGetOrderInfo.getId());
                    startActivity(mIntent);
                }
            });

            mGetOrderAdapter.setOnBtnClickListener(new GetOrderAdapter.OnBtnClickListener() {
                @Override
                public void onClick(String token, int type, String id) {
                    mPresenter.openBoxEvent(token, type, id);
                }
            });

            mRlModulenameRefresh.setLinearLayoutManager(data, mLinearLayoutManager);
        } else {
            if (data != null && mGetOrderInfoList != null) {
                int dataSize = data.size();
                if (offset != 0) {
                    for (int i = 0; i < dataSize; i++) {
                        mGetOrderInfoList.add(data.get(i));
                        mGetOrderAdapter.notifyItemChanged(i);
                    }
                } else {
                    mGetOrderInfoList.clear();
                    mGetOrderInfoList.addAll(data);
                    mGetOrderAdapter.notifyDataSetChanged();
                }
            }
        }
    }


    @Override
    public void hideRefresh() {
        if (mRlModulenameRefresh != null) {
            mRlModulenameRefresh.endRefreshing();
            mRlModulenameRefresh.endLoadingMore();
        }
    }

    @Override
    public void jumpActivity(ScanOrder.ShipperInfo order, String number) {
        Intent mIntent = new Intent(getActivity(), LogisticsInfoActivity.class);
        mIntent.putExtra(ActivityConstants.ORDER_ID, number);
        mIntent.putExtra(ActivityConstants.ShipperCode, order.getShipperCode());
        mIntent.putExtra(ActivityConstants.ShipperName, order.getShipperName());
        startActivity(mIntent);
    }

    @Override
    public void refreshList(int position) {

        mSenderOrderInfoList.remove(position);
        mSenderOrderAdapter.notifyDataSetChanged();
    }

    @Override
    public void jumpActivity(int charge, String order_id) {
        Intent mIntent = new Intent(getActivity(), CommonPayActivity.class);
        mIntent.putExtra(ActivityConstants.CHARGE, charge / 100 + "");
        mIntent.putExtra(ActivityConstants.COMMON_PAYTYPE, ActivityConstants.QUJIAN_OERDER);
        mIntent.putExtra(ActivityConstants.ORDER_ID, order_id);
        startActivity(mIntent);
    }


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_express_order, null);
        return view;
    }

    @Override
    public void initData() {
        initRefreshLayout();
        mPresenter = new IOrderPresenterIpml(this, mContext);
        beginRefreshing();

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            type = bundle.getString("type");
            requestId = bundle.getInt("requestId");
        }
    }

    // 通过代码方式控制进入正在刷新状态。
    public void beginRefreshing() {
        mRlModulenameRefresh.beginRefreshing();
    }

    /**
     * 初始化刷新
     */
    private void initRefreshLayout() {
        //设置代理
        mRlModulenameRefresh.setDelegate(this);
        //设置刷新风格
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(mContext, true);
        mRlModulenameRefresh.setRefreshViewHolder(mBGARefreshViewHolder);
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }


    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(getActivity());
    }
}
