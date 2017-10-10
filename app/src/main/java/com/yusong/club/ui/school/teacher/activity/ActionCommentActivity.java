package com.yusong.club.ui.school.teacher.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.school.mvp.implView.IActionCommentActivityView;
import com.yusong.club.ui.school.mvp.presenter.impl.IActionCommentActivityPresenterImpl;
import com.yusong.club.ui.school.school.bean.Comment;
import com.yusong.club.ui.school.teacher.adapter.ActionCommentAdapter;
import com.yusong.club.utils.AndroidBug5497Workaround;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.ToastUtils;
import com.yusong.club.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.club.utils.bga.BGARefreshLayout;
import com.yusong.club.utils.bga.BGARefreshViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ActionCommentActivity extends BaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate, View.OnClickListener, IActionCommentActivityView {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.linear_back)
    LinearLayout linearBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.im_msg)
    ImageView imMsg;
    @InjectView(R.id.rv_action_comment)
    RecyclerView rvActionComment;
    @InjectView(R.id.mBGA_layout)
    BGARefreshLayout mBGALayout;
    @InjectView(R.id.et_content)
    EditText etContent;
    @InjectView(R.id.btn_send)
    Button btnSend;
    private Context mContext;
    private boolean isRefresh = true;
    private ActionCommentAdapter adapter;
    private List<Comment> datas;
    private IActionCommentActivityPresenterImpl mPresenter;
    private int id = -1;

    @Override
    protected int layoutId() {
        return R.layout.activity_action_comment;
    }

    @Override
    public void initData() {
        AndroidBug5497Workaround.assistActivity(this);
        mContext = ActionCommentActivity.this;
        datas = new ArrayList<>();
        if (getIntent() != null && getIntent().getIntExtra("ActionId", -1) != -1) {
            id = getIntent().getIntExtra("ActionId", -1);
        }

        initRefreshLayout();
        imMsg.setVisibility(View.GONE);
        tvTitle.setText("活动评论");
        linearBack.setOnClickListener(this);
        btnSend.setOnClickListener(this);
        mPresenter = new IActionCommentActivityPresenterImpl(this, this);
        rvActionComment.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new ActionCommentAdapter(datas, mContext);
        rvActionComment.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mPresenter.searchCommentList(CacheUtils.getToken(mContext), id, 0, 10);
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
        ButterKnife.inject(this);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        isRefresh = true;
        mPresenter.searchCommentList(CacheUtils.getToken(mContext), id, 0, 10);

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        isRefresh = false;
        mPresenter.searchCommentList(CacheUtils.getToken(mContext), id, datas.size(), 10);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_back:
                finish();
                break;
            case R.id.btn_send:
                mPresenter.createComment(CacheUtils.getToken(mContext), id, etContent.getText().toString());
                break;
        }
    }

    @Override
    public void getCommentList(List<Comment> data) {
        if (isRefresh == true) {
            datas.clear();
            datas.addAll(data);
        } else {
            datas.addAll(data);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void closeRefreshing() {
        if (mBGALayout != null) {
            mBGALayout.endRefreshing();
            mBGALayout.endLoadingMore();
        }

    }

    @Override
    public void createComment(String data) {
        ToastUtils.show(mContext, "发表成功", Toast.LENGTH_LONG);
        etContent.setText("");
        mPresenter.searchCommentList(CacheUtils.getToken(mContext), id, 0, 10);
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);

    }
}
