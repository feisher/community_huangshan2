package com.yusong.community.ui.home.activity;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.MainActivity;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.home.mvp.implView.ILoginView;
import com.yusong.community.ui.home.mvp.presenter.impl.ILoginPresenterImpl;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.Constants;
import com.yusong.community.utils.ToastUtils;

import org.apache.commons.lang.StringUtils;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by quaner on 16/12/30.
 */
public class LoginActivity extends BaseActivity implements ILoginView {
    @InjectView(R.id.ll_back)
    LinearLayout mLlBack;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.tv_text)
    TextView mTvText;
    @InjectView(R.id.rl_txt)
    RelativeLayout mRlTxt;
    @InjectView(R.id.et_name)
    EditText mEtName;
    @InjectView(R.id.et_pwd)
    EditText mEtPwd;
    @InjectView(R.id.btn_login)
    Button mBtnLogin;
    @InjectView(R.id.tv_forgetPwd)
    TextView mTvForgetPwd;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;

    private ILoginPresenterImpl mPresenter;
    private boolean mLogout = true;

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
        return R.layout.activity_home_login;
    }

    @Override
    public void initView() {

        Intent intent = getIntent();
        if (intent != null) {
            mLogout = intent.getBooleanExtra("logout", true);
        }


        initTitle();
        mPresenter = new ILoginPresenterImpl(this, this);

        if (mLogout) {

            String token = CacheUtils.getToken(this);
            if (!StringUtils.isEmpty(token)) {
                jumpHome();
            }
        } else {
            ToastUtils.showShort(this, "账号已在其他设备登录，如非本人操作，请及时修改密码！");
        }

    }

    private void initTitle() {

        mLlBack.setVisibility(View.GONE);
        mRlTxt.setVisibility(View.VISIBLE);
        mTvText.setText("注册");
        mTvTitle.setText("用户登录");

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.ll_back, R.id.btn_login, R.id.tv_forgetPwd, R.id.rl_txt})
    public void onClick(View view) {
        Intent mIntent = null;
        switch (view.getId()) {
            case R.id.btn_login://登录
                String username = mEtName.getText().toString().trim();
                String pswd = mEtPwd.getText().toString().trim();
                mPresenter.login(username, pswd, Constants.AGENTID);

                break;
            case R.id.tv_forgetPwd://忘记密码
                mIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                mIntent.putExtra("type", 2);
                startActivity(mIntent);
                break;
            case R.id.rl_txt://注册
                mIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                mIntent.putExtra("type", 1);
                startActivity(mIntent);
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        loginTimeout = false;
    }


    @Override
    public void jumpHome() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(this);
    }
}
