package com.yusong.community.ui.me.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.home.activity.LoginActivity;
import com.yusong.community.ui.me.mvp.entity.UserInfo;
import com.yusong.community.ui.me.mvp.implView.IMySettingActivityView;
import com.yusong.community.ui.me.mvp.presenter.impl.MySettingActivityPresenterImpl;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.SPUtils;
import com.yusong.community.utils.ToastUtils;
import com.yusong.community.utils.glide.GlideImgManager;

import butterknife.InjectView;
import butterknife.OnClick;


public class MySettingActivity extends BaseActivity implements IMySettingActivityView {
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.iv_user_icon)
    ImageView ivUserIcon;
    @InjectView(R.id.tv_nickname)
    TextView tvNickname;
    @InjectView(R.id.tv_phone)
    TextView tvPhone;
    @InjectView(R.id.ll_look_user_info)
    LinearLayout llLookUserInfo;
    @InjectView(R.id.ll_feedback)
    LinearLayout llFeedback;
    @InjectView(R.id.ll_give_a_mark)
    LinearLayout llGiveAMark;
    @InjectView(R.id.tv_cache_volume)
    TextView tvCacheVolume;
    @InjectView(R.id.ll_clear_cache)
    LinearLayout llClearCache;
    @InjectView(R.id.ll_about_us)
    LinearLayout llAboutUs;
    @InjectView(R.id.tv_exit_login)
    TextView tvExitLogin;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    public MySettingActivityPresenterImpl mPresenter;
    private Context mContext;

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
    protected void initListener() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_my_setting;
    }

    @Override
    public void initView() {
        tvTitle.setText("设置");
    }

    @Override
    public void initData(){
        mContext=MySettingActivity.this;
        UserInfo userInfo = (UserInfo) SPUtils.readObject(this, "UserInfo");
        if (userInfo!=null) {
            if (!TextUtils.isEmpty(userInfo.getPortrait())) {
                GlideImgManager.loadCircleImage(this, userInfo.getPortrait(),ivUserIcon);
            } if (!TextUtils.isEmpty(userInfo.getNickname())) {
                tvNickname.setText(userInfo.getNickname());
            }if (!TextUtils.isEmpty(userInfo.getMobile())) {
                tvPhone.setText(userInfo.getMobile());
            }
        }
        String totalCacheSize = null;
        try {
            totalCacheSize = CacheUtils.getTotalCacheSize(this);
            tvCacheVolume.setText(totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mPresenter = new MySettingActivityPresenterImpl(this, this);
    }


    @OnClick({R.id.ll_back, R.id.ll_look_user_info, R.id.ll_feedback, R.id.ll_give_a_mark, R.id.ll_clear_cache, R.id.ll_about_us, R.id.tv_exit_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_look_user_info:
                startActivity(new Intent(this, MyInfoActivity.class));
                break;
            case R.id.ll_feedback:
                LayoutInflater factory2 = LayoutInflater.from(this);//提示框
                final View view2 = factory2.inflate(R.layout.layout_with_edittext_dialog, null);//这里必须是final的
                final EditText editText = (EditText) view2.findViewById(R.id.editText);//获得输入框对象
                editText.setHint("请输入反馈意见");
//                final EditText editText = new EditText(this);
                editText.setBackgroundResource(R.drawable.shape_editor_ll);
                new AlertDialog.Builder(this)
                        .setTitle("请选择填写反馈意见")//提示框标题
                        .setView(view2)
                        .setPositiveButton("提交",//提示框的两个按钮
                                new android.content.DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        String content = editText.getText().toString().trim();
                                        mPresenter.feedback(content);
                                        dialog.dismiss();
                                    }
                                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
                break;
            case R.id.ll_give_a_mark:
                LayoutInflater factory = LayoutInflater.from(this);//提示框
                final View view1 = factory.inflate(R.layout.layout_ratingbar_dialog, null);//这里必须是final的
                final RatingBar mRatingBar=(RatingBar)view1.findViewById(R.id.rating_bar);//获得输入框对象
                new AlertDialog.Builder(this)
                        .setTitle("请选择评分")//提示框标题
                        .setView(view1)
                        .setPositiveButton("提交",//提示框的两个按钮
                                new android.content.DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        float numStars =  mRatingBar.getRating();
                                        mPresenter.scoring(numStars);
                                        dialog.dismiss();
                                    }
                                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
                break;
            case R.id.ll_clear_cache:
//                ToastUtils.showShort(this,"直接进行后台清除缓存操作");
                CacheUtils.clearAllCache(this);
                tvCacheVolume.setText("0 MB");
                break;
            case R.id.ll_about_us:
                WebView webView = new WebView(this);
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(CacheUtils.getUserInfo(this).getAboutUrl());
//                webView.loadUrl("http://baike.baidu.com/link?url=UejPgvjApF7YtN1Cucy3Fe0rHosLlv-hc9qbmXdik_vJ6O03A6OCxRljY6OUD8gr1z7w7cVsXOeDA-ydKgXblS0tRNtkvQLIDd5Tr0mjaerpDVoa39F6aB2H3r9e5v6nyH92KB2jCEy323axuzl6gPtE78VybbMCtuvpDWUchqlz3HYozfdHwtotcK7ehAs95atYaOzgWQIUzpSj0whhqCqnhmWPD6xksy1TC9DcIZC");
                new AlertDialog.Builder(this)
                        .setTitle("关于智联")//提示框标题
                        .setView(webView)
                        .setPositiveButton("知道啦",//提示框的两个按钮
                                new android.content.DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        dialog.dismiss();
                                    }
                                }).create().show();
                break;
            case R.id.tv_exit_login:
//                ToastUtils.showShort(this, "退出登陆，进入登陆页面");
                new AlertDialog.Builder(this)
                    .setTitle("提示")//提示框标题
                    .setMessage("您确定要退出登录吗？")
                    .setPositiveButton("确定",//提示框的两个按钮
                            new android.content.DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    MyApplication.exit();
                                    EMClient.getInstance().logout(true);
                                    CacheUtils.clearSP(MySettingActivity.this);
                                    startActivity(new Intent(MySettingActivity.this, LoginActivity.class));
                                    dialog.dismiss();
                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();

                break;
        }
    }
    @Override
    public void feedbackCallback() {
        ToastUtils.showShort(getApplicationContext(),"反馈成功,感谢您的参与！");
    }

    @Override
    public void scoringCallback() {
        ToastUtils.showShort(getApplicationContext(), "评分成功,感谢您的参与！");
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(this);
    }
}
