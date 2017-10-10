package com.yusong.club.ui.school.teacher.activity;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.school.mvp.entity.EditSucessResult;
import com.yusong.club.ui.school.mvp.implView.IEditPhotoActivityView;
import com.yusong.club.ui.school.mvp.presenter.impl.IEditPhotoActivityPresenterImpl;
import com.yusong.club.ui.school.teacher.bean.PhotoAlbum;
import com.yusong.club.utils.CacheUtils;

import butterknife.InjectView;

public class EditPhotoActivity extends BaseActivity implements View.OnClickListener, IEditPhotoActivityView {
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
    private IEditPhotoActivityPresenterImpl mPresenter;
    private PhotoAlbum photoAlbum;

    @Override
    protected int layoutId() {
        return R.layout.activity_edit_photo;
    }

    @Override
    public void initData() {
        mContext = EditPhotoActivity.this;
        if (getIntent() != null && getIntent().getSerializableExtra("photoAlbum") != null) {
            photoAlbum = (PhotoAlbum) getIntent().getSerializableExtra("photoAlbum");
            etTitle.setText(photoAlbum.getAlbumName());
            etContent.setText(photoAlbum.getMemo());
        }
        tvTitle.setText("编辑相册");
        mContext = EditPhotoActivity.this;
        tvSave.setOnClickListener(this);
        linearBack.setOnClickListener(this);
        mPresenter = new IEditPhotoActivityPresenterImpl(this, this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_back:
                finish();
            case R.id.tv_save:
                mPresenter.editPhoto(CacheUtils.getToken(mContext), photoAlbum.getId(), etContent.getText().toString(), etTitle.getText().toString());
                break;

        }
    }

    @Override
    public void editPhotoSucess(EditSucessResult data) {
        finish();
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);

    }
}
