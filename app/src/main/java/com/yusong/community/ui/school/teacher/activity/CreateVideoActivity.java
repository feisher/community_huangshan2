package com.yusong.community.ui.school.teacher.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.school.mvp.implView.ICreateVideoActivityView;
import com.yusong.community.ui.school.mvp.presenter.impl.ICreateVideoActivityPresenterImpl;
import com.yusong.community.ui.school.teacher.bean.Result;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CreateVideoActivity extends BaseActivity implements View.OnClickListener, ICreateVideoActivityView {
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_save)
    TextView tvSave;
    @InjectView(R.id.linear_back)
    LinearLayout linearBack;
    @InjectView(R.id.feedback_et_content)
    EditText feedbackEtContent;
    @InjectView(R.id.et_name)
    EditText etName;
    private Context mContext;
    private ICreateVideoActivityPresenterImpl mPresenter;

    @Override
    protected int layoutId() {
        return R.layout.activity_create_video;
    }

    @Override
    public void initData() {
        tvTitle.setText("新建视频");
        mContext = CreateVideoActivity.this;
        linearBack.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        mPresenter = new ICreateVideoActivityPresenterImpl(this, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_save:
                mPresenter.createVideo_album(CacheUtils.getToken(mContext), etName.getText().toString(), feedbackEtContent.getText().toString());
                break;
            case R.id.linear_back:
                finish();
                break;
        }


    }

    @Override
    public void createVideoAlbum(Result data) {
        ToastUtils.show(mContext, "创建成功", Toast.LENGTH_LONG);
        finish();
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);
    }
}
