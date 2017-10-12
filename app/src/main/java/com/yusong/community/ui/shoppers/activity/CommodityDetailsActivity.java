package com.yusong.community.ui.shoppers.activity;

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
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.school.teacher.view.flowlayout.FlowLayout;
import com.yusong.community.ui.school.teacher.view.flowlayout.TagAdapter;
import com.yusong.community.ui.school.teacher.view.flowlayout.TagFlowLayout;
import com.yusong.community.ui.shoppers.adapter.CommentAdapter;
import com.yusong.community.ui.shoppers.adapter.ShopPictureAdapter;
import com.yusong.community.ui.shoppers.bean.CommodityBean;
import com.yusong.community.ui.shoppers.bean.PinLunBean;
import com.yusong.community.ui.shoppers.bean.SpecificationBean;
import com.yusong.community.ui.shoppers.mvp.implView.ImplCanQGView;
import com.yusong.community.ui.shoppers.mvp.implView.ImplQueryQCommdityDetailsView;
import com.yusong.community.ui.shoppers.mvp.implView.ImplQueryShangPinPinLunView;
import com.yusong.community.ui.shoppers.mvp.implView.ImplQueryShangpingDetailsView;
import com.yusong.community.ui.shoppers.mvp.presenter.impl.ImplCanQGPresenterImpl;
import com.yusong.community.ui.shoppers.mvp.presenter.impl.ImplQueryQCommdityDetailsPersenterImpl;
import com.yusong.community.ui.shoppers.mvp.presenter.impl.ImplQueryShangPinPinLunPresenterImpl;
import com.yusong.community.ui.shoppers.mvp.presenter.impl.ImplQueryShangpingDetailsPresenterImpl;
import com.yusong.community.ui.supermarket.mvp.ImolView.SMCommodityView;
import com.yusong.community.ui.supermarket.mvp.presenter.impl.SMCommodityPresenterImpl;
import com.yusong.community.utils.ActivityConstants;
import com.yusong.community.utils.ToastUtils;
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
 *         created at 2017/3/6 9:52.
 *         商品详情页面
 */

