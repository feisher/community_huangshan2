package com.yusong.club.ui.me.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.MainActivity;
import com.yusong.club.ui.base.BaseFragment;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.charge.activity.ChargeMyOrderActivity;
import com.yusong.club.ui.community_service.activity.ServiceOrderActivity;
import com.yusong.club.ui.express.activity.AddressActivity;
import com.yusong.club.ui.express.activity.MailOrderActivity;
import com.yusong.club.ui.home.activity.LoginActivity;
import com.yusong.club.ui.me.activity.BalanceActivity;
import com.yusong.club.ui.me.activity.MyInfoActivity;
import com.yusong.club.ui.me.activity.MySettingActivity;
import com.yusong.club.ui.me.adapter.MeFragmentMyOrderFunModuleAdapter;
import com.yusong.club.ui.me.adapter.MeFragmentMyToolsFunModuleAdapter;
import com.yusong.club.ui.me.mvp.entity.MyOrderFunModuleDatas;
import com.yusong.club.ui.me.mvp.entity.MyToolsFunModuleDatas;
import com.yusong.club.ui.me.mvp.entity.UserInfo;
import com.yusong.club.ui.me.mvp.implView.IMeFregmentView;
import com.yusong.club.ui.me.mvp.presenter.impl.MeFregmentPresenterImpl;
import com.yusong.club.ui.shoppers.activity.ShopOrderActivity;
import com.yusong.club.ui.supermarket.SuperMarketOrderActivity;
import com.yusong.club.utils.ActivityConstants;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.SpaceItemDecoration;
import com.yusong.club.utils.ToastUtils;
import com.yusong.club.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.club.utils.bga.BGARefreshLayout;
import com.yusong.club.utils.bga.BGARefreshViewHolder;
import com.yusong.club.utils.glide.GlideImgManager;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by quaner on 16/12/26.
 */

public class MeFragment extends BaseFragment implements IMeFregmentView, BGARefreshLayout.BGARefreshLayoutDelegate {

    @InjectView(R.id.iv_user_icon)
    ImageView ivUserIcon;
    @InjectView(R.id.tv_click_login)
    TextView tvClickLogin;
    @InjectView(R.id.tv_user_level)
    TextView tvUserLevel;
    @InjectView(R.id.tv_signin_status)
    TextView tvSigninStatus;
    @InjectView(R.id.tv_my_balance)
    TextView tvMyBalance;
    @InjectView(R.id.ll_my_balance)
    LinearLayout llMyBalance;
    @InjectView(R.id.tv_my_red_packet)
    TextView tvMyRedPacket;
    @InjectView(R.id.ll_my_red_packet)
    LinearLayout llMyRedPacket;
    @InjectView(R.id.tv_my_integral)
    TextView tvMyIntegral;
    @InjectView(R.id.ll_my_integral)
    LinearLayout llMyIntegral;
    @InjectView(R.id.ll_logined_fun_layout)
    LinearLayout llLoginedFunLayout;
    @InjectView(R.id.rv_myorder)
    RecyclerView rvMyorder;
    @InjectView(R.id.rv_mytools)
    RecyclerView rvMytools;
    @InjectView(R.id.ll_setting)
    LinearLayout llSetting;
    @InjectView(R.id.rl_refresh)
    BGARefreshLayout rlRefresh;

    private int[] mMyOrderFunModuleItems_img = {
            R.mipmap.my_order_send_package, R.mipmap.my_order_take_package,
            R.mipmap.my_order_put_package, R.mipmap.my_order_charging_pile,
            R.mipmap.my_order_eshop, R.mipmap.my_order_community,
            R.mipmap.my_order_travel, R.mipmap.super_market_order
    };
// R.mipmap.img_empty_lucency
    private String[] mMyOrderFunModuleItems_name = {
            "寄件订单", "取件订单", "存包订单", "充电订单",
            "电商订单", "社区订单", "旅游订单", "超市订单"

    };
    private int[] mMyToolsFunModuleItems_img = {
            R.mipmap.my_tools_addressbook, R.mipmap.my_tools_health,
            R.mipmap.my_tools_collect, R.mipmap.my_tools_share
    };
    private String[] mMyToolsFunModuleItems_name = {"地址簿", "我的健康", "我的收藏", "我的评论"};

