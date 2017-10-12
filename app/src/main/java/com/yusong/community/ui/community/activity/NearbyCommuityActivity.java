package com.yusong.community.ui.community.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.map.LocationService;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.charge.view.BaseDialog;
import com.yusong.community.ui.community.adapter.NearbyCommuitysAdapter;
import com.yusong.community.ui.community.mvp.entity.Community;
import com.yusong.community.ui.community.mvp.entity.SetCommunity;
import com.yusong.community.ui.community.mvp.implView.INearbyCommuinityActivityView;
import com.yusong.community.ui.community.mvp.presenter.impl.NearbyActivityPresenterIpml;
import com.yusong.community.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.community.utils.bga.BGARefreshLayout;
import com.yusong.community.utils.bga.BGARefreshViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

import static com.yusong.community.MyApplication.showMessage;

/**
 * create by feisher on 2017/3/2 14:11
 * Email：458079442@qq.com
 */
public class NearbyCommuityActivity extends BaseActivity implements INearbyCommuinityActivityView, BGARefreshLayout.BGARefreshLayoutDelegate {

    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_text)
    TextView tvText;
    @InjectView(R.id.rl_txt)
    RelativeLayout rlTxt;
    @InjectView(R.id.rv_nearby_commuitys)
    RecyclerView rvNearbyCommuitys;
    @InjectView(R.id.bgar_layout)
    BGARefreshLayout bgarLayout;
    public NearbyActivityPresenterIpml mPresenter;
    double lng;
    double lat;
    int cityCode;
    public NearbyCommuitysAdapter mAdapter;
    public LinearLayoutManager mLinearLayoutManager;
    List<Community> mCommunityListDatas = new ArrayList<>();
    public boolean isGetLocation = false;
    public int areaId = 0;
    public int SELECT_CITY = 365;
    @InjectView(R.id.tv_nearby_title)
    TextView tvNearbyTitle;
    public String city;
    public boolean isReFresh = false;

    @Override
    protected int layoutId() {
        return R.layout.activity_nearby_commuity;
    }

    @Override
    public void initView() {
        initRefreshLayout();
    }

    @Override
    public void initData() {
        tvTitle.setText("选择小区");
        llBack.setVisibility(View.GONE);
        rlTxt.setVisibility(View.VISIBLE);
        Drawable drawable = getResources().getDrawable(R.mipmap.icon_arrow_down);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tvText.setCompoundDrawables(null, null, drawable, null);
        mPresenter = new NearbyActivityPresenterIpml(this, this);
        queryNearbyCommunity();

    }

    private void queryNearbyCommunity() {
        if (LocationService.mLocation != null) {
            lng = LocationService.mLocation.getLongitude();
            lat = LocationService.mLocation.getLatitude();
            city = LocationService.mLocation.getCity();
            if (!TextUtils.isEmpty(city)) {
                if (isReFresh ==false) {
                    tvText.setText(city);
                }
            }
            if (!TextUtils.isEmpty(LocationService.mLocation.getCityCode())) {
                cityCode = Integer.parseInt(LocationService.mLocation.getCityCode());
                if (areaId != 0) {
                    mPresenter.queryNearbyCommuity(areaId, 0, lat, lng);
                } else {
                    mPresenter.queryNearbyCommuity(0, cityCode, lat, lng);
                }
                isGetLocation = true;
            } else {
                isGetLocation = false;
                tvText.setText("选择城市");
                bgarLayout.endRefreshing();
                showMessage("定位失败");
            }
        } else {
            isGetLocation = false;
            bgarLayout.endRefreshing();
            tvText.setText("选择城市");
            showMessage("定位失败");
        }
    }

    @OnClick({R.id.ll_back, R.id.rl_txt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
//                finish();
                break;
            case R.id.rl_txt:
                Intent intent = new Intent(this, HaveCommunityCityActivity.class);
                if (!TextUtils.isEmpty(city)) {
                    intent.putExtra("CITYNAME",city);
                }else {
                    intent.putExtra("CITYNAME","定位失败！");
                }
                intent.putExtra("AREAID",cityCode);
                startActivityForResult(intent, SELECT_CITY);
                break;
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        isReFresh = true;
        if (isGetLocation == true) {
            queryNearbyCommunity();
        } else {
            if (areaId != 0) {
                mPresenter.queryNearbyCommuity(areaId, 0, 0, 0);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (mCommunityListDatas.size()>0&&mCommunityListDatas.get(0) != null) {
            MyApplication.showMessage("默认选择第一个社区");
            int id = mCommunityListDatas.get(0).getId();
            mPresenter.setCurrentCommunity(id);
        } else {
            showMessage("没有获取到附近社区，请到城市列表选择城市");
            final BaseDialog baseDialog = new BaseDialog(this);
            baseDialog.setTitle("提示")
                    .setMessage("跳转城市选择页面？")
                    .setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(NearbyCommuityActivity.this, HaveCommunityCityActivity.class);
                            if (!TextUtils.isEmpty(city)) {
                                intent.putExtra("CITYNAME",city);
                            }else {
                                intent.putExtra("CITYNAME","定位失败！");
                            }
                            intent.putExtra("AREAID",cityCode);
                            startActivityForResult(intent, SELECT_CITY);
                            baseDialog.dismiss();
                        }
                    }).setNegativeButton("取消", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    baseDialog.dismiss();
                    finish();
                }
            });
