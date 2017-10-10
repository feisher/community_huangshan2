package com.yusong.club.ui.school.teacher.fragment;


import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseFragment;
import com.yusong.club.ui.school.mvp.entity.TeacherInfo;
import com.yusong.club.ui.school.mvp.implView.ITeacherMineFragmentaView;
import com.yusong.club.ui.school.mvp.presenter.impl.ITeacherMineFragmentaPresenterImpl;
import com.yusong.club.ui.school.school.activity.ChooseActivity;
import com.yusong.club.ui.school.user.AuditStatusEvent;
import com.yusong.club.ui.school.user.activity.CreateRoleCheckResultActivity;
import com.yusong.club.ui.school.user.activity.CreateRoleExplainActivity;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.SPUtils;
import com.yusong.club.utils.glide.GlideImgManager;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherMineFragment extends BaseFragment implements ITeacherMineFragmentaView {
    @InjectView(R.id.im_back)
    ImageView imBack;
    @InjectView(R.id.tv_back)
    TextView tvBack;
    @InjectView(R.id.linear_back)
    LinearLayout linearBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.im_msg)
    ImageView imMsg;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.ll_apply_other_role)
    LinearLayout llApplyOtherRole;
    public int auditStatus;
    public String auditMemo;
    public int applyId;
    public String auditTime;
    public AuditStatusEvent mAuditStatusEvent;
    @InjectView(R.id.iv_user_icon)
    ImageView ivUserIcon;
    @InjectView(R.id.tv_user_name)
    TextView tvUserName;
    @InjectView(R.id.tv_mobile)
    TextView tvMobile;
    @InjectView(R.id.ll_idno)
    LinearLayout llIdno;
    @InjectView(R.id.tv_duty)
    TextView tvDuty;
    @InjectView(R.id.tv_clazz)
    TextView tvClazz;
    private int id = 0;
    public ITeacherMineFragmentaPresenterImpl mPresenter;

    @Override
    public View initView() {
        return View.inflate(mContext, R.layout.fragment_teacher_mine, null);
    }



    @Override
    public void initData() {
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= 18) {
            ivAdaptiveDownApi18.setVisibility(View.VISIBLE);
        } else {
            ivAdaptiveDownApi18.setVisibility(View.GONE);
        }
        if (getArguments() != null && getArguments().getString("SchoolId") != null) {
            id = Integer.parseInt(getArguments().getString("SchoolId"));
        }
        mPresenter = new ITeacherMineFragmentaPresenterImpl(this, mContext);
        mPresenter.queryTeacherInfo(CacheUtils.getToken(mContext), null);
        linearBack.setVisibility(View.GONE);
        tvTitle.setText("我的");
        imMsg.setVisibility(View.GONE);
        llIdno.setVisibility(View.GONE);
        mAuditStatusEvent = (AuditStatusEvent) SPUtils.readObject(mContext, "AuditStatusEvent");
        if (mAuditStatusEvent != null) {
            auditStatus = mAuditStatusEvent.getAuditStatus();
            auditMemo = mAuditStatusEvent.getAuditMemo();
            applyId = mAuditStatusEvent.getApplyId();
            auditTime = mAuditStatusEvent.getAuditTime();
        }
    }

    @Override
    public void getTeacherIn(TeacherInfo data) {
        if (!TextUtils.isEmpty(data.getPortrait())) {
            GlideImgManager.loadCircleImage(mContext, data.getPortrait(), ivUserIcon);
        }
        if (!TextUtils.isEmpty(data.getTeacherName())) {
            tvUserName.setText(data.getTeacherName());
        }
        if (!TextUtils.isEmpty(data.getMobile())) {
            tvMobile.setText(data.getMobile());
        }
        if (!TextUtils.isEmpty(data.getCourerName())) {
            tvClazz.setText(data.getCourerName());
        }
        if (!TextUtils.isEmpty(data.getJob())) {
            tvDuty.setText(data.getJob());
        }

    }

    @OnClick({R.id.ll_apply_other_role, R.id.ll_mobile, R.id.ll_idno, R.id.ll_duty, R.id.ll_clazz})
    public void onClick(View view) {
        Intent mIntent = null;
        switch (view.getId()) {
            case R.id.ll_apply_other_role:
                if (auditStatus == 1) {//待审核
                    mIntent = new Intent(mContext, CreateRoleExplainActivity.class);
                    mIntent.putExtra("applyId", applyId);
                } else if (auditStatus == 3) {//审核不通过
                    mIntent = new Intent(mContext, CreateRoleCheckResultActivity.class);
                    mIntent.putExtra("auditMemo", auditMemo);//不通过原因
                    mIntent.putExtra("auditTime", auditTime);//审核时间
                } else if (auditStatus == 0) {// == 0 的情况，2的情况不存在，通过审核了那就必然有角色
                    mIntent = new Intent(mContext, ChooseActivity.class);
                    mIntent.putExtra("type", "chooseCreate");
                }
                startActivity(mIntent);
                break;
            case R.id.ll_mobile:
                break;
            case R.id.ll_idno:
                break;
            case R.id.ll_duty:
                break;
            case R.id.ll_clazz:
                break;
        }
    }

    @Override
    public void showProgressDialog() {

        MyApplication.showProgressDialog(mContext);

    }
}
