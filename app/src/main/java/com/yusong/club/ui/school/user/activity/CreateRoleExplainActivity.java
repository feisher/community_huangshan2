package com.yusong.club.ui.school.user.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.api.BaseSubscriber;
import com.yusong.club.api.HttpResult;
import com.yusong.club.api.HttpUtil;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.utils.CacheUtils;

import butterknife.InjectView;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CreateRoleExplainActivity extends BaseActivity {

    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_text)
    TextView tvText;
    @InjectView(R.id.rl_txt)
    RelativeLayout rlTxt;
    public int applyId;
    @InjectView(R.id.btn_alert_audit)
    Button btnAlertAudit;

    @Override
    protected int layoutId() {
        return R.layout.activity_create_role_explain;
    }

    @Override
    public void initData() {
        llBack.setVisibility(View.VISIBLE);
        tvTitle.setText("申请说明");
        tvText.setText("知道啦");
        applyId = getIntent().getIntExtra("applyId",0);
    }

    @OnClick({R.id.ll_back, R.id.tv_text, R.id.rl_txt,R.id.btn_alert_audit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_text:
                finish();
                break;
            case R.id.rl_txt:
                finish();
                break;
            case R.id.btn_alert_audit:
                if (applyId>0) {
                    alertAudit(CacheUtils.getToken(this),applyId);
                }else {
                    MyApplication.showMessage("请耐心等待，一天后再发送提醒");
                    finish();
                }
                break;
        }
    }

    private void alertAudit(String token, int applyId) {
        Subscription subscription = HttpUtil.getInstance()
                .noticAudit(token,applyId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HttpResult<String>>() {
                    @Override
                    protected void onFailure(String err) {
                        MyApplication.showMessage("网络繁忙！");
                    }
                    @Override
                    protected void onSuccess(HttpResult<String> listHttpResult) {
                        if (listHttpResult.code==0) {
                         MyApplication.showMessage("提醒成功");
                            finish();
                        }else {
                            if (!TextUtils.isEmpty(listHttpResult.message)) {
                                MyApplication.showMessage(listHttpResult.message);
                            }
                        }
                    }
                });
        addSubcribe(subscription);
    }

}
