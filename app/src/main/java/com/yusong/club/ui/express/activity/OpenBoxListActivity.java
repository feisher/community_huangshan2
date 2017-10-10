package com.yusong.club.ui.express.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.express.adapter.OpenBoxListAdapter;
import com.yusong.club.ui.express.mvp.entity.OpenBoxOrder;
import com.yusong.club.ui.express.mvp.entity.Order;
import com.yusong.club.ui.express.mvp.implView.IOpenBoxListView;
import com.yusong.club.ui.express.mvp.presenter.impl.IOpenBoxListPresenterImpl;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * <ul>
 * <li>功能说明：开箱列表</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class OpenBoxListActivity extends BaseActivity implements IOpenBoxListView {

    @InjectView(R.id.ll_back)
    LinearLayout mLlBack;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.rl_list)
    RecyclerView mRlList;
    @InjectView(R.id.tv_empty)
    TextView mTvEmpty;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView mIvAdaptiveDownApi18;
    private OpenBoxOrder mOpenBoxOrder;
    private IOpenBoxListPresenterImpl mPresenter;
    private OpenBoxListAdapter mAdapter;
    private List<Order> mDeliverList;
    private List<Order> mSendOrderList;
    private List<Order> mStoreOrderList;

    @Override
    protected void initListener() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_express_openbox_list;
    }

    @Override
    public void initView() {
        mTvTitle.setText("开箱列表");
        mPresenter = new IOpenBoxListPresenterImpl(this, this);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        mOpenBoxOrder = intent.getParcelableExtra("orderList");//获取数据
        mDeliverList = mOpenBoxOrder.getDeliverOrderList();
        mSendOrderList = mOpenBoxOrder.getSendOrderList();
        mStoreOrderList = mOpenBoxOrder.getStoreOrderList();
        //判空
        mPresenter.isEmpty(mDeliverList, mSendOrderList, mStoreOrderList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }


    @OnClick(R.id.ll_back)
    public void onClick() {
        finish();
    }

    /**
     * 设置适配器
     *
     * @param all
     */
    @Override
    public void setOrderAdapter(List<Order> all) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mAdapter = new OpenBoxListAdapter(all, this);
        mRlList.setLayoutManager(layoutManager);
        mRlList.setAdapter(mAdapter);
        mAdapter.setOnOpenBoxClickListener(new OpenBoxListAdapter.OnOpenBoxClickListener() {
            @Override
            public void onBtnClick(Order order) {
                mPresenter.openBox(mDeliverList, mSendOrderList, mStoreOrderList, order);
            }
        });

    }

    @Override
    public void OrderEmpty() {
        mTvEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(this);
    }
}
