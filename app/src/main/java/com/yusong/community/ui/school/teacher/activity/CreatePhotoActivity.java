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
import com.yusong.community.ui.school.mvp.implView.ICreatePhotoActivityView;
import com.yusong.community.ui.school.mvp.presenter.impl.ICreatePhotoActivityPresenterImpl;
import com.yusong.community.ui.school.teacher.bean.Result;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CreatePhotoActivity extends BaseActivity implements View.OnClickListener, ICreatePhotoActivityView {
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_save)
    TextView tvSave;
    @InjectView(R.id.linear_back)
    LinearLayout linearBack;
    @InjectView(R.id.et_title)
    EditText etTitle;
    @InjectView(R.id.et_content)
    EditText etContent;
    private Context mContext;
    private ICreatePhotoActivityPresenterImpl mPresenter;

    @Override
    protected int layoutId() {
        return R.layout.activity_create_photo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
    }

    @Override
    public void initData() {
        tvTitle.setText("新建相册");
        mContext = CreatePhotoActivity.this;
        mPresenter = new ICreatePhotoActivityPresenterImpl(this, this);
        tvSave.setOnClickListener(this);
        linearBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_save:
                mPresenter.createPhoto_album(CacheUtils.getToken(mContext), etTitle.getText().toString() , etContent.getText().toString());
                break;
            case R.id.linear_back:
                finish();
                break;

        }

    }

    @Override
    public void createPhotoAlbum(Result data) {
        ToastUtils.show(mContext, "新建成功", Toast.LENGTH_LONG);
        finish();

    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);

    }
}
