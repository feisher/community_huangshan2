package com.yusong.community.ui.school.teacher.activity;

import android.content.Context;
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

import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.school.mvp.entity.StuList;
import com.yusong.community.ui.school.mvp.implView.IChooseStudentActivityView;
import com.yusong.community.ui.school.mvp.presenter.impl.IChooseStudentActivityPresenterImpl;
import com.yusong.community.ui.school.teacher.adapter.ChooseStudentAdapter;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.community.utils.bga.BGARefreshLayout;
import com.yusong.community.utils.bga.BGARefreshViewHolder;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ChooseStudentActivity extends BaseActivity implements View.OnClickListener, BGARefreshLayout.BGARefreshLayoutDelegate, IChooseStudentActivityView {
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.rv_List)
    RecyclerView rvList;
    @InjectView(R.id.mBGA_layout)
    BGARefreshLayout mBGALayout;
    public Context mContext;
    List<StuList> noticeDatas = new ArrayList<>();
    public LinearLayoutManager mMLinearLayoutManager;
    public ChooseStudentAdapter mAdapter;
    @InjectView(R.id.iv_search)
    ImageView ivSearch;
    @InjectView(R.id.main_et_seach)
    EditText mainEtSeach;
    @InjectView(R.id.not_data_layout)
    RelativeLayout notDataLayout;
    private IChooseStudentActivityPresenterImpl mPresenter;
    private boolean isRefresh = true;

    @Override
    protected int layoutId() {
        return R.layout.activity_choose_student;
    }

    @Override
    protected void initListener() {
        super.initListener();


    }

    @Override
    public void initData() {
        mContext = ChooseStudentActivity.this;
        llBack.setOnClickListener(this);
        tvTitle.setText("选择学生");
        ivSearch.setOnClickListener(this);
        initRefreshLayout();
        mMLinearLayoutManager = new LinearLayoutManager(mContext);
        mMLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mAdapter = new ChooseStudentAdapter(noticeDatas, mContext);
        rvList.setAdapter(mAdapter);
        rvList.setLayoutManager(mMLinearLayoutManager);
        mPresenter = new IChooseStudentActivityPresenterImpl(this, this);
        mPresenter.ueryStuList(CacheUtils.getToken(mContext), "", 0, 10);
        mAdapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("studentId", noticeDatas.get(position).getId());
                bundle.putString("studentName", noticeDatas.get(position).getStudentName());
                intent.putExtras(bundle);
                setResult(0, intent);
                finish();
            }
        });
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.iv_search:
                isRefresh = true;
                if (StringUtils.isEmpty(mainEtSeach.getText().toString())) {
                    mPresenter.ueryStuList(CacheUtils.getToken(mContext), null, 0, 10);
                } else {
                    mPresenter.ueryStuList(CacheUtils.getToken(mContext), mainEtSeach.getText().toString(), 0, 10);
                }
                break;
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        isRefresh = true;
        mPresenter.ueryStuList(CacheUtils.getToken(mContext), mainEtSeach.getText().toString(), 0, 10);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        isRefresh = false;
        mPresenter.ueryStuList(CacheUtils.getToken(mContext), mainEtSeach.getText().toString(), noticeDatas.size(), 10);

        return true;
    }

    @Override
    public void getStuList(List<StuList> data) {
        if (isRefresh) {
            if (data != null && data.size() != 0) {
                notDataLayout.setVisibility(View.GONE);
            } else {
                notDataLayout.setVisibility(View.VISIBLE);
            }
            noticeDatas.clear();
            noticeDatas.addAll(data);
        } else {
            notDataLayout.setVisibility(View.GONE);
            noticeDatas.addAll(data);
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

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);
    }
}
