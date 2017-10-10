package com.yusong.club.ui.community_notice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.community_notice.adapter.NoticeAdapter;
import com.yusong.club.ui.community_notice.entity.NoticeBean;
import com.yusong.club.ui.community_notice.mvp.presenter.impl.CommunityNoticePresenterImpl;
import com.yusong.club.ui.community_notice.mvp.implview.CommunityNoticeView;
import com.yusong.club.utils.SpaceItemDecoration;
import com.yusong.club.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.club.utils.bga.BGARefreshLayout;
import com.yusong.club.utils.bga.BGARefreshViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Mr_Peng
 * @created at 2017-08-23.
 * @describe: 小区公告
 */

public class NoticeActivity extends BaseActivity implements CommunityNoticeView, BGARefreshLayout.BGARefreshLayoutDelegate {
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.notice_recyclerView)
    RecyclerView noticeRecyclerView;
    @InjectView(R.id.notice_refresh_layout)
    BGARefreshLayout noticeRefreshLayout;

    private List<NoticeBean> beanList = new ArrayList<NoticeBean>();
    private CommunityNoticePresenterImpl noticePresenter;
    private NoticeAdapter adapter;
    private boolean isClear = true;

    @OnClick(R.id.ll_back)
    void backClick() {
        this.finish();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_community_notice;
    }

    @Override
    public void initView() {
        tvTitle.setText(R.string.xiaoqu_gonggao);
        initRefreshLayout();
        adapter = new NoticeAdapter(beanList, this);
        noticeRecyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return true;
            }
        };
        noticeRecyclerView.setLayoutManager(layoutManager);
        noticeRecyclerView.addItemDecoration(new SpaceItemDecoration(18, 0));
        noticeRefreshLayout.setLinearLayoutManager(beanList, layoutManager);
        noticePresenter = new CommunityNoticePresenterImpl(this, this);
        adapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                Intent intent = new Intent(NoticeActivity.this, NoticeDetailsActivity.class);
                intent.putExtra("NoticeBean", beanList.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        noticePresenter.queryNotic(0, 10);
        super.onStart();
    }

    @Override
    public void initData() {

    }

    private void initRefreshLayout() {
        //设置代理
        noticeRefreshLayout.setDelegate(this);
        //设置刷新风格
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(this, true);
        noticeRefreshLayout.setRefreshViewHolder(mBGARefreshViewHolder);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void querySucced(List<NoticeBean> data) {
        if (isClear) {
            if (beanList.size() > 0) {
                beanList.clear();
            }
        }
        beanList.addAll(data);
        adapter.notifyDataSetChanged();
        noticeRefreshLayout.endLoadingMore();
        noticeRefreshLayout.endRefreshing();
    }

    @Override
    public void queryError() {
        noticeRefreshLayout.endLoadingMore();
        noticeRefreshLayout.endRefreshing();
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        isClear = true;
        noticePresenter.queryNotic(0, 10);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        isClear = false;
        noticePresenter.queryNotic(beanList.size(), 10);
        return true;
    }
}
