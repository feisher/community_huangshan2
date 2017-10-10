package com.yusong.club.ui.express.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.map.LocationService;
import com.yusong.club.map.NavigationUtil;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.express.adapter.NearbyBoxAdapter;
import com.yusong.club.ui.express.event.Navigation;
import com.yusong.club.ui.express.mvp.entity.NearbyBox;
import com.yusong.club.ui.express.mvp.implView.INearbyCabinetView;
import com.yusong.club.ui.express.mvp.presenter.impl.INearbyCabinetPresenterImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * <ul>
 * <li>功能说明：附近柜子界面</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */
public class NearbyBoxActivity extends BaseActivity implements INearbyCabinetView {
    @InjectView(R.id.ll_back)
    LinearLayout mLlBack;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.map_view)
    MapView mapView;
    @InjectView(R.id.rl_kdg)
    RecyclerView mRlKdg;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView mIvAdaptiveDownApi18;
    INearbyCabinetPresenterImpl mPresenter;
    private double lng = 0;
    private double lat = 0;
    private BaiduMap mBaiduMap;
    private LocationService mService;
    private NearbyBoxAdapter mAdapter;
    BitmapDescriptor kongXianBm = BitmapDescriptorFactory.fromResource(R.mipmap.mark_box);
    BitmapDescriptor zhanManBm = BitmapDescriptorFactory.fromResource(R.mipmap.mark_box);
    BitmapDescriptor guZhangBm = BitmapDescriptorFactory.fromResource(R.mipmap.mark_box);

    private List<Marker> markerList = new ArrayList<>();
    private Marker mMarker;
    private LinearLayoutManager mLayoutManager;
    private List<NearbyBox> data;


    @OnClick(R.id.ll_back)
    public void onClick() {
        finish();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void naviGationEvent(Navigation navigation) {
        new NavigationUtil(this, navigation.getLng(), navigation.getLat());
    }


    @Override
    public void setAdapter(List<NearbyBox> data) {
        this.data = data;
        if (mAdapter == null) {
            mLayoutManager = new LinearLayoutManager(this);
            mAdapter = new NearbyBoxAdapter(data, this);
            mRlKdg.setAdapter(mAdapter);
            mRlKdg.setLayoutManager(mLayoutManager);
            mAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, Object data, int position) {
                    MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(markerList.get(position).getPosition());
                    mBaiduMap.setMapStatus(mapStatusUpdate);
                }
            });
        } else {
            this.data.clear();
            this.data.addAll(data);
            mAdapter.notifyDataSetChanged();
        }

        if (markerList.size() > 0) {
            for (Marker marker : markerList) {
                marker.remove();
            }
            markerList.clear();
        }
        for (int i = 0; i < data.size(); i++) {
            NearbyBox bean = data.get(i);
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
            bundle.putSerializable("NearbyBox", bean);
            bundle.putInt("position", i);
            mMarker.setExtraInfo(bundle);
            markerList.add(mMarker);
        }

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_express_nearby;
    }

    @Override
    public void initView() {
     //   new NavigationUtils(this);
        mService = new LocationService(this);
        EventBus.getDefault().register(this);
        mTvTitle.setText("附近快柜");
        mPresenter = new INearbyCabinetPresenterImpl(this, this);
    }

    @Override
    public void initData() {

        if (LocationService.mLocation != null) {
            lng = LocationService.mLocation.getLongitude();
            lat = LocationService.mLocation.getLatitude();
        }
        mPresenter.queryNearestBox((float) lng, (float) lat);
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
                NearbyBox nearbyBox = (NearbyBox) marker.getExtraInfo().get("NearbyBox");
                int i = marker.getExtraInfo().getInt("position");
                MoveToPosition(mLayoutManager, mRlKdg, i);
//                ToastUtils.showShort(NearbyBoxActivity.this, nearbyBox.getTerminalName());
                Button btn = new Button(getApplicationContext());
                btn.setBackgroundResource(R.drawable.baidu);
                btn.setText(nearbyBox.getTerminalName());
                btn.setTextColor(Color.parseColor("#999999"));
                mBaiduMap.showInfoWindow(new InfoWindow(btn, marker.getPosition(), -47));
                return false;
            }
        });
        super.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
        mService = new LocationService(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
        mService.stop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();//mapview的生命周期
        mPresenter.onDestroy();
        EventBus.getDefault().unregister(this);
    }



    @Override
    protected void adaptiveSystemVersions() {
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= 18) {
            mIvAdaptiveDownApi18.setVisibility(View.VISIBLE);
        } else {
            mIvAdaptiveDownApi18.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initListener() {

    }

    /**
     * RecyclerView 移动到当前位置，
     *
     * @param manager       设置RecyclerView对应的manager
     * @param mRecyclerView 当前的RecyclerView
     * @param n             要跳转的位置
     */
    public static void MoveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {
        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }

    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(this);
    }
}
