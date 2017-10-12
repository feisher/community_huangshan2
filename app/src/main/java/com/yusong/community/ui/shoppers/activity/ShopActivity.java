package com.yusong.community.ui.shoppers.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.charge.view.BaseDialog;
import com.yusong.community.ui.shoppers.adapter.DianpuAdapter;
import com.yusong.community.ui.shoppers.bean.CommodityBean;
import com.yusong.community.ui.shoppers.bean.FenLeiBean;
import com.yusong.community.ui.shoppers.bean.ShopDetailsBean;
import com.yusong.community.ui.shoppers.mvp.implView.ImplQueryDianpuShangpinView;
import com.yusong.community.ui.shoppers.mvp.implView.ImplQueryDianpufenleiView;
import com.yusong.community.ui.shoppers.mvp.presenter.impl.ImplQueryDianpuShangpinPresenterImpl;
import com.yusong.community.ui.shoppers.mvp.presenter.impl.ImplQueryDianpufenleipresenterImpl;
import com.yusong.community.ui.shoppers.view.BaseScrollView;
import com.yusong.community.utils.SpaceItemDecoration;
import com.yusong.community.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.community.utils.bga.BGARefreshLayout;
import com.yusong.community.utils.bga.BGARefreshViewHolder;
import com.yusong.community.utils.glide.GlideImgManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Mr_Peng
 *         created at 2017/3/6 9:55.
 *         店铺
 */

