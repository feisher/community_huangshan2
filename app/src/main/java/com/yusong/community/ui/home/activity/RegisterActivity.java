package com.yusong.community.ui.home.activity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.base.BaseWebViewActivity;
import com.yusong.community.ui.home.mvp.entity.Protocol;
import com.yusong.community.ui.home.mvp.implView.IRegisterView;
import com.yusong.community.ui.home.mvp.presenter.impl.IRegisterPresenterImpl;

import butterknife.InjectView;
import butterknife.OnClick;

import static com.yusong.community.R.id.btn_getCode;


/**
 * Created by quaner on 17/1/3.
 */
public class RegisterActivity extends BaseActivity implements IRegisterView {
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.et_phone)
    EditText mEtPhone;
    @InjectView(btn_getCode)
    Button mBtnGetCode;
    @InjectView(R.id.et_msgCode)
    EditText mEtMsgCode;
    @InjectView(R.id.et_pswd)
    EditText mEtPswd;
    @InjectView(R.id.et_okpwd)
    EditText mEtOkpwd;
    @InjectView(R.id.btn_register)
    Button mBtnRegister;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.tv_protocol)
    TextView mTvProtocol;
    @InjectView(R.id.ll_register)
    LinearLayout mLlRegister;
    private IRegisterPresenterImpl mPresenter;
    private int mType;


    @Override
    protected void initListener() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_home_register;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        mType = intent.getIntExtra("type", 1);
        if (mType == 1) {
            mTvTitle.setText("注册");
            mBtnRegister.setText("注   册");
        } else if (mType == 2) {
            mTvTitle.setText("忘记密码");
            mBtnRegister.setText("确   定");
            mLlRegister.setVisibility(View.GONE);
        }

    }

    @Override
    public void initData() {

    }
    @OnClick({R.id.ll_back, btn_getCode, R.id.btn_register, R.id.tv_protocol})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case btn_getCode:
                mPresenter.getCode(mEtPhone);
                break;
            case R.id.btn_register:
                if (mType == 1) {
                    //注册
                    mPresenter.register(mEtPhone, mEtMsgCode, mEtPswd, mEtOkpwd);
                } else {
                    //修改密码
                    mPresenter.revisePwd(mEtPhone, mEtMsgCode, mEtPswd, mEtOkpwd);
                }
                break;
            case R.id.tv_protocol:
                mPresenter.queryProtocol();

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mPresenter.onDestroy();
    }


    @Override
    public void recovery() {
        mBtnGetCode.setText("获取验证码");//数据发送完后设置为原来的文字
        mBtnGetCode.setTextColor(Color.parseColor("#3492E9"));
        mBtnGetCode.setBackgroundResource(R.drawable.shape_send_msg);//数据发送完后设置为原来背景色
        mBtnGetCode.setEnabled(true);
    }

    @Override
    public void start() {
        mBtnGetCode.setEnabled(false);//在发送数据的时候设置为不能点击
        mBtnGetCode.setBackgroundResource(R.drawable.shape_send_msg2);
        mBtnGetCode.setTextColor(Color.WHITE);
    }

    /**
     * 更新倒计时显示
     */
    @Override
    public void upDataTime(Long aLong) {
        mBtnGetCode.setText("剩余时间" + aLong + "秒");
        mBtnGetCode.setTextColor(Color.WHITE);
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void jumpActivity(Protocol data) {
        String url = data.getCustomerProtocolUrl();
        Intent intent = new Intent(this, BaseWebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", "用户服务协议");
        startActivity(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter = new IRegisterPresenterImpl(this, this);
        mPresenter.isStartCountdown();//判断是否有倒计时情况
    }

    @Override
    protected void onStart() {
        super.onStart();
        LOGIN_TIMEOUT = false;
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(this);
    }

}
