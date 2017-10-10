package com.yusong.club.ui.school.teacher.fragment;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseFragment;
import com.yusong.club.ui.school.mvp.entity.UserInfoDetails;
import com.yusong.club.ui.school.mvp.implView.IParentMineFragmentView;
import com.yusong.club.ui.school.mvp.presenter.impl.IParentMineFragmentPresenterImpl;
import com.yusong.club.ui.school.school.activity.ChooseActivity;
import com.yusong.club.ui.school.user.AuditStatusEvent;
import com.yusong.club.ui.school.user.activity.CreateRoleCheckResultActivity;
import com.yusong.club.ui.school.user.activity.CreateRoleExplainActivity;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.SPUtils;
import com.yusong.club.utils.glide.GlideImgManager;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class ParentMineFragment extends BaseFragment implements IParentMineFragmentView {
    @InjectView(R.id.linear_back)
    LinearLayout linearBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.im_msg)
    ImageView imMsg;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
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
    public IParentMineFragmentPresenterImpl mPresenter;
    @InjectView(R.id.ll_mobile)
    LinearLayout llMobile;
    @InjectView(R.id.textView4)
    TextView textView4;
    @InjectView(R.id.tv_id_no)
    TextView tvIdNo;
    @InjectView(R.id.tv_student_name)
    TextView tvStudentName;
    @InjectView(R.id.ll_student_name)
    LinearLayout llStudentName;
    @InjectView(R.id.ll_duty)
    LinearLayout llDuty;
    @InjectView(R.id.ll_clazz)
    LinearLayout llClazz;
    private int id=0;

    @Override
    public View initView() {
        return View.inflate(mContext, R.layout.fragment_parent_mine, null);
    }

    @Override
    public void initData() {
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= 18) {
            ivAdaptiveDownApi18.setVisibility(View.VISIBLE);
        } else {
            ivAdaptiveDownApi18.setVisibility(View.GONE);
        }
        if (getArguments()!=null&&getArguments().getString("SchoolId")!=null){
            id=Integer.parseInt(getArguments().getString("SchoolId"));
        }
        mPresenter = new IParentMineFragmentPresenterImpl(this, mContext);
        mPresenter.queryParentInfo(CacheUtils.getToken(mContext), null);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void getParentInfo(UserInfoDetails data) {
        if (!TextUtils.isEmpty(data.getPortrait())) {
            GlideImgManager.loadCircleImage(mContext, data.getPortrait(), ivUserIcon);
        }
        if (!TextUtils.isEmpty(data.getRealName())) {
            tvUserName.setText(data.getRealName());
        }
        if (!TextUtils.isEmpty(data.getMobile())) {
            tvMobile.setText(data.getMobile());
        }
        if (!TextUtils.isEmpty(data.getStudentNo())) {
            tvIdNo.setText(data.getStudentNo());
        }
        if (!TextUtils.isEmpty(data.getStudentName())) {
            tvStudentName.setText(data.getStudentName());
        }
        if (!TextUtils.isEmpty(data.getSchoolName())) {
            tvDuty.setText(data.getSchoolName());
        }
        if (!TextUtils.isEmpty(data.getClazzName())) {
            tvClazz.setText(data.getClazzName());
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
