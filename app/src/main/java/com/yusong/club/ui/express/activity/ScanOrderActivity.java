package com.yusong.club.ui.express.activity;

import android.content.Intent;
import android.os.Vibrator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.express.mvp.entity.ScanOrder;
import com.yusong.club.ui.express.mvp.implView.IMailQueryView;
import com.yusong.club.ui.express.mvp.presenter.impl.IMailQueryPresenterIpml;
import com.yusong.club.utils.ActivityConstants;
import com.yusong.club.utils.LogUtils;

import butterknife.InjectView;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.QRCodeView;

/**
 * <ul>
 * <li>功能说明：扫码查询快件界面</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class ScanOrderActivity extends BaseActivity
        implements QRCodeView.Delegate, IMailQueryView {

    @InjectView(R.id.ll_back)
    LinearLayout mLlBack;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.zbarview)
    QRCodeView mQRCodeView;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView mIvAdaptiveDownApi18;
    private long mLong;
    private IMailQueryPresenterIpml mPresenter;

    @Override
    protected void initListener() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_express_scan_order;
    }

    @Override
    public void initView() {
        mPresenter = new IMailQueryPresenterIpml(this, this);
        mQRCodeView.setDelegate(this);
        mTvTitle.setText("扫码查询");

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        mQRCodeView.startCamera();
        mQRCodeView.showScanRect();
        mQRCodeView.startSpot();
    }


    @Override
    protected void onStop() {
        mQRCodeView.stopCamera();
        super.onStop();
    }


    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String order_id) {
        vibrate();
        mQRCodeView.startSpot();
        mLong = Long.parseLong(order_id);
        mPresenter.scanOrder(order_id);

    }


    @Override
    public void onScanQRCodeOpenCameraError() {
        LogUtils.e("打开相机出错");
    }

    @OnClick(R.id.ll_back)
    public void onClick() {
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mQRCodeView.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void jumpActivity(ScanOrder.ShipperInfo order) {
        Intent mIntent = new Intent(ScanOrderActivity.this, LogisticsInfoActivity.class);
        mIntent.putExtra(ActivityConstants.ORDER_ID, mLong);
        mIntent.putExtra(ActivityConstants.ShipperCode, order.getShipperCode());
        mIntent.putExtra(ActivityConstants.ShipperName, order.getShipperName());
        startActivity(mIntent);
        finish();
    }

    @Override
    public void jump() {
        MyApplication.closeProgressDialog();
        ActivityConstants.list_number.add(mLong);
        Intent mIntent = new Intent(ScanOrderActivity.this, SearchSuccessActivity.class);
        mIntent.putExtra(ActivityConstants.ORDER_ID, mLong);
        startActivity(mIntent);
        finish();
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(this);
    }
}