public class ShopActivity extends BaseActivity implements View.OnClickListener,
        BGARefreshLayout.BGARefreshLayoutDelegate, ImplQueryDianpufenleiView,
        ImplQueryDianpuShangpinView, BaseScrollView.ScrollViewListener {

    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.gv_shop)
    RecyclerView gvShop;
    @InjectView(R.id.rl_modulename_refresh)
    BGARefreshLayout rlModulenameRefresh;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.shop_banner)
    Banner shopBanner;
    @InjectView(R.id.shop_name_tv)
    TextView shopNameTv;
    @InjectView(R.id.office_time_tv)
    TextView officeTimeTv;
    @InjectView(R.id.call_shop_layout)
    RelativeLayout callShopLayout;
    @InjectView(R.id.shop_address_tv)
    TextView shopAddressTv;
    @InjectView(R.id.zonghe_tv)
    TextView zongheTv;
    @InjectView(R.id.xiaoliang_tv)
    TextView xiaoliangTv;
    @InjectView(R.id.xinpin_tv)
    TextView xinpinTv;
    @InjectView(R.id.jiage_tv)
    TextView jiageTv;
    @InjectView(R.id.zonghe_tv_2)
    TextView zongheTv2;
    @InjectView(R.id.xiaoliang_tv_2)
    TextView xiaoliangTv2;
    @InjectView(R.id.xinpin_tv_2)
    TextView xinpinTv2;
    @InjectView(R.id.jiage_tv_2)
    TextView jiageTv2;
    @InjectView(R.id.layout_height)
    LinearLayout layoutHeight;
    @InjectView(R.id.app_title_layout)
    LinearLayout appTitleLayout;
    @InjectView(R.id.base_scrollview)
    BaseScrollView baseScrollview;
    @InjectView(R.id.shop_title_2)
    LinearLayout shopTitle2;
    @InjectView(R.id.shop_title_1)
    LinearLayout shopTitle1;
    private List<FenLeiBean> beanList = new ArrayList<FenLeiBean>();
    private ImplQueryDianpufenleipresenterImpl queryDianpufenleipresenterImpl;//查询店铺分类
    private ImplQueryDianpuShangpinPresenterImpl dianpuShangpinPresenterImpl;//查询店铺商品
    private int shopId = -1;
    private int fenleiId = 0;
    private String orderBy = "orderNum";
    private DianpuAdapter adapter;
    private List<CommodityBean> list = new ArrayList<CommodityBean>();
    private boolean isClear = false;
    private TextView textView;//当前按钮
    private TextView previousTv;//记录上一个按钮
    private TextView previousTv2;
    private String tel = null;
    private boolean isUpOrDown = false;
    private boolean isSpinner1 = true;//屏蔽下拉初始化触发请求
    private boolean isSpinner2 = true;
    private int height;//总高度  用来判断滑动高度好用来实现悬浮效果

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {//测量高度
        super.onWindowFocusChanged(hasFocus);
        height = layoutHeight.getMeasuredHeight();
    }

    @Override
    public void onScrollChanged(int x, int y, int oldx, int oldy) {
        if (y > height) {
            shopTitle1.setVisibility(View.INVISIBLE);
            shopTitle2.setVisibility(View.VISIBLE);
        } else {
            shopTitle1.setVisibility(View.VISIBLE);
            shopTitle2.setVisibility(View.GONE);
        }
    }

    @Override
    public void shangpinSucced(List<CommodityBean> data) {//店铺商品
        if (isClear) {
            if (list.size() > 0) {
                list.clear();
            }
            isClear = !isClear;
        }
        list.addAll(data);
        adapter.notifyDataSetChanged();
        rlModulenameRefresh.endLoadingMore();
        rlModulenameRefresh.endRefreshing();
    }

    @Override
    public void dianpuFenleiSucced(List<FenLeiBean> data) {//店铺商品分类

    }

    @Override
    public void dianpuDetailsSucced(ShopDetailsBean bean) {//店铺信息获取成功
        tel = bean.getTel();
        shopBanner.setImageLoader(new GlideImgManager());
        shopBanner.setImages(bean.getImageList());
        shopBanner.start();
        shopNameTv.setText(bean.getShopName());
        officeTimeTv.setText("营业时间:" + bean.getOfficeTime());
        shopAddressTv.setText(bean.getShopLocation());
    }


    @Override
    protected void initListener() {
        llBack.setOnClickListener(this);
    }


    @Override
    protected int layoutId() {
        return R.layout.activity_shop_shop;
    }

    @Override
    public void initView() {
        shopId = getIntent().getIntExtra("shopId", -1);
        tvTitle.setText(getIntent().getStringExtra("shopName"));
        previousTv = zongheTv;
        previousTv2 = zongheTv2;
        zongheTv.setOnClickListener(this);
        xiaoliangTv.setOnClickListener(this);
        xinpinTv.setOnClickListener(this);
        jiageTv.setOnClickListener(this);

        zongheTv2.setOnClickListener(this);
        xiaoliangTv2.setOnClickListener(this);
        xinpinTv2.setOnClickListener(this);
        jiageTv2.setOnClickListener(this);

        callShopLayout.setOnClickListener(this);
        baseScrollview.setScrollViewListener(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        gvShop.setLayoutManager(layoutManager);
        adapter = new DianpuAdapter(list, this);
        gvShop.setAdapter(adapter);
        gvShop.addItemDecoration(new SpaceItemDecoration(10, 10));
        rlModulenameRefresh.setGridLayoutManager(list, layoutManager);

        adapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                Intent intent = new Intent(ShopActivity.this, CommodityDetailsActivity.class);
                intent.putExtra("itemId", list.get(position).getId());
                intent.putExtra("isQG", 1);
                startActivity(intent);
            }
        });
        queryDianpufenleipresenterImpl = new ImplQueryDianpufenleipresenterImpl(this, ShopActivity.this);
        //     queryDianpufenleipresenterImpl.queryDianpufenlei(shopId);
        queryDianpufenleipresenterImpl.queryDianpuDetails(shopId);

        dianpuShangpinPresenterImpl = new ImplQueryDianpuShangpinPresenterImpl(this, ShopActivity.this);
        dianpuShangpinPresenterImpl.queryDianPuShangpin(fenleiId, shopId, orderBy, 0, 15);
    }

    @Override
    public void initData() {
        //初始化上拉刷新 下拉加载
        initRefreshLayout();
    }

    private void initRefreshLayout() {
        //设置代理
        rlModulenameRefresh.setDelegate(this);
        //设置刷新风格
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(this, true);
        rlModulenameRefresh.setRefreshViewHolder(mBGARefreshViewHolder);
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
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.ll_back) {
            finish();
            return;
        } else if (viewId == R.id.call_shop_layout) {//拨打电话
            final BaseDialog dialog = new BaseDialog(this);
            super.phoneNumber = tel;
            dialog.setTitle("联系商机");
            dialog.setMessage("商家电话:" + tel + " 是否联系他？");
            dialog.setNegativeButton("取消", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.setPositiveButton("确定", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    callDirectly(tel);

                }
            });
        } else {
            previousTv.setTextColor(Color.parseColor("#5C5C5C"));
            previousTv2.setTextColor(Color.parseColor("#5C5C5C"));
            textView = (TextView) v;
            textView.setTextColor(Color.parseColor("#FF2E2E"));
            Drawable drawable = getResources().getDrawable(R.mipmap.jiantou_moren);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            jiageTv.setCompoundDrawables(null, null, drawable, null);
            jiageTv2.setCompoundDrawables(null, null, drawable, null);
            previousTv = (TextView) v;
            isClear = true;
            switch (viewId) {
                case R.id.zonghe_tv:
                    previousTv2 = zongheTv2;
                    zongheTv2.setTextColor(Color.parseColor("#FF2E2E"));
                    orderBy = "orderNum";
                    dianpuShangpinPresenterImpl.queryDianPuShangpin(fenleiId, shopId, orderBy, 0, 15);
                    break;
                case R.id.zonghe_tv_2:
                    previousTv2 = zongheTv;
                    zongheTv.setTextColor(Color.parseColor("#FF2E2E"));
                    orderBy = "orderNum";
                    dianpuShangpinPresenterImpl.queryDianPuShangpin(fenleiId, shopId, orderBy, 0, 15);
                    break;
                case R.id.xiaoliang_tv:
                    previousTv2 = xiaoliangTv2;
                    xiaoliangTv2.setTextColor(Color.parseColor("#FF2E2E"));
                    orderBy = "soldAmount";
                    dianpuShangpinPresenterImpl.queryDianPuShangpin(fenleiId, shopId, orderBy, 0, 15);
                    break;
                case R.id.xiaoliang_tv_2:
                    previousTv2 = xiaoliangTv;
                    xiaoliangTv.setTextColor(Color.parseColor("#FF2E2E"));
                    orderBy = "soldAmount";
                    dianpuShangpinPresenterImpl.queryDianPuShangpin(fenleiId, shopId, orderBy, 0, 15);
                    break;
                case R.id.xinpin_tv:
                    previousTv2 = xinpinTv2;
                    xinpinTv2.setTextColor(Color.parseColor("#FF2E2E"));
                    orderBy = "id";
                    dianpuShangpinPresenterImpl.queryDianPuShangpin(fenleiId, shopId, orderBy, 0, 15);
                    break;
                case R.id.xinpin_tv_2:
                    previousTv2 = xinpinTv;
                    xinpinTv.setTextColor(Color.parseColor("#FF2E2E"));
                    orderBy = "id";
                    dianpuShangpinPresenterImpl.queryDianPuShangpin(fenleiId, shopId, orderBy, 0, 15);
                    break;
                case R.id.jiage_tv:
                    Drawable nav_up;
                    previousTv2 = jiageTv2;
                    jiageTv2.setTextColor(Color.parseColor("#FF2E2E"));
                    if (isUpOrDown) {//upToDown 价格按从高到低  downToUp价格按从低到高
                        orderBy = "upToDown";
                        nav_up = getResources().getDrawable(R.mipmap.jiantou_gao);
                        isUpOrDown = !isUpOrDown;
                    } else {
                        orderBy = "downToUp";
                        nav_up = getResources().getDrawable(R.mipmap.jiantou_di);
                        isUpOrDown = !isUpOrDown;
                    }
                    nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
                    jiageTv.setCompoundDrawables(null, null, nav_up, null);
                    jiageTv2.setCompoundDrawables(null, null, nav_up, null);
                    dianpuShangpinPresenterImpl.queryDianPuShangpin(fenleiId, shopId, orderBy, 0, 15);
                    break;
                case R.id.jiage_tv_2:
                    Drawable nav_up2;
                    previousTv2 = jiageTv;
                    jiageTv.setTextColor(Color.parseColor("#FF2E2E"));
                    if (isUpOrDown) {//upToDown 价格按从高到低  downToUp价格按从低到高
                        orderBy = "upToDown";
                        nav_up2 = getResources().getDrawable(R.mipmap.jiantou_gao);
                        isUpOrDown = !isUpOrDown;
                    } else {
                        orderBy = "downToUp";
                        nav_up2 = getResources().getDrawable(R.mipmap.jiantou_di);
                        isUpOrDown = !isUpOrDown;
                    }
                    nav_up2.setBounds(0, 0, nav_up2.getMinimumWidth(), nav_up2.getMinimumHeight());
                    jiageTv.setCompoundDrawables(null, null, nav_up2, null);
                    jiageTv2.setCompoundDrawables(null, null, nav_up2, null);
                    dianpuShangpinPresenterImpl.queryDianPuShangpin(fenleiId, shopId, orderBy, 0, 15);
                    break;
                default:
                    break;
            }
        }
    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(final BGARefreshLayout refreshayout) {
        isClear = true;
        dianpuShangpinPresenterImpl.queryDianPuShangpin(fenleiId, shopId, orderBy, 0, 15);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(final BGARefreshLayout refreshLayout) {
        dianpuShangpinPresenterImpl.queryDianPuShangpin(fenleiId, shopId, orderBy, list.size(), 15);
        return true;
    }


    @Override
    public void showProgressDialog() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        shopBanner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        shopBanner.stopAutoPlay();
    }

    @Override
    protected void onDestroy() {
        dianpuShangpinPresenterImpl.onDestroy();
        queryDianpufenleipresenterImpl.onDestroy();
        super.onDestroy();
    }

//    @Override
//    public void dianpuFenleiSucced(List<FenLeiBean> data) {//店铺商品分类
//        if (beanList.size() > 0) {
//            beanList.clear();
//        }
//        beanList.addAll(data);
//        final List<String> spinnerList = new ArrayList<String>();
//        for (FenLeiBean bean : beanList) {
//            spinnerList.add(bean.getCategoryName());
//        }
//        if (beanList.size() > 0) {
//            fenleiId = beanList.get(0).getId();
//            dianpuShangpinPresenterImpl.queryDianPuShangpin(fenleiId, shopId, orderBy, 0, 15);
//        } else {
//            spinnerList.add("暂无分类");
//        }
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerList);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        selectFenlei.setAdapter(arrayAdapter);
//        selectFenlei.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if (isSpinner2) {
//                    isSpinner2 = false;
//                } else {
//                    if (beanList.size() > 0) {
//                        fenleiId = beanList.get(i).getId();
//                        dianpuShangpinPresenterImpl.queryDianPuShangpin(fenleiId, shopId, orderBy, 0, 15);
//                        isClear = true;
//                    }
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//    }
}