    List<MyOrderFunModuleDatas> mMyOrderFunModuleDatasList = new ArrayList<>();
    List<MyToolsFunModuleDatas> mMyToolsFunModuleDatasList = new ArrayList<>();
    public MeFragmentMyOrderFunModuleAdapter mMyOrderFunModuleAdapter;
    public MeFragmentMyToolsFunModuleAdapter mMyToolsFunModuleAdapter;
    public MeFregmentPresenterImpl mPresenter;
    public MainActivity activity;

    @Override
    public View initView() {
        return View.inflate(mContext, R.layout.fragment_me, null);
    }

    @Override
    public void initData() {
        activity = (MainActivity) mContext;
        initRefreshLayout();//初始化刷新控件
        mPresenter = new MeFregmentPresenterImpl(this, this.mContext);
        mPresenter.queryUserInfo(CacheUtils.getToken(this.mContext));
        for (int i = 0; i < mMyOrderFunModuleItems_img.length; i++) {
            MyOrderFunModuleDatas mMyOrderFunModuleDatas = new MyOrderFunModuleDatas(mMyOrderFunModuleItems_img[i], mMyOrderFunModuleItems_name[i]);
            mMyOrderFunModuleDatasList.add(mMyOrderFunModuleDatas);
        }
        for (int i = 0; i < mMyToolsFunModuleItems_img.length; i++) {
            MyToolsFunModuleDatas mMyToolsFunModuleDatas = new MyToolsFunModuleDatas(mMyToolsFunModuleItems_img[i], mMyToolsFunModuleItems_name[i]);
            mMyToolsFunModuleDatasList.add(mMyToolsFunModuleDatas);
        }
        initFunctionModule();
    }


