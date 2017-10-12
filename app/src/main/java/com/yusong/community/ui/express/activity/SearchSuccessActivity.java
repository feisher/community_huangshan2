package com.yusong.community.ui.express.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.express.adapter.SearchSuccessAdapter;
import com.yusong.community.ui.express.mvp.entity.ScanOrder;
import com.yusong.community.ui.express.mvp.implView.ISearchSuccessView;
import com.yusong.community.ui.express.mvp.presenter.impl.ISearchSuccessPresenterImpl;
import com.yusong.community.utils.ActivityConstants;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * <ul>
 * <li>功能说明：查询结果界面。非物流信息</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */
public class SearchSuccessActivity extends BaseActivity implements ISearchSuccessView {
    @InjectView(R.id.ll_back)
    LinearLayout mLlBack;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.tv_number)
    TextView mTvNumber;
    @InjectView(R.id.tv_result)
    TextView mTvResult;
    @InjectView(R.id.rl_searchinfo)
    RecyclerView mRlSearchinfo;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView mIvAdaptiveDownApi18;
    private long mNumber;
    private ISearchSuccessPresenterImpl mPresenter;
    private LinearLayoutManager mLayoutManager;
    private List<ScanOrder.ShipperInfo> mShippers;
    private SearchSuccessAdapter mAdapter;

    @Override
    protected void initListener() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_express_search_success;
    }

    @Override
    public void initView() {
        mTvTitle.setText("查询结果");
        mPresenter = new ISearchSuccessPresenterImpl(this, this);
        Intent intent = getIntent();
        mNumber = intent.getLongExtra(ActivityConstants.ORDER_ID, 1);
        mPresenter.scanOrder(mNumber);
    }

    @Override
    public void initData() {

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

    @Override
    public void errEmpty() {
        mTvNumber.setText("暂无物流信息");
        mTvResult.setVisibility(View.GONE);
        mRlSearchinfo.setVisibility(View.GONE);
    }

    @Override
    public void showInfo(ScanOrder order) {
        order.getShippers();
        mTvNumber.setText(mNumber + "");
        mTvResult.setText("查到以下结果，点击查看");
        mShippers = order.getShippers();
        mLayoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        if (mShippers != null && mShippers.size() > 0) {
            mAdapter = new SearchSuccessAdapter(mShippers,this);
            mRlSearchinfo.setAdapter(mAdapter);
            mRlSearchinfo.setLayoutManager(mLayoutManager);
            mAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, Object data, int position) {

                    ScanOrder.ShipperInfo info = (ScanOrder.ShipperInfo) data;
                    Intent mIntent = new Intent(SearchSuccessActivity.this, LogisticsInfoActivity.class);
                    mIntent.putExtra(ActivityConstants.ORDER_ID, mNumber);
                    mIntent.putExtra(ActivityConstants.ShipperCode, info.getShipperCode());
                    mIntent.putExtra(ActivityConstants.ShipperName, info.getShipperName());
                    startActivity(mIntent);
                }
            });

            mAdapter.setOnShowInfoListener(new SearchSuccessAdapter.ShowInfoLisener() {
                @Override
                public void setOnShowInfoLisener(TextView tv_number) {
                    tv_number.setText(mNumber + "");
                }
            });
        }

    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(this);
    }
}
