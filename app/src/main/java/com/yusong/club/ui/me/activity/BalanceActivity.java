package com.yusong.club.ui.me.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.me.adapter.MoneyAdapter;
import com.yusong.club.ui.me.mvp.entity.MoneyList;
import com.yusong.club.ui.me.mvp.implView.IBalanceView;
import com.yusong.club.ui.me.mvp.presenter.impl.BalancePresenterImpl;
import com.yusong.club.utils.AppUtils;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.SpaceItemDecoration;
import com.yusong.club.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.club.utils.bga.BGARefreshLayout;
import com.yusong.club.utils.bga.BGARefreshViewHolder;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * <ul>
 * <li>功能说明：余额界面</li>
 * <li>作者：杨文新 </li>
 * <li>时间：17/3/7 15:11 </li>
 * </ul>
 */
public class BalanceActivity extends BaseActivity implements IBalanceView, BGARefreshLayout.BGARefreshLayoutDelegate {

    @InjectView(R.id.ll_back)
    LinearLayout mLlBack;
    @InjectView(R.id.btn_recharge)
    Button mBtnRecharge;
    @InjectView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @InjectView(R.id.tv_money)
    TextView mTvMoney;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.bga_layout)
    BGARefreshLayout mBgaLayout;
    private BalancePresenterImpl mPresenter;
    int offset = 0;
    int limit = 10;
    List<MoneyList> mMoneyInfoList;
    MoneyAdapter mMoneyAdapter;

    @Override
    protected int layoutId() {
        return R.layout.activity_me_balance;
    }

    @Override
    public void setMoneyAdapter(List<MoneyList> data) {

        if (!AppUtils.listIsEmpty(data))
            if (mMoneyAdapter == null) {
                mMoneyInfoList = data;
                LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };
                mMoneyAdapter = new MoneyAdapter(mMoneyInfoList, this);
                mRecyclerView.setAdapter(mMoneyAdapter);
                mRecyclerView.addItemDecoration(new SpaceItemDecoration(1,0));
                mRecyclerView.setLayoutManager(mLinearLayoutManager);
            } else {
                if (mMoneyInfoList != null) {
                    int dataSize = data.size();
                    if (offset != 0) {
                        for (int i = 0; i < dataSize; i++) {
                            mMoneyInfoList.add(data.get(i));
                            mMoneyAdapter.notifyItemChanged(i);
                        }
                    } else {
                        mMoneyInfoList.clear();
                        mMoneyInfoList.addAll(data);
                        mMoneyAdapter.notifyDataSetChanged();
                    }
                }
            }
    }

    /**
     * 进行下拉刷新联网操作
     */
    @Override
    public void onBGARefreshLayoutBeginRefreshing(final BGARefreshLayout refreshayout) {
        offset = 0;
        mPresenter.queryBalance();
        mPresenter.queryMoneyList(CacheUtils.getToken(this), CacheUtils.getId(this), offset, limit);

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

        mPresenter.queryMoneyList(CacheUtils.getToken(this), CacheUtils.getId(this), offset, limit);

        return true;
    }

    //显示余额
    @Override
    public void updateBalance(String balance) {
        mTvMoney.setText(balance);
    }

    @Override
    public void initData() {
        initRefreshLayout();
        mTvTitle.setText("我的余额");
        mPresenter = new BalancePresenterImpl(this, this);
    }

    @OnClick({R.id.ll_back, R.id.btn_recharge})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.btn_recharge:
                startActivity(new Intent(BalanceActivity.this, RechargeActivity.class));
                break;
        }
    }

    // 通过代码方式控制进入正在刷新状态。
    public void beginRefreshing() {
        mBgaLayout.beginRefreshing();
    }

    /**
     * 初始化刷新
     */
    private void initRefreshLayout() {
        //设置代理
        mBgaLayout.setDelegate(this);
        //设置刷新风格
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(this, true);
        mBgaLayout.setRefreshViewHolder(mBGARefreshViewHolder);
    }

    @Override
    public void closeLoading() {
        mBgaLayout.endRefreshing();
        mBgaLayout.endLoadingMore();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        beginRefreshing();
    }

    @Override
    public void showProgressDialog() {

    }

}
