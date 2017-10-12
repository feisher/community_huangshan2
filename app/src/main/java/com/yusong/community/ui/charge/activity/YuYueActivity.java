package com.yusong.community.ui.charge.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.map.LocationService;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.charge.adapter.YuYueAdapter;
import com.yusong.community.ui.charge.bean.NearbyBean;
import com.yusong.community.ui.charge.mvp.implView.IChargeYuYueView;
import com.yusong.community.ui.charge.mvp.presenter.impl.IChargeYuYuePresenterImpl;
import com.yusong.community.utils.ToastUtils;
import com.yusong.community.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.community.utils.bga.BGARefreshLayout;
import com.yusong.community.utils.bga.BGARefreshViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;


/**
 * Created by Mr_Peng on 2016/12/24.
 */
public class YuYueActivity
        extends BaseActivity implements IChargeYuYueView, BGARefreshLayout.BGARefreshLayoutDelegate {

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
    @InjectView(R.id.iv_img)
    ImageView ivImg;
    @InjectView(R.id.ll_img)
    LinearLayout llImg;
    @InjectView(R.id.tv_text)
    TextView tvText;
    @InjectView(R.id.rl_txt)
    RelativeLayout rlTxt;

    private double lng = 118.077546;
    private double lat = 30.277746;
    private LocationService mService;
    private String keyword = "";

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        if (LocationService.mLocation != null) {
            lng = LocationService.mLocation.getLongitude();
            lat = LocationService.mLocation.getLatitude();
        }
        yuYuePresenter.qureyFuJinChage((float) lng, (float) lat, type, findEt.getText().toString().trim());
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if (LocationService.mLocation != null) {
            lng = LocationService.mLocation.getLongitude();
            lat = LocationService.mLocation.getLatitude();
        }
        yuYuePresenter.qureyFuJinChage((float) lng, (float) lat, type, findEt.getText().toString().trim());
        return true;
    }

    private IChargeYuYuePresenterImpl yuYuePresenter;
    private YuYueAdapter yuYueAdapter;
    private List<NearbyBean> nearbyBeanList = new ArrayList<NearbyBean>();
    private String type = null;//标示 快/慢 冲

    @InjectView(R.id.yuyue_listview)
    ListView yuyueListview;
    @InjectView(R.id.map_view)
    MapView mapView;
    @InjectView(R.id.yuyue_refresh)
    BGARefreshLayout yuyueRefresh;
    //地图相关
    private BaiduMap mBaiduMap;
    BitmapDescriptor kongXianBm = BitmapDescriptorFactory.fromResource(R.mipmap.chongdian_keyong);
    BitmapDescriptor zhanManBm = BitmapDescriptorFactory.fromResource(R.mipmap.zhan_man);
    BitmapDescriptor guZhangBm = BitmapDescriptorFactory.fromResource(R.mipmap.guzhang);

    @OnClick(R.id.ll_back)
    void backClick() {
        this.finish();
    }

    @OnClick(R.id.ll_img)
    void toMyOrderClick() {
        startActivity(new Intent(this, ChargeMyOrderActivity.class));
    }

    @OnItemClick(R.id.yuyue_listview)
    void listViewClick(int postion) {
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(markerList.get(postion).getPosition());
        mBaiduMap.setMapStatus(mapStatusUpdate);
    }


    @Override
    public void initView() {
        mService = new LocationService(this);
        tvTitle.setVisibility(View.GONE);
        findLayout.setVisibility(View.VISIBLE);
        ivImg.setBackgroundResource(R.mipmap.wode);
        llImg.setVisibility(View.VISIBLE);
        type = getIntent().getStringExtra("type");
        yuYuePresenter = new IChargeYuYuePresenterImpl(this, YuYueActivity.this);
        yuYueAdapter = new YuYueAdapter(this, this, nearbyBeanList);
        yuyueListview.setAdapter(yuYueAdapter);
        findEt.addTextChangedListener(textWatcher);
        initRefreshLayout();
        if (type.equals("0")) {
            type = "";
        }
        if (LocationService.mLocation != null) {
            lng = LocationService.mLocation.getLongitude();
            lat = LocationService.mLocation.getLatitude();
        } else {
            ToastUtils.showShort(getApplicationContext(), "地图加载失败");
        }
        yuYuePresenter.qureyFuJinChage((float) lng, (float) lat, type, "");
    }

    @Override
    protected void onStart() {
        mBaiduMap = mapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mBaiduMap.setMyLocationEnabled(true); // 开启定位图层
        mBaiduMap.setIndoorEnable(true); // 开启室内图
        MyLocationData locData = new MyLocationData.Builder().accuracy(200).direction(100).latitude(lat).longitude(lng).build();
        mBaiduMap.setMyLocationData(locData);
        LatLng ll = new LatLng(lat, lng);
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(ll).zoom(17.0f);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                NearbyBean nearbyBean = (NearbyBean) marker.getExtraInfo().get("NearbyBean");
                int i = marker.getExtraInfo().getInt("position");
                yuyueListview.setSelection(i);
//                ToastUtils.showShort(getApplicationContext(), nearbyBean.getChargerName());
                Button btn = new Button(getApplicationContext());
                btn.setBackgroundResource(R.drawable.baidu);
                btn.setText(nearbyBean.getChargerName());
                btn.setTextColor(Color.parseColor("#999999"));
                mBaiduMap.showInfoWindow(new InfoWindow(btn, marker.getPosition(), -47));
                return false;
            }
        });
        super.onStart();
    }

    //搜素监听
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            yuYuePresenter.qureyFuJinChage((float) lng, (float) lat, type, charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    public void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_charge_yuyue;
    }

