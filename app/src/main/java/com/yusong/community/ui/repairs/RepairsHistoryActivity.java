package com.yusong.community.ui.repairs;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.repairs.mvp.ImplView.RepairsHistoryView;
import com.yusong.community.ui.repairs.mvp.persenter.impl.RepairsHistoryPresenterImpl;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.SpaceItemDecoration;
import com.yusong.community.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.community.utils.bga.BGARefreshLayout;
import com.yusong.community.utils.bga.BGARefreshViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 * @created at 2017-09-01.
 * @describe: 申报历史
 */

public class RepairsHistoryActivity extends BaseActivity implements RepairsHistoryView, BGARefreshLayout.BGARefreshLayoutDelegate {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.find_et)
    EditText findEt;
    @InjectView(R.id.find_layout)
    RelativeLayout findLayout;
    @InjectView(R.id.left_radio)
    RadioButton leftRadio;
    @InjectView(R.id.center_radio)
    RadioButton centerRadio;
    @InjectView(R.id.right_radio)
    RadioButton rightRadio;
    @InjectView(R.id.radio_group)
    RadioGroup radioGroup;
    @InjectView(R.id.left_radio_address)
    RadioButton leftRadioAddress;
    @InjectView(R.id.right_radio_address)
    RadioButton rightRadioAddress;
    @InjectView(R.id.radio_group_address)
    RadioGroup radioGroupAddress;
    @InjectView(R.id.iv_img)
    ImageView ivImg;
    @InjectView(R.id.ll_img)
    LinearLayout llImg;
    @InjectView(R.id.tv_text)
    TextView tvText;
    @InjectView(R.id.rl_txt)
    RelativeLayout rlTxt;
    @InjectView(R.id.app_title_layout)
    LinearLayout appTitleLayout;
    @InjectView(R.id.history_recycler_view)
    RecyclerView historyRecyclerView;
    @InjectView(R.id.history_refresh_layout)
    BGARefreshLayout historyRefreshLayout;

    private RepairsHistoryPresenterImpl presenter;
    private boolean isClear = true;
    private List<RepairsHistoryBean> list = new ArrayList<RepairsHistoryBean>();
    private RepairsHistoryAdapter adapter;

    @OnClick(R.id.ll_back)
    void back() {
        this.finish();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_repairs_history;
    }

    @Override
    public void initView() {
        tvTitle.setText("历史记录");
        adapter = new RepairsHistoryAdapter(list, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        historyRecyclerView.setAdapter(adapter);
        historyRecyclerView.addItemDecoration(new SpaceItemDecoration(10, 0));
        historyRecyclerView.setLayoutManager(linearLayoutManager);
        historyRefreshLayout.setLinearLayoutManager(list, linearLayoutManager);
        initRefreshLayout();//初始化下拉刷新
    }

    @Override
    public void initData() {
        presenter = new RepairsHistoryPresenterImpl(this, this);
        presenter.queryRepairsHistory(CacheUtils.getProprietorId(this), 0, 10);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void querySucces(List<RepairsHistoryBean> list) {
        if (isClear) {
            if (this.list.size() > 0) {
                list.clear();
            }
            this.list.addAll(list);
        } else {
            this.list.addAll(list);
        }
        adapter.notifyDataSetChanged();
        historyRefreshLayout.endRefreshing();
        historyRefreshLayout.endLoadingMore();
    }

    @Override
    public void queryError() {
        historyRefreshLayout.endRefreshing();
        historyRefreshLayout.endLoadingMore();
    }

    private void initRefreshLayout() {
        //设置代理
        historyRefreshLayout.setDelegate(this);
        //设置刷新风格
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(this, true);
        historyRefreshLayout.setRefreshViewHolder(mBGARefreshViewHolder);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        isClear = true;
        presenter.queryRepairsHistory(CacheUtils.getProprietorId(this), 0, 10);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        isClear = false;
        presenter.queryRepairsHistory(CacheUtils.getProprietorId(this), list.size(), 10);
        return true;
    }
}