    /**
     * 初始化功能模块
     */
    private void initFunctionModule() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mMyOrderFunModuleAdapter = new MeFragmentMyOrderFunModuleAdapter(mMyOrderFunModuleDatasList, mContext);
        rvMyorder.setAdapter(mMyOrderFunModuleAdapter);
        rvMyorder.addItemDecoration(new SpaceItemDecoration(2, 1));
        rvMyorder.setLayoutManager(gridLayoutManager);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(mContext, 4) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mMyToolsFunModuleAdapter = new MeFragmentMyToolsFunModuleAdapter(mMyToolsFunModuleDatasList, mContext);
        rvMytools.setAdapter(mMyToolsFunModuleAdapter);
        rvMytools.setLayoutManager(gridLayoutManager1);
        rvMytools.addItemDecoration(new SpaceItemDecoration(2, 1));

    }

    @Override
    public void initListener() {
        mMyOrderFunModuleAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                Intent mIntent = null;
                switch (position) {
                    case 0:
                        mIntent = new Intent(mContext, MailOrderActivity.class);
                        mIntent.putExtra(ActivityConstants.ORDER_TYPE, 2);
                        mContext.startActivity(mIntent);
                        break;
                    case 1:
                        mIntent = new Intent(mContext, MailOrderActivity.class);
                        mIntent.putExtra(ActivityConstants.ORDER_TYPE, 1);
                        mContext.startActivity(mIntent);
                        break;
                    case 2:
                        mIntent = new Intent(mContext, MailOrderActivity.class);
                        mIntent.putExtra(ActivityConstants.ORDER_TYPE, 3);
                        mContext.startActivity(mIntent);
                        break;
                    case 3:
                        mContext.startActivity(new Intent(mContext, ChargeMyOrderActivity.class));
                        break;
                    case 4:
                        mIntent = new Intent(mContext, ShopOrderActivity.class);
                        mContext.startActivity(mIntent);
                        break;
                    case 5:
                        mIntent = new Intent(mContext, ServiceOrderActivity.class);
                        mContext.startActivity(mIntent);
                        break;
                    case 6:
                        ToastUtils.showShort(mContext, "暂无");
                        break;
                    case 7:
                        mIntent = new Intent(mContext, SuperMarketOrderActivity.class);
                        mContext.startActivity(mIntent);
                        break;
                }
            }
        });
        mMyToolsFunModuleAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                Intent mIntent = null;
                switch (position) {
                    case 0:
                        mIntent = new Intent(mContext, AddressActivity.class);
                        mIntent.putExtra(ActivityConstants.INFO_FILL, 3);
                        startActivity(mIntent);
                        break;
                    case 1:
                        ToastUtils.showShort(mContext, "功能暂未开放");
                        break;
                    case 2:
                        ToastUtils.showShort(mContext, "功能暂未开放");
                        break;
                    case 3:
                        ToastUtils.showShort(mContext, "功能暂未开放");
                        break;

                }
            }
        });

    }


    @OnClick({R.id.iv_user_icon, R.id.tv_click_login, R.id.tv_signin_status, R.id.ll_my_balance, R.id.ll_my_red_packet, R.id.ll_my_integral, R.id.ll_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_user_icon:
                if (!TextUtils.isEmpty(CacheUtils.getToken(mContext))) {
                    mContext.startActivity(new Intent(mContext, MyInfoActivity.class));
                } else {
                    mContext.startActivity(new Intent(mContext, LoginActivity.class));
                }
                break;
            case R.id.tv_click_login:
                mContext.startActivity(new Intent(mContext, LoginActivity.class));
                break;
            case R.id.tv_signin_status:
                mPresenter.signIn();
                break;
            case R.id.ll_my_balance:
                startActivity(new Intent(getActivity(), BalanceActivity.class));
//                ToastUtils.showShort(getActivity(), "跳转到余额详情界面");
                break;
            case R.id.ll_my_red_packet:
//                ToastUtils.showShort(getActivity(), "跳转到红包详情界面");
                break;
            case R.id.ll_my_integral:
//                ToastUtils.showShort(getActivity(), "跳转到积分详情界面");
                break;
            case R.id.ll_setting:
                if (!TextUtils.isEmpty(CacheUtils.getToken(mContext))) {
                    mContext.startActivity(new Intent(mContext, MySettingActivity.class));
                } else {
                    mContext.startActivity(new Intent(mContext, LoginActivity.class));
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        UserInfo userInfo = CacheUtils.getUserInfo(mContext);
        updateShowUserInfo(userInfo);
    }

    private void updateShowUserInfo(UserInfo userInfo) {
        if (userInfo != null) {
            if (!TextUtils.isEmpty(userInfo.getPortrait())) {
                GlideImgManager.loadCircleImage(mContext, userInfo.getPortrait(), ivUserIcon);
            }
            TextView mTvTitle = (TextView) activity.findViewById(R.id.tv_title);
            if (!StringUtils.isEmpty(userInfo.getNickname())) {
                mTvTitle.setText(userInfo.getNickname());
            } else if (!StringUtils.isEmpty(userInfo.getMobile())) {
                mTvTitle.setText(userInfo.getMobile());
            }
            if (userInfo.getSignIn() == 0) {
                tvSigninStatus.setText("未签到");
                tvSigninStatus.setBackgroundResource(R.drawable.bg_yellow_oval);
            } else {
                tvSigninStatus.setText("已签到");
                tvSigninStatus.setBackgroundResource(R.drawable.bg_light_blue_oval);
            }
        }
    }


    @Override
    public void getUserInfoCallback(UserInfo data) {
        if (rlRefresh != null) {
            rlRefresh.endRefreshing();
        }
        GlideImgManager.loadCircleImage(mContext, data.getPortrait(), ivUserIcon);
        TextView mTvTitle = (TextView) activity.findViewById(R.id.tv_title);
        if (!StringUtils.isEmpty(data.getNickname())) {
            mTvTitle.setText(data.getNickname());
        } else if (!StringUtils.isEmpty(data.getMobile())) {
            mTvTitle.setText(data.getMobile());
        }
        if (!TextUtils.isEmpty(data.getBalance())) {
            tvMyBalance.setText(data.getBalance() + "");
        } else {
            tvMyBalance.setText("0");
        }
        tvMyRedPacket.setText(data.getRedPacketCount() + "");//红包
        tvMyIntegral.setText(data.getScore() + "");//积分
        tvUserLevel.setText("等级：Lv" + data.getLevel());
        if (data.getSignIn() == 0) {
            tvSigninStatus.setText("未签到");
            tvSigninStatus.setBackgroundResource(R.drawable.bg_yellow_oval);
        } else {
            tvSigninStatus.setText("已签到");
            tvSigninStatus.setBackgroundResource(R.drawable.bg_light_blue_oval);
        }

    }

    /**
     * 签到回调
     */
    @Override
    public void signInCallback() {
        tvSigninStatus.setText("已签到");
        tvSigninStatus.setBackgroundResource(R.drawable.bg_light_blue_oval);
    }

    @Override
    public void closeLoading() {
        if (rlRefresh != null) {
            rlRefresh.endRefreshing();
        }
    }

    /**
     * 初始化刷新布局
     */
    private void initRefreshLayout() {
        rlRefresh.setDelegate(this);//设置代理
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(mContext, true);
        rlRefresh.setRefreshViewHolder(mBGARefreshViewHolder);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mPresenter.queryUserInfo(CacheUtils.getToken(this.mContext));
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(getActivity());
    }
}
