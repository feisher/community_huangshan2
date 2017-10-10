package com.yusong.club.ui.charge.activity;

import android.annotation.TargetApi;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.charge.bean.SettingBean;
import com.yusong.club.ui.charge.mvp.implView.IChargeSettingView;
import com.yusong.club.ui.charge.mvp.presenter.impl.ICharegeSettingPresenterImpl;
import com.yusong.club.ui.charge.view.BaseDialog;
import com.yusong.club.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.yusong.club.R.id.iv_img;


/**
 * Created by Mr_Peng on 2017/1/4.
 */

public class HelpActivity extends BaseActivity implements IChargeSettingView {

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
    @InjectView(iv_img)
    ImageView ivImg;
    @InjectView(R.id.ll_img)
    LinearLayout llImg;
    @InjectView(R.id.tv_text)
    TextView tvText;
    @InjectView(R.id.rl_txt)
    RelativeLayout rlTxt;
    @InjectView(R.id.find_layout)
    RelativeLayout findLayout;
    @InjectView(R.id.help_web_view)
    WebView helpWebView;

    private String url = null;
    private String phoneNumber = null;
    private BaseDialog dialog;
    private ICharegeSettingPresenterImpl iChargeYuEPayPresenterimpl;

    @OnClick({R.id.ll_back, R.id.ll_img})
    void allHelpClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                this.finish();
                break;
            case R.id.ll_img:
                if (!TextUtils.isEmpty(phoneNumber)) {
                    showCallDialog();
                } else {
                    ToastUtils.showShort(getApplicationContext(), "客服电话未获取");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void initView() {
        tvTitle.setText("帮助");
        llImg.setVisibility(View.VISIBLE);
        ivImg.setBackgroundResource(R.mipmap.call_phone_2);
        inithelpWebView();
        iChargeYuEPayPresenterimpl = new ICharegeSettingPresenterImpl(this, HelpActivity.this);
        iChargeYuEPayPresenterimpl.querySettingPresenter();
    }

    private void inithelpWebView() {
        WebSettings webSetting = helpWebView.getSettings();
        webSetting.setBuiltInZoomControls(true);
        webSetting.setJavaScriptEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setDatabaseEnabled(true);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSetting.setAppCacheEnabled(true);
        helpWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

            }
        });
        helpWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                WebView.HitTestResult hitTestResult = view.getHitTestResult();
                if (!TextUtils.isEmpty(url) && hitTestResult == null) {
                    view.loadUrl(url);
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

    }

    @Override
    public void SuccedNext(SettingBean settingBean) {
        url = settingBean.getUrl();
        phoneNumber = settingBean.getTel();
        helpWebView.loadUrl(url);
    }

    private void showCallDialog() {
        super.phoneNumber = phoneNumber;
        dialog = new BaseDialog(this);
        dialog.setTitle("客服电话");
        dialog.setMessage("确定拨打:" + phoneNumber + "吗？");
        dialog.setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                callDirectly(phoneNumber);
            }
        });
        dialog.setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_charge_help;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (helpWebView != null) helpWebView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (helpWebView != null) helpWebView.onResume();
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
    protected void onDestroy() {
        super.onDestroy();
        if (helpWebView != null) {
            ViewGroup parent = (ViewGroup) helpWebView.getParent();
            if (parent != null) {
                parent.removeView(helpWebView);
            }
            helpWebView.removeAllViews();
            helpWebView.destroy();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (helpWebView.canGoBack()) {
                    helpWebView.goBack();
                } else {
                    finish();
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(this);
    }
}
