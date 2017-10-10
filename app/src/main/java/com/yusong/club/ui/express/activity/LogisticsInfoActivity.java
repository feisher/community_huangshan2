package com.yusong.club.ui.express.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.express.adapter.LogisticsInfoAdapter;
import com.yusong.club.ui.express.mvp.entity.OrderLogistics;
import com.yusong.club.ui.express.mvp.implView.ILogisticsInfoView;
import com.yusong.club.ui.express.mvp.presenter.impl.ILogisticsInfoPresenterImpl;
import com.yusong.club.utils.ActivityConstants;
import com.yusong.club.utils.AppUtils;

import org.apache.commons.lang.StringUtils;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * <ul>
 * <li>功能说明：物流详情界面</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */
public class LogisticsInfoActivity extends BaseActivity implements ILogisticsInfoView {

    @InjectView(R.id.ll_back)
    LinearLayout mLlBack;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.iv_icon)
    ImageView mIvIcon;
    @InjectView(R.id.tv_name)
    TextView mTvName;
    @InjectView(R.id.tv_number)
    TextView mTvNumber;
    @InjectView(R.id.rl_info)
    RecyclerView mRlInfo;
    @InjectView(R.id.tv_state)
    TextView mTvState;
    @InjectView(R.id.tv_wl)
    TextView mTvWl;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView mIvAdaptiveDownApi18;
    private String mShipperCode;
    private long mOrder_id;
    private String mShipperName;
    private ILogisticsInfoPresenterImpl mPresenter;
    private LogisticsInfoAdapter mAdapter;


    @Override
    public void errEmpty() {
        mTvWl.setText("暂无物流信息");
    }

    @Override
    public void showInfo(OrderLogistics logistics) {
        String code = logistics.getShipperCode();
        AppUtils.setIcon(mIvIcon, code);
        int state = Integer.parseInt(logistics.getState());
        if (!StringUtils.isEmpty(logistics.getReason()) || state == 0) {
            mTvWl.setText("暂无物流信息");
        } else {
            mTvWl.setText("物流跟踪信息");
        }

        if (state == 4) {
            mTvState.setText("问题件");
        } else if (state == 2) {
            mTvState.setText("派送中");
        } else if (state == 3) {
            mTvState.setText("已签收");
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mAdapter = new LogisticsInfoAdapter(logistics.getTraces(), this);
        mRlInfo.setLayoutManager(layoutManager);
        mRlInfo.setAdapter(mAdapter);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_express_logisticsinfo;
    }


    @Override
    public void initView() {
        mPresenter = new ILogisticsInfoPresenterImpl(this, this);
        mTvTitle.setText("物流详情");
        Intent intent = getIntent();
        mShipperCode = intent.getStringExtra(ActivityConstants.ShipperCode);
        mShipperName = intent.getStringExtra(ActivityConstants.ShipperName);
        mOrder_id = intent.getLongExtra(ActivityConstants.ORDER_ID, 1);
        mTvName.setText(mShipperName);
        mTvNumber.setText(mOrder_id + "");
    }

    @Override
    public void initData() {
        //查询详细物流
        mPresenter.queryDetailed(mOrder_id, mShipperCode);
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
    protected void initListener() {

    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(this);
    }
}
