package com.yusong.community.ui.school.school.activity;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.charge.view.BaseDialog;
import com.yusong.community.ui.school.mvp.entity.ApplyRole;
import com.yusong.community.ui.school.mvp.implView.RoleApplyDetailActivityView;
import com.yusong.community.ui.school.mvp.presenter.impl.RoleApplyDetailActivityPresenterImpl;
import com.yusong.community.utils.AndroidBug5497Workaround;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.glide.GlideImgManager;

import butterknife.InjectView;
import butterknife.OnClick;

public class RoleApplyDetailActivity extends BaseActivity implements RoleApplyDetailActivityView {

    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_realname)
    TextView tvRealname;
    @InjectView(R.id.tv_tel)
    TextView tvTel;
    @InjectView(R.id.tv_apply_role)
    TextView tvApplyRole;
    @InjectView(R.id.tv_student_id)
    TextView tvStudentId;
    @InjectView(R.id.ll_student_id)
    LinearLayout llStudentId;
    @InjectView(R.id.tv_clazz)
    TextView tvClazz;
    @InjectView(R.id.tv_student_name)
    TextView tvStudentName;
    @InjectView(R.id.ll_student_name)
    LinearLayout llStudentName;
    @InjectView(R.id.tv_relation)
    TextView tvRelation;
    @InjectView(R.id.ll_relation)
    LinearLayout llRelation;
    @InjectView(R.id.iv_apply_role_icon)
    ImageView ivApplyRoleIcon;
    @InjectView(R.id.btn_pass_apply)
    Button btnPassApply;
    @InjectView(R.id.btn_refuse_apply)
    Button btnRefuseApply;
    @InjectView(R.id.photoview)
    PhotoView photoview;
    @InjectView(R.id.rl_allviews)
    RelativeLayout rlAllviews;

    public Info mInfo;
    public ApplyRole applyRole;
    @InjectView(R.id.tv_id_no)
    TextView tvIdNo;
    public Context mContext;
    @InjectView(R.id.et_audit_intor)
    EditText etAuditIntor;
    public RoleApplyDetailActivityPresenterImpl mPresenter;

    @Override
    protected int layoutId() {
        return R.layout.activity_role_apply_detail;
    }

    @Override
    public void initView() {
        mContext = RoleApplyDetailActivity.this;
        AndroidBug5497Workaround.assistActivity(this);
        llBack.setVisibility(View.VISIBLE);
        photoview.enable();//设置photoview启用缩放
        mPresenter = new RoleApplyDetailActivityPresenterImpl(this, this);
    }

    @Override
    public void initData() {
        applyRole = (ApplyRole) getIntent().getSerializableExtra("applyRole");
        if (applyRole != null) {
            if (applyRole.getType() == 1) {
                llStudentName.setVisibility(View.VISIBLE);
                llStudentId.setVisibility(View.VISIBLE);
                llRelation.setVisibility(View.VISIBLE);
                tvTitle.setText("家长申请");
                tvApplyRole.setText("家长");
                tvStudentName.setText(applyRole.getStduentName());
                tvStudentId.setText(applyRole.getStduentNo());
                tvRelation.setText(applyRole.getApplyRole());
            } else {
                llStudentName.setVisibility(View.GONE);
                llStudentId.setVisibility(View.GONE);
                llRelation.setVisibility(View.GONE);
                tvTitle.setText("老师申请");
                tvApplyRole.setText("老师");
            }
            tvRealname.setText(applyRole.getUserName());
            tvTel.setText(applyRole.getTel());
            tvClazz.setText(applyRole.getClazzName());
            tvIdNo.setText(applyRole.getIdCard());
            GlideImgManager.loadImage(mContext, applyRole.getPhotoPath(), ivApplyRoleIcon);
        }
    }

    @OnClick({R.id.ll_back, R.id.iv_apply_role_icon, R.id.btn_pass_apply, R.id.btn_refuse_apply, R.id.photoview})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.iv_apply_role_icon:
                if (applyRole != null) {
                    imageZom(ivApplyRoleIcon, applyRole.getPhotoPath());
                }
                break;
            case R.id.btn_pass_apply:
                final String auditIntor = etAuditIntor.getText().toString().trim();
                if (!TextUtils.isEmpty(auditIntor)) {
                    final BaseDialog baseDialog = new BaseDialog(mContext);
                    baseDialog.setTitle("提示").setMessage("请仔细核对申请信息,然后确认通过审核").setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mPresenter.auditRoleApply(CacheUtils.getToken(mContext),applyRole.getId(),auditIntor,2);
                            baseDialog.dismiss();
                        }
                    }).setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            baseDialog.dismiss();
                        }
                    });
                }else {
                    MyApplication.showMessage("审核说明内容为必填内容");
                }
                break;
            case R.id.btn_refuse_apply:
                String auditIntor1 = etAuditIntor.getText().toString().trim();
                if (!TextUtils.isEmpty(auditIntor1)) {
                    mPresenter.auditRoleApply(CacheUtils.getToken(mContext),applyRole.getId(),auditIntor1,3);
                }else {
                    MyApplication.showMessage("审核说明内容为必填内容");
                }
                break;
            case R.id.photoview:
                photoview.animaTo(mInfo, new Runnable() {
                    @Override
                    public void run() {
                        photoview.setVisibility(View.GONE);
                    }
                });
                break;
        }
    }

    private void imageZom(ImageView img, String url) {
        photoview.setVisibility(View.VISIBLE);
        mInfo = PhotoView.getImageViewInfo(img);
        GlideImgManager.loadImageFitCenter(this, url, photoview);
        photoview.animaFrom(mInfo);
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void auditRoleApplyCallback() {
        finish();
    }
}
