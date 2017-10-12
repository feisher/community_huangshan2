package com.yusong.community.ui.school.school.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.school.event.ReadNoticeEvent;
import com.yusong.community.ui.school.mvp.entity.Notice;
import com.yusong.community.ui.school.mvp.implView.INoticeActivityView;
import com.yusong.community.ui.school.mvp.presenter.impl.NoticeActivityPresenterImpl;
import com.yusong.community.ui.school.school.adapter.NoticeActivityAdapter;
import com.yusong.community.utils.ActivityConstants;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.community.utils.bga.BGARefreshLayout;
import com.yusong.community.utils.bga.BGARefreshViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 校园公告
 */
public class NoticeActivity extends BaseActivity implements INoticeActivityView, View.OnClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.iv_img)
    ImageView ivImg;
    @InjectView(R.id.ll_img)
    LinearLayout llImg;
    @InjectView(R.id.tv_text)
    TextView tvText;
    @InjectView(R.id.rl_txt)
    RelativeLayout rlTxt;
    @InjectView(R.id.rv_List)
    RecyclerView rvList;
    @InjectView(R.id.mBGA_layout)
    BGARefreshLayout mBGALayout;
    public Context mContext;
    public NoticeActivityPresenterImpl mPresenter;

    List<Notice> noticeDatas = new ArrayList<>();
    public boolean isRefresh;
    public LinearLayoutManager mMLinearLayoutManager;
    public NoticeActivityAdapter mAdapter;
    @InjectView(R.id.not_data_layout)
    RelativeLayout notDataLayout;
    private int isDelete;


    @Override
    protected void initListener() {
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_school_notice;
    }

    @Override
    public void initView() {
        mContext = NoticeActivity.this;
        llBack.setVisibility(View.VISIBLE);
        tvTitle.setText("公告");
        initRefreshLayout();
        mPresenter = new NoticeActivityPresenterImpl(this, this);
        EventBus.getDefault().register(this);
        if (getIntent() != null) {
            switch (Integer.parseInt(getIntent().getStringExtra("roleType"))) {
                case 1:
                    isDelete = 0;
                    llImg.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    isDelete = 1;
                    llImg.setVisibility(View.GONE);
                    break;
                case 3:
                    isDelete = 1;
                    llImg.setVisibility(View.GONE);
                    break;
            }
        }
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
        getListDeta();
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void getCreatePostEvent(ReadNoticeEvent mReadNoticeEvent) {
//        MyApplication.showMessage("接收到发帖");
        isRefresh = true;
        getListDeta();
    }

    private void getListDeta() {
        if (getIntent() != null) {
            switch (Integer.parseInt(getIntent().getStringExtra("roleType"))) {
                case 1:
                    mPresenter.queryPublicNoticeList(CacheUtils.getToken(mContext), 0, 10, isRefresh);
                    break;
                case 2:
                    mPresenter.queryTeacherNoticeList(CacheUtils.getToken(mContext), 0, 10);
                    break;
                case 3:
                    mPresenter.queryGuardianNoticeList(CacheUtils.getToken(mContext), 0, 10);
                    break;
            }
        }
    }

    @OnClick({R.id.ll_back, R.id.ll_img})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_img:
                Intent intent = new Intent(this, AddNoticeActivity.class);
                startActivityForResult(intent, ActivityConstants.NOTITY_CONGIRM);
                break;
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        isRefresh = true;
        getListDeta();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        isRefresh = false;
        if (getIntent() != null) {
            switch (Integer.parseInt(getIntent().getStringExtra("roleType"))) {
                case 1:
                    mPresenter.queryPublicNoticeList(CacheUtils.getToken(mContext), noticeDatas.size(), 10, isRefresh);
                    break;
                case 2:
                    mPresenter.queryTeacherNoticeList(CacheUtils.getToken(mContext), noticeDatas.size(), 10);
                    break;
                case 3:
                    mPresenter.queryGuardianNoticeList(CacheUtils.getToken(mContext), noticeDatas.size(), 10);
                    break;
            }
        }

        return true;
    }

    @Override
    public void closeRefreshing() {
        if (mBGALayout != null) {
            mBGALayout.endRefreshing();
            mBGALayout.endLoadingMore();
        }
    }

    @Override
    public void refreshList(int position) {
        noticeDatas.remove(position);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void noticeDataCallback(List<Notice> data) {
        if (isRefresh){
            if (  data != null&&data.size()!=0){
                notDataLayout.setVisibility(View.GONE);
            }else {
                notDataLayout.setVisibility(View.VISIBLE);
            }
        }else {
            notDataLayout.setVisibility(View.GONE);
        }

        showData(data);

    }

    public void showData(List<Notice> data) {
        if (isRefresh == true) {
            noticeDatas.clear();
            noticeDatas.addAll(data);
        } else {
            noticeDatas.addAll(data);
        }
        if (mAdapter == null) {
            mMLinearLayoutManager = new LinearLayoutManager(mContext);
            mAdapter = new NoticeActivityAdapter(noticeDatas, mContext);
            mAdapter.setIsDelete(isDelete);
            rvList.setAdapter(mAdapter);
//            rvList.addItemDecoration(new SpaceItemDecoration(1, 0));
            rvList.setLayoutManager(mMLinearLayoutManager);
            mBGALayout.setLinearLayoutManager(noticeDatas, mMLinearLayoutManager);
            mAdapter.notifyDataSetChanged();
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 删除公告条目方法
     *
     * @param id
     * @param position
     */
    public void deleteNoticeItem(int id, int position) {
        mPresenter.deleteNotice(CacheUtils.getToken(mContext), id, position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == ActivityConstants.NOTITY_CONGIRM) {
            isRefresh = true;
            getListDeta();
        }
    }

    @Override
    public void noticeTeacherDataCallback(List<Notice> data) {
        showData(data);
    }

    @Override
    public void noticeParentDataCallback(List<Notice> data) {
        showData(data);
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
