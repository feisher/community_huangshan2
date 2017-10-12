package com.yusong.community.ui.express.activity;

import android.content.Intent;
import android.os.Vibrator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.api.HttpResult;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.express.mvp.entity.OpenBoxOrder;
import com.yusong.community.ui.express.mvp.implView.IScanOpenBoxView;
import com.yusong.community.ui.express.mvp.presenter.impl.IScanOpenBoxPresenterImpl;
import com.yusong.community.utils.LogUtils;

import butterknife.InjectView;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.QRCodeView;

/**
 * <ul>
 * <li>功能说明：扫码开箱界面</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class ScanOpenBoxActivity extends BaseActivity implements QRCodeView.Delegate, IScanOpenBoxView {
    @InjectView(R.id.ll_back)
    LinearLayout mLlBack;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.zbarview)
    QRCodeView mQRCodeView;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView mIvAdaptiveDownApi18;
    private IScanOpenBoxPresenterImpl mPresenter;

    @Override
    protected void initListener() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_express_scan;
    }

    @Override
    public void initView() {
        mQRCodeView.setDelegate(this);
        mPresenter = new IScanOpenBoxPresenterImpl(this, this);
        mTvTitle.setText("扫码开箱");
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
    public void onScanQRCodeSuccess(String result) {
        LogUtils.kdg_log("result:" + result);
        mPresenter.queryTerminalCode(result);
        vibrate();
        mQRCodeView.startSpot();
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
        mQRCodeView.onDestroy();
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void jumpActivity(HttpResult<OpenBoxOrder> result) {
        Intent mIntent = new Intent(this, OpenBoxListActivity.class);
        mIntent.putExtra("orderList", result.data);
        startActivity(mIntent);
        finish();
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(this);
    }
}
