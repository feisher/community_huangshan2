package com.yusong.community.ui.charge.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.yusong.community.R;
import com.yusong.community.map.NavigationUtil;
import com.yusong.community.ui.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Mr_Peng on 2016/12/30.
 */

public class PaySuccesActivity extends BaseActivity {

    @InjectView(R.id.diaohang_layout)
    RelativeLayout diaohangLayout;
    @InjectView(R.id.daohang_mapview)
    MapView daohangMapview;
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

    @OnClick(R.id.ll_back)
    void backClick() {
        this.finish();
    }

    @OnClick(R.id.diaohang_layout)
    void daohang(){
        new NavigationUtil(this,lng,lat);
    }

    private double lng = 0.0;
    private double lat = 0.0;

    //地图相关
    private BaiduMap mBaiduMap;

    @Override
    protected void initListener() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_charge_pay_succed;
    }

    @Override
    public void initView() {
        tvTitle.setText("支付成功");
        Intent intent = getIntent();
        lng = intent.getDoubleExtra("lng", 0.0);
        lat = intent.getDoubleExtra("lat", 0.0);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onStart() {
        super.onStart();



        mBaiduMap = daohangMapview.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mBaiduMap.setMyLocationEnabled(true); // 开启定位图层
        mBaiduMap.setIndoorEnable(true); // 开启室内图
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(new LatLng(lat,lng));
        mBaiduMap.setMapStatus(mapStatusUpdate);


         mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(15));
        LatLng point = new LatLng(lat, lng);
        // 构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher);
        // 构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);
        // 在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(point));
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
}
