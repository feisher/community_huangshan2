package com.yusong.community.ui.charge.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.charge.bean.NearbyBean;
import com.yusong.community.ui.charge.bean.SancContentBean;
import com.yusong.community.ui.charge.mvp.implView.IChargeQueryDetailsView;
import com.yusong.community.ui.charge.mvp.implView.IChargeScanView;
import com.yusong.community.ui.charge.mvp.presenter.impl.IChargeQueryDetailsPresenterImpl;
import com.yusong.community.ui.charge.mvp.presenter.impl.IChargeScanPresenterimpl;
import com.yusong.community.ui.home.mvp.cache.TokenInfo;
import com.yusong.community.utils.CacheUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zbar.ZBarView;

/**
 * Created by Mr_Peng on 2017/1/9.
 */

public class ScanChargeActivity extends BaseActivity implements QRCodeView.Delegate, IChargeScanView, IChargeQueryDetailsView {
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
    @InjectView(R.id.iv_img)
    ImageView ivImg;
    @InjectView(R.id.ll_img)
    LinearLayout llImg;
    @InjectView(R.id.tv_text)
    TextView tvText;
    @InjectView(R.id.rl_txt)
    RelativeLayout rlTxt;
    private IChargeScanPresenterimpl scanPresenterimpl;
    private IChargeQueryDetailsPresenterImpl queryDetailsPresenter;
    private String code = null;

    @Override
    public void onScanQRCodeSuccess(String result) {
        code = result;
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        //todo 添加集成hybrid充电桩
        if (result.contains("scan_qrcode/index.htm?qrcode")) {
            Intent intent = new Intent();
            intent.setClassName(getPackageName(),"com.yusong.chargersdk.ui.QRCodeActivity");
            intent.putExtra("qrcodeStr",result);
            TokenInfo tokenInfo = CacheUtils.getTokenInfo(getApplicationContext());
            if (tokenInfo!=null) {
                intent.putExtra("mobile", tokenInfo.getName());
                startActivity(intent);
            }
        }else {
            scanPresenterimpl.scanCharge(result);
        }
        vibrate();
        zbarview.startSpot();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }


    @InjectView(R.id.zbarview)
    ZBarView zbarview;

    @OnClick(R.id.ll_back)
    void backClick() {
        this.finish();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_charge_scan;
    }

    @Override
    public void initView() {
        tvTitle.setText("扫码充电");
        zbarview.setDelegate(this);
        scanPresenterimpl = new IChargeScanPresenterimpl(this, this);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @Override
    protected void adaptiveSystemVersions() {
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= 18) {
            ivAdaptiveDownApi18.setVisibility(View.VISIBLE);
        } else {
            ivAdaptiveDownApi18.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        zbarview.startCamera();
        zbarview.showScanRect();
        zbarview.startSpot();
    }


    @Override
    protected void onStop() {
        zbarview.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        zbarview.onDestroy();
        super.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void jumpActivity(List<SancContentBean> data) {

        Intent intent = new Intent(this, ScanStartChargeActivity.class);
        intent.putExtra("SancContentBean", new Gson().toJson(data));
        startActivity(intent);
        this.finish();

    }

    @Override
    public void queryNewCharge() {
//        queryDetailsPresenter = new IChargeQueryDetailsPresenterImpl(this, this);
//        queryDetailsPresenter.queryChargeDetalis(code);
        Intent intent = new Intent(this, YuYueDetailsActivity.class);
        intent.putExtra("orderType", 2);
        intent.putExtra("chargeId", code);
        startActivity(intent);
    }



    @Override
    public void QueryDetailsMessage(String message) {

    }

    @Override
    public void toChareDetails(List<NearbyBean> data) {
        Intent intent = new Intent(this, YuYueDetailsActivity.class);
        intent.putExtra("nearbyBean", data.get(0));
        startActivity(intent);
        this.finish();
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(this);
    }
}
