package com.yusong.community.ui.express.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.express.adapter.MailQueryAdapter;
import com.yusong.community.ui.express.mvp.entity.ScanOrder;
import com.yusong.community.ui.express.mvp.implView.IMailQueryView;
import com.yusong.community.ui.express.mvp.presenter.impl.IMailQueryPresenterIpml;
import com.yusong.community.utils.ActivityConstants;
import com.yusong.community.utils.ToastUtils;

import org.apache.commons.lang.StringUtils;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * <ul>
 * <li>功能说明：快件查询界面</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */
public class MailQueryActivity extends BaseActivity implements IMailQueryView {

    @InjectView(R.id.ll_back)
    LinearLayout mLlBack;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.et_query)
    EditText mEtQuery;
    @InjectView(R.id.btn_commit)
    Button mBtnCommit;
    @InjectView(R.id.rl_number)
    RecyclerView mRlNumber;
    @InjectView(R.id.ll_scan)
    LinearLayout mLlScan;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView mIvAdaptiveDownApi18;
    private MailQueryAdapter mAdapter;
    private LinearLayoutManager mMLinearLayoutManager;
    private IMailQueryPresenterIpml mPresenter;
    private long mLong;

    @Override
    protected void initListener() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_express_mailquery;
    }

    @Override
    public void initView() {
        mTvTitle.setText("快件查询");
        mPresenter = new IMailQueryPresenterIpml(this, this);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.closeProgressDialog();
        //设置历史输入记录
        if (ActivityConstants.list_number != null && ActivityConstants.list_number.size() > 0) {
            mMLinearLayoutManager = new LinearLayoutManager(this);
            mAdapter = new MailQueryAdapter(ActivityConstants.list_number,this);
            mRlNumber.setAdapter(mAdapter);
            mRlNumber.setLayoutManager(mMLinearLayoutManager);
        }
        if (mAdapter != null) {
            mAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, Object data, int position) {
                    long number = (long) data;
                    mEtQuery.setText(number + "");
                    mPresenter.scanOrder(number + "");
                }
            });
        }
    }

    @OnClick({R.id.ll_back, R.id.btn_commit, R.id.ll_scan})
    public void onClick(View view) {

        Intent mIntent = null;
        switch (view.getId()) {
            case R.id.ll_back:

                finish();
                break;
            case R.id.btn_commit:

                String number = mEtQuery.getText().toString().trim();

                if (StringUtils.isEmpty(number)) {
                    ToastUtils.showShort(this, "单号不能为空");
                    return;
                }
                if (number.length() <= 9) {
                    ToastUtils.showShort(this, "至少输入10位快递单号");
                    return;
                }
                mLong = Long.parseLong(number);
                if (!ActivityConstants.list_number.contains(mLong)) {
                    ActivityConstants.list_number.add(0,mLong);
                }
                mPresenter.scanOrder(number);

                break;
            case R.id.ll_scan:
                mIntent = new Intent(MailQueryActivity.this, ScanOrderActivity.class);
                startActivity(mIntent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }


    @Override
    public void jumpActivity(ScanOrder.ShipperInfo order) {
        Intent mIntent = new Intent(MailQueryActivity.this, LogisticsInfoActivity.class);
        mIntent.putExtra(ActivityConstants.ORDER_ID, mLong);
        mIntent.putExtra(ActivityConstants.ShipperCode, order.getShipperCode());
        mIntent.putExtra(ActivityConstants.ShipperName, order.getShipperName());
        startActivity(mIntent);
    }

    @Override
    public void jump() {
        MyApplication.closeProgressDialog();
        Intent mIntent = new Intent(MailQueryActivity.this, SearchSuccessActivity.class);
        mIntent.putExtra(ActivityConstants.ORDER_ID, mLong);
        startActivity(mIntent);
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(this);
    }
}
