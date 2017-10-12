package com.yusong.community.ui;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.charge.view.BaseDialog;
import com.yusong.community.ui.community.activity.CreatePostActivity;
import com.yusong.community.ui.community.activity.NearbyCommuityActivity;
import com.yusong.community.ui.community.fragment.CommunityFragment;
import com.yusong.community.ui.community.mvp.entity.PostsCatogrey;
import com.yusong.community.ui.home.fragment.DiscoverFragment;
import com.yusong.community.ui.home.fragment.HomeFragment;
import com.yusong.community.ui.home.fragment.MsgFragment;
import com.yusong.community.ui.home.mvp.cache.TokenInfo;
import com.yusong.community.ui.home.mvp.implView.IMainActivityView;
import com.yusong.community.ui.home.mvp.presenter.impl.IMainActivityPresenterImpl;
import com.yusong.community.ui.me.activity.MySettingActivity;
import com.yusong.community.ui.me.fragment.MeFragment;
import com.yusong.community.ui.me.mvp.entity.UserInfo;
import com.yusong.community.ui.shoppers.activity.FindCommodityActivity;
import com.yusong.community.ui.visitor.entity.CommuntitySetingBean;
import com.yusong.community.ui.visitor.entity.OwnerInfo;
import com.yusong.community.ui.visitor.mvp.ImplView.IViewOwnerInfo;
import com.yusong.community.ui.visitor.mvp.prsenter.impl.QueryOwenInfoPrsenterImpl;
import com.yusong.community.utils.AppUtils;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.MD5Utils;
import com.yusong.community.utils.ToastUtils;
import com.yusong.community.utils.interfacecallback.SetMainActivityCurrentFragment;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by quaner on 16/12/26.
 */