public class CommodityDetailsActivity extends BaseActivity implements View.OnClickListener,
        ImplQueryShangpingDetailsView, ImplQueryShangPinPinLunView,
        BGARefreshLayout.BGARefreshLayoutDelegate, ImplQueryQCommdityDetailsView, ImplCanQGView, SMCommodityView {

    @InjectView(R.id.commundity_name_tv)
    TextView commundityNameTv;
    @InjectView(R.id.linear_shop)
    LinearLayout linearShop;
    @InjectView(R.id.commodity_jishao_tv)
    TextView commodityJishaoTv;
    @InjectView(R.id.tv_price)
    TextView tvPrice;
    @InjectView(R.id.btn_buy)
    Button btnBuy;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.pinlun_recyclerview)
    RecyclerView pinlunRecyclerview;
    @InjectView(R.id.shop_picture_list)
    RecyclerView shopPictureList;
    @InjectView(R.id.refresh_layout)
    BGARefreshLayout refreshLayout;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.banner)
    Banner banner;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
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
    @InjectView(R.id.sold_tv)
    TextView soldTv;
    @InjectView(R.id.left_tv)
    TextView leftTv;
    @InjectView(R.id.select_layout)
    LinearLayout selectLayout;
    @InjectView(R.id.id_flowlayout)
    TagFlowLayout idFlowlayout;
    @InjectView(R.id.guige_tv)
    TextView guigeTv;
    private List<String> imageStr = new ArrayList<String>();
    private List<PinLunBean> pinLunList = new ArrayList<PinLunBean>();
    private CommentAdapter pinLunAdapter;
    private ShopPictureAdapter pictureAdapter;
    private List<String> pictureList = new ArrayList<String>();
    private int itemId = -1;//商品详情
    private int isQG = -1;
    private ImplQueryShangpingDetailsPresenterImpl shangpingDetailsPresenterImpl;//查询商品详情
    private ImplQueryShangPinPinLunPresenterImpl queryShangPinPinLunPresenterImpl;//查询商品评论
    private ImplQueryQCommdityDetailsPersenterImpl queryQCommdityDetailsPersenterImpl;//查询抢购商品详情
    private SMCommodityPresenterImpl smPresenter;//超市商品详情
    private CommodityBean bean;
    private String specitication = "";

    @Override
    public void refreshCommundityDetails(CommodityBean data) {//商品详情
        refreshView(data);
    }

    @Override
    public void queryCommoditySucceed(CommodityBean bean) {//超市商品详情
        refreshView(bean);
    }

    @Override
    public void queryCommodityCommentSucced(List<PinLunBean> data) {//超市商品评论
        closeRefresh();
        pinLunList.addAll(data);
        pinLunAdapter.notifyDataSetChanged();
    }

    @Override
    public void queryCommodityCommentError() {
        closeRefresh();
    }

    @Override
    public void succedQgCommdityDetails(CommodityBean data) {//抢购商品详情
        refreshView(data);
    }

    @Override
    public void refreshPinlun(List<PinLunBean> data) {//商品评论
        closeRefresh();
        pinLunList.addAll(data);
        pinLunAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    protected void initListener() {
        llBack.setOnClickListener(this);
        linearShop.setOnClickListener(this);
        btnBuy.setOnClickListener(this);
        selectLayout.setOnClickListener(this);
    }


    @Override
    public void querySpecificationSuccess(List<SpecificationBean> beanList) {
        speciticationRefresh(beanList);

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_shop_information;
    }

    @Override
    public void querySMSpecificationSuccess(List<SpecificationBean> beanList) {
        speciticationRefresh(beanList);
    }

    private void speciticationRefresh(List<SpecificationBean> beanList) {//规格刷新

        if (beanList == null || beanList.size() == 0) {
            return;
        }
        final List<String> mVals = new ArrayList<String>();
        for (SpecificationBean bean : beanList) {
            mVals.add(bean.getPropertyValue());
        }
        if (mVals.size() > 0) {
            selectLayout.setVisibility(View.VISIBLE);
            idFlowlayout.setVisibility(View.VISIBLE);
            guigeTv.setText("规格：" + mVals.get(0));
            idFlowlayout.setAdapter(new TagAdapter<String>(mVals) {
                @Override
                public View getView(FlowLayout parent, int position, String s) {
                    TextView tv = (TextView) getLayoutInflater().inflate(R.layout.tv,
                            idFlowlayout, false);
                    tv.setText(s);
                    return tv;
                }
            });
            idFlowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    specitication = mVals.get(position);
//                    idFlowlayout.setVisibility(View.GONE);
                    guigeTv.setText("规格：" + mVals.get(position));
                    return true;
                }
            });
        }
    }

    @Override
    public void initView() {
        Intent intent;
        intent = getIntent();
        itemId = intent.getIntExtra("itemId", 0);
        isQG = intent.getIntExtra("isQG", 0);
        tvTitle.setText("商品详情");
        imageStr.add("");
        banner.setImageLoader(new GlideImgManager());
        banner.setImages(imageStr);
        banner.start();
        initRefreshLayout();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(CommodityDetailsActivity.this, 1) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        pinLunAdapter = new CommentAdapter(pinLunList, this);
        pinlunRecyclerview.setLayoutManager(gridLayoutManager);
        pinlunRecyclerview.setAdapter(pinLunAdapter);
        refreshLayout.setLinearLayoutManager(pinLunList, gridLayoutManager);

        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(CommodityDetailsActivity.this, 1) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        pictureAdapter = new ShopPictureAdapter(pictureList, this);
        shopPictureList.setLayoutManager(gridLayoutManager1);
        shopPictureList.setAdapter(pictureAdapter);//详情图片

        if (isQG == 1 || isQG == 3) {
            btnBuy.setText("立即购买");
            if (isQG == 3) {
                smPresenter = new SMCommodityPresenterImpl(this, CommodityDetailsActivity.this);
                smPresenter.querySMCommodityDetails(itemId);
                smPresenter.querySMComment(itemId, 0, 10);
                smPresenter.querySMSpecification(itemId);
            } else {
                shangpingDetailsPresenterImpl = new ImplQueryShangpingDetailsPresenterImpl(this, CommodityDetailsActivity.this);
                shangpingDetailsPresenterImpl.queryShangpinDetails(itemId);
                shangpingDetailsPresenterImpl.querySpecification(itemId);
            }

        } else {
            btnBuy.setText("立即抢购");
            queryQCommdityDetailsPersenterImpl = new ImplQueryQCommdityDetailsPersenterImpl(this, CommodityDetailsActivity.this);
            queryQCommdityDetailsPersenterImpl.queryQgCommdity(itemId);
        }
        if (isQG != 3) {
            queryShangPinPinLunPresenterImpl = new ImplQueryShangPinPinLunPresenterImpl(this, CommodityDetailsActivity.this);
            queryShangPinPinLunPresenterImpl.queryShangPinPinLun(itemId, 0, 10);
        }
        canQGPresenterImpl = new ImplCanQGPresenterImpl(this, CommodityDetailsActivity.this);
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

    private ImplCanQGPresenterImpl canQGPresenterImpl;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.btn_buy:
                if (isQG == 2) {
                    canQGPresenterImpl.queryCanQG(itemId);
                } else {
                    Intent intent = new Intent(this, ConfirmOrderActivity.class);
                    intent.putExtra("CommodityBean", bean);
                    intent.putExtra("type", isQG);// 如果为3是超市
                    intent.putExtra("standard", specitication);
                    startActivityForResult(intent, ActivityConstants.REQUEST_CODE);
                }

                break;
            case R.id.linear_shop:
                if (isQG == 3) {
                    this.finish();//返回超市
                    return;
                }
                Intent intent1 = new Intent(this, ShopActivity.class);
                intent1.putExtra("shopId", bean.getShopId());
                intent1.putExtra("shopName", bean.getShopName());
                startActivity(intent1);
                break;
            case R.id.select_layout:
//                if(isQG==3){
//                    smPresenter.querySMSpecification(itemId);
//                }else{
//                    shangpingDetailsPresenterImpl.querySpecification(itemId);
//                }

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == ActivityConstants.REQUEST_CODE && ActivityConstants.RESULT_CODE == resultCode) {
//            this.finish();
//        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        if (isQG == 1 || isQG == 3) {
            if (isQG == 3) {
                smPresenter.querySMCommodityDetails(itemId);
            } else {
                shangpingDetailsPresenterImpl.queryShangpinDetails(itemId);
            }
        } else {
            queryQCommdityDetailsPersenterImpl.queryQgCommdity(itemId);
        }
        if (isQG != 3 && queryShangPinPinLunPresenterImpl != null) {
            pinLunList.clear();
            queryShangPinPinLunPresenterImpl.queryShangPinPinLun(itemId, 0, 10);
        } else if (isQG == 3 && smPresenter != null) {
            pinLunList.clear();
            smPresenter.querySMComment(itemId, 0, 10);
        }
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if (isQG != 3 && queryShangPinPinLunPresenterImpl != null) {
            queryShangPinPinLunPresenterImpl.queryShangPinPinLun(itemId, pinLunList.size(), 10);
        } else if (isQG == 3 && smPresenter != null) {
            smPresenter.querySMComment(itemId, pinLunList.size(), 10);
        }
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
        int soldAmount = data.getSoldAmount();
        int seftAmount = data.getLeftAmount();
        if (soldAmount < 0) {
            soldAmount = 0;
        }
        if (seftAmount < 0) {
            seftAmount = 0;
        }
        soldTv.setText(String.format("已售 %d", soldAmount));
        leftTv.setText(String.format("仅剩 %d", seftAmount));
        tvPrice.setText("￥" + data.getPrice());
        closeRefresh();
    }

    @Override
    public void canQG(int limitRestAmount) {
        if (limitRestAmount > 0) {
            Intent intent = new Intent(this, ConfirmOrderActivity.class);
            intent.putExtra("CommodityBean", bean);
            intent.putExtra("canQG", limitRestAmount);
            intent.putExtra("standard", specitication);
            startActivityForResult(intent, ActivityConstants.REQUEST_CODE);
        } else {
            ToastUtils.showShort(this, "抢购失效");
        }
    }

    @Override
    protected void onDestroy() {
        if (shangpingDetailsPresenterImpl != null) {
            shangpingDetailsPresenterImpl.onDestroy();
        }
        if (queryShangPinPinLunPresenterImpl != null) {
            queryShangPinPinLunPresenterImpl.onDestroy();
        }
        if (queryQCommdityDetailsPersenterImpl != null) {
            queryQCommdityDetailsPersenterImpl.onDestroy();
        }
        if (smPresenter != null) {
            smPresenter.onDestroy();
        }
        super.onDestroy();
    }
}
