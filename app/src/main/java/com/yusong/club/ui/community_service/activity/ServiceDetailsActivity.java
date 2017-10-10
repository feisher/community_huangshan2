package com.yusong.club.ui.community_service.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.community_service.mvp.ImplView.ServiceDetailView;
import com.yusong.club.ui.community_service.mvp.presenter.ImplPresenter.ServiceDetailPresenterImpl;
import com.yusong.club.ui.shoppers.adapter.CommentAdapter;
import com.yusong.club.ui.shoppers.adapter.ShopPictureAdapter;
import com.yusong.club.ui.shoppers.bean.CommodityBean;
import com.yusong.club.ui.shoppers.bean.PinLunBean;
import com.yusong.club.utils.ActivityConstants;
import com.yusong.club.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.club.utils.bga.BGARefreshLayout;
import com.yusong.club.utils.bga.BGARefreshViewHolder;
import com.yusong.club.utils.glide.GlideImgManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Mr_Peng
 *         created at 2017/3/6 9:52.
 *         详情页面
 */

public class ServiceDetailsActivity extends BaseActivity implements View.OnClickListener,
        BGARefreshLayout.BGARefreshLayoutDelegate, ServiceDetailView {

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
    @InjectView(R.id.left_radio)
    RadioButton leftRadio;
    @InjectView(R.id.center_radio)
    RadioButton centerRadio;
    @InjectView(R.id.right_radio)
    RadioButton rightRadio;
    @InjectView(R.id.radio_group)
    RadioGroup radioGroup;
    @InjectView(R.id.left_radio_address)
    RadioButton leftRadioAddress;
    @InjectView(R.id.right_radio_address)
    RadioButton rightRadioAddress;
    @InjectView(R.id.radio_group_address)
    RadioGroup radioGroupAddress;
    @InjectView(R.id.iv_img)
    ImageView ivImg;
    @InjectView(R.id.ll_img)
    LinearLayout llImg;
    @InjectView(R.id.tv_text)
    TextView tvText;
    @InjectView(R.id.rl_txt)
    RelativeLayout rlTxt;
    @InjectView(R.id.app_title_layout)
    LinearLayout appTitleLayout;
    @InjectView(R.id.banner)
    Banner banner;
    @InjectView(R.id.commundity_name_tv)
    TextView commundityNameTv;
    @InjectView(R.id.linear_shop)
    LinearLayout linearShop;
    @InjectView(R.id.commodity_jishao_tv)
    TextView commodityJishaoTv;
    @InjectView(R.id.shop_picture_list)
    RecyclerView shopPictureList;
    @InjectView(R.id.pinlun_recyclerview)
    RecyclerView pinlunRecyclerview;
    @InjectView(R.id.refresh_layout)
    BGARefreshLayout refreshLayout;
    @InjectView(R.id.tv_price)
    TextView tvPrice;
    @InjectView(R.id.btn_buy)
    Button btnBuy;
    private List<String> imageStr = new ArrayList<String>();
    private List<PinLunBean> pinLunList = new ArrayList<PinLunBean>();
    private CommentAdapter pinLunAdapter;
    private ShopPictureAdapter pictureAdapter;
    private List<String> pictureList = new ArrayList<String>();
    private int itemId = -1;//商品详情
    private CommodityBean bean;
    private ServiceDetailPresenterImpl mPresenter;

    @Override
    public void showProgressDialog() {

    }

    @Override
    protected void initListener() {
        llBack.setOnClickListener(this);
        linearShop.setOnClickListener(this);
        btnBuy.setOnClickListener(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_service_detail;
    }

    @Override
    public void initView() {
        Intent intent;
        intent = getIntent();
        itemId = intent.getIntExtra("itemId", 0);
        tvTitle.setText("服务详情");
        imageStr.add("");
        banner.setImageLoader(new GlideImgManager());
        banner.setImages(imageStr);
        banner.start();
        initRefreshLayout();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ServiceDetailsActivity.this, 1) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        pinLunAdapter = new CommentAdapter(pinLunList, this);
        pinlunRecyclerview.setLayoutManager(gridLayoutManager);
        pinlunRecyclerview.setAdapter(pinLunAdapter);
        refreshLayout.setLinearLayoutManager(pinLunList, gridLayoutManager);

        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(ServiceDetailsActivity.this, 1) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        pictureAdapter = new ShopPictureAdapter(pictureList, this);
        shopPictureList.setLayoutManager(gridLayoutManager1);
        shopPictureList.setAdapter(pictureAdapter);//详情图片
    }

    @Override
    public void initData() {

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.btn_buy:
                Intent intent = new Intent(this, ServiceConfirmOrderActivity.class);
                intent.putExtra("CommodityBean", bean);
                startActivityForResult(intent, ActivityConstants.REQUEST_CODE);
                break;
            case R.id.linear_shop:
                this.finish();//返回超市
                break;
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        pinLunList.clear();
        mPresenter.queryServiceDetails(itemId);
        mPresenter.queryServiceComments(itemId, 0, 10);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mPresenter.queryServiceDetails(itemId);
        mPresenter.queryServiceComments(itemId, pinLunList.size(), 10);
        return true;
    }


    private void initRefreshLayout() {
        //设置代理
        refreshLayout.setDelegate(this);
        //设置刷新风格
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(this, true);
        refreshLayout.setRefreshViewHolder(mBGARefreshViewHolder);
    }

    @Override
    public void onStart() {
        if (pinLunList.size() > 0) {
            pinLunList.clear();
        }
        mPresenter = new ServiceDetailPresenterImpl(this, this);
        mPresenter.queryServiceDetails(itemId);
        mPresenter.queryServiceComments(itemId, 0, 10);
        banner.startAutoPlay();
        super.onStart();

    }

    @Override
    public void onStop() {
        banner.stopAutoPlay();
        super.onStop();
    }

    public void closeRefresh() {
        refreshLayout.endRefreshing();
        refreshLayout.endLoadingMore();
    }

    private void refreshView(CommodityBean data) {//刷新界面
        if (data == null) return;
        bean = data;
        if (data.getImageList().length > 0) {
            List<String> list = new ArrayList<String>();
            for (String imageList : data.getImageList()) {
                list.add(imageList);
            }
            banner.update(list);
        }
        if (data.getDetailsImageList().length > 0) {
            if (pictureList.size() > 0) {
                pictureList.clear();
            }
            for (String str : data.getDetailsImageList()) {
                pictureList.add(str);
            }
            pictureAdapter.notifyDataSetChanged();
            shopPictureList.setVisibility(View.VISIBLE);
        } else {
            shopPictureList.setVisibility(View.GONE);
        }
        commundityNameTv.setText(data.getItemName());
        commodityJishaoTv.setText(data.getIntroduction());
        tvPrice.setText("￥" + data.getPrice());
        closeRefresh();
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public void queryServiceDetailSucces(CommodityBean bean) {
        refreshView(bean);
    }

    @Override
    public void queryServiceCommentSucces(List<PinLunBean> datas) {
        closeRefresh();
        pinLunList.addAll(datas);
        pinLunAdapter.notifyDataSetChanged();
    }

    @Override
    public void queryServiceCommentError() {
        closeRefresh();
    }
}
