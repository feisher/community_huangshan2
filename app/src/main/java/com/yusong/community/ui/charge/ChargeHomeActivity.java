package com.yusong.community.ui.charge;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.youth.banner.Banner;
import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.charge.activity.ChargeMyOrderActivity;
import com.yusong.community.ui.charge.activity.HelpActivity;
import com.yusong.community.ui.charge.activity.MoveSosActivity;
import com.yusong.community.ui.charge.activity.ScanChargeActivity;
import com.yusong.community.ui.charge.activity.YuYueActivity;
import com.yusong.community.ui.charge.adapter.ChargeHomeAdapter;
import com.yusong.community.ui.charge.mvp.implView.IChargeHomeView;
import com.yusong.community.ui.charge.mvp.presenter.impl.IChareHomePresenterImpl;
import com.yusong.community.ui.home.adapter.HomeItemsAdapter;
import com.yusong.community.utils.PermissionUtil;
import com.yusong.community.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.community.utils.bga.BGARefreshLayout;
import com.yusong.community.utils.bga.BGARefreshViewHolder;
import com.yusong.community.utils.glide.GlideImgManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.yusong.community.utils.PermissionUtil.RESULT_CAPTURE_IMAGE;

/**
 * Created by Mr_Peng on 2016/12/23.
 * 充电桩首页
 */
public class ChargeHomeActivity extends BaseActivity implements IChargeHomeView, BGARefreshLayout.BGARefreshLayoutDelegate {
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

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        homePresenter.requestChargeHomeBanner();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    int[] items_img = {
            R.mipmap.yuyuechogndian, R.mipmap.chengshikuaichong, R.mipmap.xiaoqumanchong,
            R.mipmap.yidongsos
    };
    int[] items_name = {
            R.string.appoint_charging_text, R.string.city_fast_charging_text, R.string.plot_slow_charging_text
            , R.string.move_sos_text
    };
    private ChargeHomeAdapter homeAdapter;
    List<Integer> list = new ArrayList<>();

    @InjectView(R.id.banner_home)
    Banner bannerHome;
    @InjectView(R.id.charge_gridview)
    RecyclerView chargeGridview;
    @InjectView(R.id.help_layout)
    LinearLayout helpLayout;
    @InjectView(R.id.scan_layout)
    LinearLayout scanLayout;
    @InjectView(R.id.my_order_layout)
    LinearLayout myOrderTv;
    @InjectView(R.id.charge_home_refresh)
    BGARefreshLayout chargeHomeRefresh;

    private IChareHomePresenterImpl homePresenter;


    @OnClick(R.id.ll_back)
    void allClick() {
        this.finish();
    }

    @OnClick(R.id.help_layout)
    void onHelpClick() {
        startActivity(new Intent(this, HelpActivity.class));
    }

    @OnClick(R.id.scan_layout)
    void onScanClick() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            PermissionUtil.getCameraAndPhotoPermission(this, RESULT_CAPTURE_IMAGE);
        } else {
            startActivity(new Intent(this, ScanChargeActivity.class));
        }
    }

    @OnClick(R.id.my_order_layout)
    void onMyOrderClick() {
        startActivity(new Intent(this, ChargeMyOrderActivity.class));
    }

    @Override
    protected void initListener() {
        homeAdapter.setOnItemClickListener(new HomeItemsAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(ChargeHomeActivity.this, YuYueActivity.class);
                        intent.putExtra("type", "0");
                        break;
                    case 1:
                        intent = new Intent(ChargeHomeActivity.this, YuYueActivity.class);
                        intent.putExtra("type", "1");
                        break;
                    case 2:
                        intent = new Intent(ChargeHomeActivity.this, YuYueActivity.class);
                        intent.putExtra("type", "2");
                        break;
                    case 3:
                        intent = new Intent(ChargeHomeActivity.this, MoveSosActivity.class);
                        break;
                    default:
                        break;
                }
                if (intent == null) return;
                startActivity(intent);
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_charge_home;
    }

    @Override
    public void initView() {
        tvTitle.setText("充电桩");
        list.add(R.mipmap.charge_image_1);
        list.add(R.mipmap.charge_image_2);
        bannerHome.setImageLoader(new GlideImgManager());
        bannerHome.setImages(list);
        bannerHome.start();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ChargeHomeActivity.this, 4) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        homeAdapter = new ChargeHomeAdapter(ChargeHomeActivity.this, items_img, items_name);
        chargeGridview.setAdapter(homeAdapter);
        chargeGridview.setLayoutManager(gridLayoutManager);
        homePresenter = new IChareHomePresenterImpl(this, ChargeHomeActivity.this);
        initRefreshLayout();
    }
    @Override
    public void initData() {
        homePresenter.requestChargeHomeBanner();
    }



    @Override
    protected void onStart() {
        bannerHome.startAutoPlay();
        super.onStart();
    }

    @Override
    protected void onStop() {
        bannerHome.stopAutoPlay();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    public void refreshView(List<String> data) {
        bannerHome.update(data);
        chargeHomeRefresh.endRefreshing();
        chargeHomeRefresh.endLoadingMore();
    }

    @Override
    public void closeRefresh() {
        chargeHomeRefresh.endRefreshing();
        chargeHomeRefresh.endLoadingMore();
    }

    private void initRefreshLayout() {
        //设置代理
        chargeHomeRefresh.setDelegate(this);
        //设置刷新风格
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(this, true);
        chargeHomeRefresh.setRefreshViewHolder(mBGARefreshViewHolder);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        homePresenter.onDestroy();

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        AndPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults, listener);
    }

    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode) {
            if (requestCode == 100) {
                startActivity(new Intent(ChargeHomeActivity.this, ScanChargeActivity.class));
            }
        }

        @Override
        public void onFailed(int requestCode) {
        }
    };

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(this);
    }
}
