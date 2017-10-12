package com.yusong.community.ui.school.teacher.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseFragment;
import com.yusong.community.ui.school.mvp.implView.IFirstItemFragmentView;
import com.yusong.community.ui.school.mvp.presenter.impl.IFirstItemFragmentPresenterImpl;
import com.yusong.community.ui.school.school.bean.StudentComment;
import com.yusong.community.ui.school.teacher.adapter.EvaluateAdapter;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.community.utils.bga.BGARefreshLayout;
import com.yusong.community.utils.bga.BGARefreshViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstItemFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate, IFirstItemFragmentView {

    @InjectView(R.id.rv_evaluate)
    RecyclerView rvEvaluate;
    @InjectView(R.id.mBGA_layout)
    BGARefreshLayout mBGALayout;
    @InjectView(R.id.not_data_layout)
    RelativeLayout notDataLayout;
    private int mId = -1;
    private IFirstItemFragmentPresenterImpl mPresenter;
    private List<StudentComment> evaluateLists;
    private boolean isRefresh = true;
    private EvaluateAdapter mAdapter;


    public static FirstItemFragment newInstance(int type) {
        Bundle args = new Bundle();
        FirstItemFragment fragment = new FirstItemFragment();
        args.putString("id", type + "");
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new IFirstItemFragmentPresenterImpl(this, mContext);
        if (getArguments() != null) {
            if (getArguments().getString("id") != null) {
                mId = Integer.parseInt(getArguments().getString("id"));
            }
        }
    }

    @Override
    public View initView() {
        return View.inflate(getActivity(), R.layout.fragment_first_item, null);

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getStuCommentList(CacheUtils.getToken(mContext), mId, 0, 10);
    }

    @Override
    public void initData() {
        evaluateLists = new ArrayList<>();
        initRefreshLayout();
        LinearLayoutManager mMLinearLayoutManager;
        mMLinearLayoutManager = new LinearLayoutManager(mContext);
        mAdapter = new EvaluateAdapter(evaluateLists, mContext);
        rvEvaluate.setAdapter(mAdapter);
//            rvList.addItemDecoration(new SpaceItemDecoration(10, 0));
        rvEvaluate.setLayoutManager(mMLinearLayoutManager);
        mBGALayout.setLinearLayoutManager(evaluateLists,mMLinearLayoutManager);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        isRefresh = true;
        mPresenter.getStuCommentList(CacheUtils.getToken(mContext), mId, 0, 10);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        isRefresh = false;
        mPresenter.getStuCommentList(CacheUtils.getToken(mContext), mId, evaluateLists.size(), 10);
        return true;
    }


    @Override
    public void getStuComment(List<StudentComment> data) {
        if (isRefresh) {
            if (data != null && data.size() != 0) {
                evaluateLists.clear();
                evaluateLists.addAll(data);
                notDataLayout.setVisibility(View.GONE);
            } else {
                notDataLayout.setVisibility(View.VISIBLE);
            }
        } else {
            evaluateLists.addAll(data);
            notDataLayout.setVisibility(View.GONE);
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
