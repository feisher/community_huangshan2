package com.yusong.club.ui.school.user.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.school.school.activity.ChooseActivity;

import butterknife.InjectView;
import butterknife.OnClick;

public class CreateRoleCheckResultActivity extends BaseActivity {

    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_check_time)
    TextView tvCheckTime;
    @InjectView(R.id.tv_refuse_reason)
    TextView tvRefuseReason;
    @InjectView(R.id.activity_create_role_check_result)
    LinearLayout activityCreateRoleCheckResult;
    @InjectView(R.id.btn_commit)
    Button btnCommit;
    public String auditMemo;
    public String auditTime;

    @Override
    protected int layoutId() {
        return R.layout.activity_create_role_check_result;
    }

    @Override
    public void initData() {
        tvTitle.setText("审核结果");
        llBack.setVisibility(View.VISIBLE);
        auditMemo = getIntent().getStringExtra("auditMemo");//不通过原因
        //不通过原因
        auditTime = getIntent().getStringExtra("auditTime");
        if (!TextUtils.isEmpty(auditMemo)) {
            tvRefuseReason.setText(auditMemo);
        }
        if (!TextUtils.isEmpty(auditTime)) {
            tvCheckTime.setText(auditTime);
        }

    }


    @OnClick({R.id.ll_back,R.id.btn_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.btn_commit:
                //　跳转到创建角色界面
                Intent intent = new Intent(this, ChooseActivity.class);
                intent.putExtra("type","chooseCreate");
                startActivity(intent);
                finish();
                break;
        }
    }

}