public class MainActivity extends BaseActivity implements IViewOwnerInfo,
        IMainActivityView, RadioGroup.OnCheckedChangeListener,
        SetMainActivityCurrentFragment {
    private final int SDK_PERMISSION_REQUEST = 127;
    @InjectView(R.id.ll_back)
    LinearLayout mLlBack;
    @InjectView(R.id.ll_img)
    LinearLayout mLlImg;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.iv_img)
    ImageView ivImg;
    private String permissionInfo;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.frameLayout)
    FrameLayout mFrameLayout;
    @InjectView(R.id.rb_home)
    RadioButton mRbHome;
    @InjectView(R.id.rb_msg)
    RadioButton mRbMsg;
    @InjectView(R.id.rb_commerce)
    RadioButton mRbCommerce;
    @InjectView(R.id.rb_community)
    RadioButton mRbCommunity;
    @InjectView(R.id.rb_me)
    RadioButton mRbMe;
    @InjectView(R.id.rg_group)
    RadioGroup mRgGroup;
    BaseDialog baseDialog;
    Fragment fragment = null;
    List<PostsCatogrey> data;
    public IMainActivityPresenterImpl mPresenter;
    private QueryOwenInfoPrsenterImpl queryOwenInfoPrsenterImpl;//查询业主信息

    private int clickTyep = 1;

    @Override
    protected void initListener() {
        mRgGroup.setOnCheckedChangeListener(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_school_main;
    }

    @Override
    public void initView() {
        //默认第一个为选中
        mRgGroup.check(R.id.rb_home);
        mTvTitle.setText("首页");
        mLlBack.setVisibility(View.GONE);
        //默认跳入首页
        fragment = new HomeFragment();
        jumpFragment(R.id.rb_home);
        getPersimmions();
//        PgyUpdateManager.register(this, "com.yusong.community.pgyerprovider");

        TokenInfo info = CacheUtils.getTokenInfo(this);
        String account = info.getImAccount();
        if (!StringUtils.isEmpty(account)) {
            AppUtils.loginIm(account, MD5Utils.md5(info.getPwd() + "huanxin"));
        }
    }


    @Override
    public void initData() {
        mPresenter = new IMainActivityPresenterImpl(this, this);
        mPresenter.queryUserInfo(CacheUtils.getToken(this));
        queryOwenInfoPrsenterImpl = new QueryOwenInfoPrsenterImpl(this, MainActivity.this);
        queryOwenInfoPrsenterImpl.queryOwnerInfo();
        queryOwenInfoPrsenterImpl.queryCommuntityStting();
    }


    /**
     * 响应选择改变的监听
     *
     * @ checkedId 是当前选中的RadioButton的ID值
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //使用指定的fragment切换当前页面
        jumpFragment(checkedId);
    }

    public void jumpFragment(int checkedId) {
        String tag = "";
        switch (checkedId) {
            case R.id.rb_home://主页
                fragment = new HomeFragment();
                mTvTitle.setText("首页");
                mLlImg.setVisibility(View.VISIBLE);
                ivImg.setBackgroundResource(R.mipmap.call_home);
                mLlBack.setVisibility(View.GONE);
                tag = "HomeFragment";
                clickTyep = 1;
                break;

            case R.id.rb_msg://消息
                fragment = new MsgFragment();
                mTvTitle.setText("消息");
                mLlImg.setVisibility(View.GONE);
                mLlBack.setVisibility(View.GONE);
                tag = "MsgFragment";
                break;

            case R.id.rb_commerce://商圈
                fragment = new DiscoverFragment();
                mTvTitle.setText("商圈");
                mLlImg.setVisibility(View.VISIBLE);
                ivImg.setBackgroundResource(R.mipmap.find_icon);
                clickTyep = 3;
                mLlBack.setVisibility(View.GONE);
                tag = "DiscoverFragment";
                break;
            case R.id.rb_community://社区
                fragment = new CommunityFragment();
                mTvTitle.setText("社区");
                mLlImg.setVisibility(View.VISIBLE);
                ivImg.setBackgroundResource(R.mipmap.community_create);
                mLlBack.setVisibility(View.GONE);
                tag = "CommunityFragment";
                clickTyep = 2;
                break;
            case R.id.rb_me://我的
                fragment = new MeFragment();
                mTvTitle.setText("我的");
                mLlImg.setVisibility(View.GONE);
                mLlBack.setVisibility(View.VISIBLE);
                tag = "MeFragment";
//                GlideImgManager.loadImage(this, R.drawable.select_setting, ivBack);
                ivBack.setBackgroundResource(R.drawable.select_setting);
//                ivBack.setImageResource(R.drawable.select_setting);
                break;
        }
        //使用指定的fragment切换当前页面

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, fragment, tag)
                .commit();
    }


    @TargetApi(23)
    private void getPersimmions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<String>();
            /***
             * 定位权限为必须权限，用户如果禁止，则每次进入都会申请
             */
            // 定位精确位置
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
            /*
             * 读写权限和电话状态权限非必要权限(建议授予)只会申请一次，用户同意或者禁止，只会弹一次
			 */
            // 读写权限
            if (addPermission(permissions, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissionInfo += "Manifest.permission.WRITE_EXTERNAL_STORAGE Deny \n";
            }
            // 读取电话状态权限
            if (addPermission(permissions, Manifest.permission.READ_PHONE_STATE)) {
                permissionInfo += "Manifest.permission.READ_PHONE_STATE Deny \n";
            }

            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
            }
        }
    }

    @TargetApi(23)
    private boolean addPermission(ArrayList<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) { // 如果应用没有获得对应权限,则添加到列表中,准备批量申请
            if (shouldShowRequestPermissionRationale(permission)) {
                return true;
            } else {
                permissionsList.add(permission);
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        baseDialog = new BaseDialog(this)
                .setTitle("提示")
                .setMessage("您确定要退出应用吗？")
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        baseDialog.dismiss();
                    }
                }).setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        baseDialog.dismiss();
                        MyApplication.exit();
                        finish();
                    }
                });
    }

    @Override
    public void CurrentFragment(int checkId) {
        mRgGroup.check(checkId);
        jumpFragment(checkId);
    }

    @OnClick({R.id.ll_back, R.id.ll_img})
    public void onClick(View view) {
        Intent mIntent = null;
        switch (view.getId()) {
            case R.id.ll_back://我的界面中跳转到设置界面
                mIntent = new Intent(this, MySettingActivity.class);
                startActivity(mIntent);
                break;
            case R.id.ll_img:
                if (clickTyep == 2) {//跳转到发帖界面
                    mIntent = new Intent(this, CreatePostActivity.class);
                    mIntent.putExtra("POSTS_CATEGORY", (Serializable) data);
                    startActivity(mIntent);
                } else if (clickTyep == 1) {//拨打电话
                    if (!TextUtils.isEmpty(CacheUtils.getTenementTel(this))) {
                        final BaseDialog dialog = new BaseDialog(this);
                        dialog.setTitle("联系客服");
                        dialog.setMessage("客服电话:" + CacheUtils.getTenementTel(this) + " 是否联系他们？");
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
                                callDirectly(CacheUtils.getTenementTel(MainActivity.this));
                            }
                        });
                    } else {
                        ToastUtils.showShort(this, "暂无联系方式");
                    }
                } else if (clickTyep == 3) {
                    Intent intent = new Intent(this, FindCommodityActivity.class);
                    intent.putExtra("findType",1);
                    startActivity(intent);
                }
                break;
        }
    }

    /**
     * 回调方法，获取到社区分类
     *
     * @param data
     */
    public void getPostCatagory(List<PostsCatogrey> data) {
        this.data = data;
    }

    /**
     * 获取个人信息回调，判断是否设置社区，没设置就跳转设置社区界面
     *
     * @param data 用户信息对象
     */
    @Override
    public void getUserInfo(UserInfo data) {
        String nickname = data.getNickname();
        if (!StringUtils.isEmpty(nickname)) {
            EMClient.getInstance().updateCurrentUserNick(nickname);
        }
        if (data.getCommunityId() == 0) {
            startActivity(new Intent(this, NearbyCommuityActivity.class));
        }
    }

    @Override
    public void queryOwnerInfoSucces(OwnerInfo data) {

    }

    @Override
    public void queryCommuntitySucces(CommuntitySetingBean communtitySetingBean) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        PgyUpdateManager.unregister();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        if (queryOwenInfoPrsenterImpl != null) {
            queryOwenInfoPrsenterImpl.onDestroy();
        }
    }


    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }


    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
