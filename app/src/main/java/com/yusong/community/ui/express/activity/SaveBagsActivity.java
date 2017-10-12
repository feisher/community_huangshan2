package com.yusong.community.ui.express.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.pay.CommonPayActivity;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.base.BaseWebViewActivity;
import com.yusong.community.ui.express.mvp.entity.CommitOrderResult;
import com.yusong.community.ui.express.mvp.entity.DisclaimerBean;
import com.yusong.community.ui.express.mvp.implView.ISaveBagsView;
import com.yusong.community.ui.express.mvp.presenter.impl.ISaveBagsPresenterImpl;
import com.yusong.community.utils.ActivityConstants;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * <ul>
 * <li>功能说明：存包界面</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */
public class SaveBagsActivity extends BaseActivity implements ISaveBagsView {
    @InjectView(R.id.ll_back)
    LinearLayout mLlBack;
    @InjectView(R.id.tv_title)
    TextView mTvTitle;
    @InjectView(R.id.et_mome)
    EditText mEtMome;
    @InjectView(R.id.tv_count)
    TextView mTvCount;
    @InjectView(R.id.et_phone)
    EditText mEtPhone;
    @InjectView(R.id.rl_edt)
    RelativeLayout mRlEdt;
    @InjectView(R.id.btn_commit)
    Button mBtnCommit;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView mIvAdaptiveDownApi18;
    @InjectView(R.id.left_radio_address)
    RadioButton leftRadioAddress;
    @InjectView(R.id.check_image)
    ImageView checkImage;
    @InjectView(R.id.statement_tv)
    TextView statementTv;
    @InjectView(R.id.check_layout)
    LinearLayout checkLayout;
    private ISaveBagsPresenterImpl mPresenter;

    private boolean isAgree = false;
    /**
     * 监听输入
     */
    private TextWatcher watcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mTvCount.setText(s.length() + "/100个字");
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void initListener() {
        mEtMome.addTextChangedListener(watcher);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_express_savalv;
    }

    @Override
    public void initView() {
        mTvTitle.setText("临时寄存");
    }

    @Override
    public void initData() {
        mPresenter = new ISaveBagsPresenterImpl(this, this);
    }

    @OnClick({R.id.ll_back, R.id.rl_edt, R.id.btn_commit, R.id.check_layout, R.id.statement_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.rl_edt:
                mEtMome.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                break;
            case R.id.btn_commit:
                if (!isAgree) {
                    return;
                }
                mPresenter.commitOrder(mEtPhone, mEtMome);
                break;
            case R.id.check_layout:
                if (!isAgree) {
                    isAgree = true;
                    checkImage.setImageResource(R.mipmap.selected);
                } else {
                    isAgree = false;
                    checkImage.setImageResource(R.mipmap.not_select);
                }
                break;
            case R.id.statement_tv:
                mPresenter.queryDisclaimer();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    /**
     * 提交成功 跳转提交成功界面
     */
    @Override
    public void commitSuccess(CommitOrderResult data) {
        int charge = data.getCharge();
        String authCode = data.getAuthCode();
        String order_id = data.getId();
        if (charge == 0) {
            Intent mIntent = new Intent(this, CommitSuccessActivity.class);
            mIntent.putExtra(ActivityConstants.AUTH_CODE, authCode);
            mIntent.putExtra(ActivityConstants.MSG, "请前往快递柜输入存包码完成存包！");
            startActivity(mIntent);
            finish();
        } else {
            Intent mIntent = new Intent(this, CommonPayActivity.class);
            mIntent.putExtra(ActivityConstants.CHARGE, charge / 100 + "");
            mIntent.putExtra(ActivityConstants.COMMON_PAYTYPE, ActivityConstants.SAVE_ORDER);
            mIntent.putExtra(ActivityConstants.ORDER_ID, order_id);
            startActivity(mIntent);
        }
    }

    @Override
    public void queryUrlSuccess(DisclaimerBean bean) {
        Intent intent = new Intent(this, BaseWebViewActivity.class);
        intent.putExtra("url", bean.getDisclaimerUrl());
        intent.putExtra("title", "免责声明");
        startActivity(intent);
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
}