//    @Override
//    protected void onStop() {
//        super.onPause();
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        mService.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();

    }


    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
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

    private void initRefreshLayout() {
        //设置代理
        yuyueRefresh.setDelegate(this);
        //设置刷新风格
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(this, true);
        yuyueRefresh.setRefreshViewHolder(mBGARefreshViewHolder);
    }

    private List<Marker> markerList = new ArrayList<>();
    private Marker mMarker;

    @Override
    public void reshreView(List<NearbyBean> data) {
        yuyueRefresh.endRefreshing();
        yuyueRefresh.endLoadingMore();
        nearbyBeanList.clear();
        nearbyBeanList.addAll(data);
        yuYueAdapter.notifyDataSetChanged();
        if (markerList.size() > 0) {
            for (Marker marker : markerList) {
                marker.remove();
            }
            markerList.clear();
        }
        for (int i = 0; i < nearbyBeanList.size(); i++) {
            NearbyBean bean = nearbyBeanList.get(i);
            OverlayOptions option = null;
            LatLng point = new LatLng(bean.getLat(), bean.getLng());
            switch (bean.getStatus()) {
                case 1:
                    option = new MarkerOptions().position(point).icon(kongXianBm);
                    break;
                case 2:
                    option = new MarkerOptions().position(point).icon(zhanManBm);
                    break;
                case 3:
                    option = new MarkerOptions().position(point).icon(guZhangBm);
                    break;
                default:
                    break;
            }
            mMarker = (Marker) mBaiduMap.addOverlay(option);
            Bundle bundle = new Bundle();
            bundle.putSerializable("NearbyBean", bean);
            bundle.putInt("position", i);
            mMarker.setExtraInfo(bundle);
            markerList.add(mMarker);
        }
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(this);
    }
}

// mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(15));
//        double lat = LocationService.mLocation.getLatitude();
//        double lon = LocationService.mLocation.getLongitude();
//        LatLng point = new LatLng(lat, lon);
//        // 构建Marker图标
//        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.ico_map);
//        // 构建MarkerOption，用于在地图上添加Marker
//        OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);
//        // 在地图上添加Marker，并显示
//        mBaiduMap.addOverlay(option);
//        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(point));