//
        }
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    /**
     * 设置社区成功回调
     */
    @Override
    public void setCurrentCommunitySucceedCallback(SetCommunity mSetCommunity) {
        showMessage("社区设置成功！");
        Intent data = new Intent();
        data.putExtra("communityId", mSetCommunity.getCommunityId());
        data.putExtra("communityName", mSetCommunity.getCommunityName());
        setResult(RESULT_OK, data);
        finish();//设置成功关闭当前界面
    }

    @Override
    public void setCurrentCommunityFailCallback() {
        final BaseDialog baseDialog = new BaseDialog(this);
        baseDialog.setTitle("提示")
                .setMessage("设置失败了，不设置社区将导致无法正常浏览社区模块内容！")
                .setPositiveButton("退出设置", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        baseDialog.dismiss();
                    }
                }).setNegativeButton("刷新重试", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseDialog.dismiss();
            }
        });

    }

    @Override
    public void closeRefresh() {
        if (bgarLayout != null) {
            bgarLayout.endRefreshing();
        }
    }

    @Override
    public void setCommunitysAdapter(List<Community> mCommunityLists) {
        mCommunityListDatas.clear();
        mCommunityListDatas.addAll(mCommunityLists);
//        if (mAdapter == null) {
            mLinearLayoutManager = new LinearLayoutManager(this);
            mAdapter = new NearbyCommuitysAdapter(this, mCommunityLists);
            rvNearbyCommuitys.setAdapter(mAdapter);
            rvNearbyCommuitys.setLayoutManager(mLinearLayoutManager);
            bgarLayout.setLinearLayoutManager(mCommunityLists, mLinearLayoutManager);
//        } else {
//            mAdapter.notifyDataSetChanged();
//        }
        rvNearbyCommuitys.scrollToPosition(0);
        mAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                if (mCommunityListDatas.get(position) != null) {
                    int id = mCommunityListDatas.get(position).getId();
                    mPresenter.setCurrentCommunity(id);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_CITY && resultCode == RESULT_OK && data != null) {
            String cityName = data.getExtras().getString("cityName");
            areaId = data.getExtras().getInt("areaId");
            if (!TextUtils.isEmpty(cityName)) {
                tvText.setText(cityName);
                tvNearbyTitle.setText(cityName+"的社区");
            }
            if (isGetLocation == true) {
                if (areaId != 0) {
                    mPresenter.queryNearbyCommuity(areaId, 0, lat, lng);
                }else {
                    mPresenter.queryNearbyCommuity(0, cityCode, lat, lng);
                }
            } else {
                if (areaId != 0) {
                    mPresenter.queryNearbyCommuity(areaId, 0, 0, 0);
                }

            }
        }
    }

    @Override
    protected void initListener() {

    }

    /**
     * 初始化刷新布局
     */
    private void initRefreshLayout() {
        bgarLayout.setDelegate(this);//设置代理
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(this, false);
        bgarLayout.setRefreshViewHolder(mBGARefreshViewHolder);
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(this);
    }
}
