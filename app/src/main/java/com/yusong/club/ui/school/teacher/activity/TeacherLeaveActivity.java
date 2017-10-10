package com.yusong.club.ui.school.teacher.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.school.mvp.entity.TeacherLeave;
import com.yusong.club.ui.school.mvp.implView.ITeacherLeaveActivityView;
import com.yusong.club.ui.school.mvp.presenter.impl.ITeacherLeaveActivityPresenterImpl;
import com.yusong.club.ui.school.teacher.adapter.TeacherLeaveActivityAdapter;
import com.yusong.club.ui.school.teacher.holder.TeacherLeaveActivityHolder;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.club.utils.bga.BGARefreshLayout;
import com.yusong.club.utils.bga.BGARefreshViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TeacherLeaveActivity extends BaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate, View.OnClickListener, ITeacherLeaveActivityView, TeacherLeaveActivityHolder.JudgeLeave {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.mBGA_layout)
    BGARefreshLayout mBGALayout;
    @InjectView(R.id.rv_List)
    RecyclerView rvList;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.iv_img)
    ImageView ivImg;
    @InjectView(R.id.ll_img)
    LinearLayout llImg;
    @InjectView(R.id.not_data_layout)
    RelativeLayout notDataLayout;
    private Context mContext;
    private TeacherLeaveActivityAdapter mAdapter;
    private ITeacherLeaveActivityPresenterImpl mPresenter;
    private boolean isRefresh = true;
    private List<TeacherLeave> datas;

    @Override
    protected int layoutId() {
        return R.layout.activity_teacher_leave;
    }

    /**
     * 初始化刷新布局
     */
    private void initRefreshLayout() {
        mBGALayout.setDelegate(this);//设置代理
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(mContext, true);
        mBGALayout.setRefreshViewHolder(mBGARefreshViewHolder);
    }

    @Override
    public void initData() {
        mContext = TeacherLeaveActivity.this;
        llImg.setVisibility(View.VISIBLE);
        ivImg.setImageResource(R.mipmap.teacher_date);
        ivImg.setOnClickListener(this);
        datas = new ArrayList();
        if (getIntent() != null && getIntent().getStringExtra("SchoolName") != null) {
            tvTitle.setText(getIntent().getStringExtra("SchoolName"));
        }
        llBack.setOnClickListener(this);
        LinearLayoutManager mMLinearLayoutManager;
        mMLinearLayoutManager = new LinearLayoutManager(mContext);
        mAdapter = new TeacherLeaveActivityAdapter(datas, mContext);
        mAdapter.setJudgeLeavejudgeLeave(this);
        rvList.setAdapter(mAdapter);
//            rvList.addItemDecoration(new SpaceItemDecoration(10, 0));
        rvList.setLayoutManager(mMLinearLayoutManager);
        mPresenter = new ITeacherLeaveActivityPresenterImpl(this, this);
        initRefreshLayout();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.queryLeaveAplly(CacheUtils.getToken(mContext), null, 0, 10);

    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        isRefresh = true;
        mPresenter.queryLeaveAplly(CacheUtils.getToken(mContext), null, 0, 10);

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        isRefresh = false;
        mPresenter.queryLeaveAplly(CacheUtils.getToken(mContext), null, datas.size(), 10);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.iv_img:
                startActivity(new Intent(mContext, ShowLeaveListActivity.class));
                break;
        }
    }


    @Override
    public void getTeacherLeave(List<TeacherLeave> data) {
        if (isRefresh) {
            if (data != null && data.size() != 0) {
                notDataLayout.setVisibility(View.GONE);
            } else {
                notDataLayout.setVisibility(View.VISIBLE);
            }
            datas.clear();
            datas.addAll(data);
        } else {
            notDataLayout.setVisibility(View.GONE);
            datas.addAll(data);
        }
        mAdapter.notifyDataSetChanged();


    }

    @Override
    public void closeRefresh() {
        if (mBGALayout != null) {
            mBGALayout.endRefreshing();
            mBGALayout.endLoadingMore();
        }

    }

    //服务器的回调
    @Override
    public void judgeServeLeave(String data) {
        mPresenter.queryLeaveAplly(CacheUtils.getToken(mContext), null, 10, 10);
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
    }

    //adapter回调

    @Override
    public void judgeLeaveYes(int pos) {
        showDialogAlert(pos, 2);
    }

    public void showDialogAlert(final int position, final int type) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("审批备注");
        builder.setView(new EditText(mContext));
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.judgeLeave(CacheUtils.getToken(mContext), datas.get(position).getId(), "", type);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    //adapter回调
    @Override
    public void judgeLeaveNo(int pos) {
        showDialogAlert(pos, 3);
    }
}
