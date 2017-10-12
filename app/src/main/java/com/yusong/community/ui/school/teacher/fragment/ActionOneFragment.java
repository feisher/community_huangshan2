package com.yusong.community.ui.school.teacher.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseFragment;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.school.event.CreateActionEvent;
import com.yusong.community.ui.school.mvp.entity.GoodAction;
import com.yusong.community.ui.school.mvp.implView.IActionOneFragmentView;
import com.yusong.community.ui.school.mvp.presenter.impl.IActionOneFragmentPresenterImpl;
import com.yusong.community.ui.school.school.bean.ActivityBean;
import com.yusong.community.ui.school.school.bean.HuoType;
import com.yusong.community.ui.school.teacher.activity.ActionCommentActivity;
import com.yusong.community.ui.school.teacher.activity.ActionDetailActivity;
import com.yusong.community.ui.school.teacher.adapter.ActionOneAdapter;
import com.yusong.community.ui.school.teacher.holder.ActionHolder;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.ToastUtils;
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

/**
 * 班级活动
 */
public class ActionOneFragment extends BaseFragment implements IActionOneFragmentView, BGARefreshLayout.BGARefreshLayoutDelegate, ActionHolder.ActionDo {
    @InjectView(R.id.not_data_layout)
    RelativeLayout notDataLayout;
    private List<ActivityBean> datas;
    private ActionOneAdapter adapter;
    @InjectView(R.id.rv_action_one)
    RecyclerView rvActionOne;
    private IActionOneFragmentPresenterImpl mPresenter;
    @InjectView(R.id.mBGA_layout)
    BGARefreshLayout mBGALayout;
    private int mId = 0;
    public boolean isRefresh = true;
    private int ps = -1;
    private int SchoolId = 0;
    private int roleTag = 0;

    public static ActionOneFragment newInstance(HuoType huoType, String SchoolId, int roleTag) {
        Bundle args = new Bundle();
        ActionOneFragment fragment = new ActionOneFragment();
        args.putString("type", huoType.getCategoryName());
        args.putString("id", huoType.getId() + "");
        args.putString("SchoolId", SchoolId);
        args.putInt("roleTag", roleTag);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            if (getArguments().getString("id") != null) {
                mId = Integer.parseInt(getArguments().getString("id"));
            }
            if (getArguments().getString("SchoolId") != null) {
                SchoolId = Integer.parseInt(getArguments().getString("SchoolId"));
            }
            if (getArguments().getInt("roleTag", 0) != 0) {
                roleTag = getArguments().getInt("roleTag", 0);
            }
        }
    }

    @Override
    public View initView() {
        return View.inflate(getActivity(), R.layout.fragment_teacher_action_one, null);
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
        initRefreshLayout();
        datas = new ArrayList<>();
        mPresenter = new IActionOneFragmentPresenterImpl(this, mContext);
        rvActionOne.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new ActionOneAdapter(datas, mContext);
        rvActionOne.setAdapter(adapter);
        mPresenter.activityList(CacheUtils.getToken(mContext), mId, 0, 10);
    }

    @Override
    public void initListener() {
        adapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                Intent intent = new Intent(mContext, ActionDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("activityId", datas.get(position).getId());
                bundle.putInt("SchoolId", SchoolId);
                bundle.putInt("roleTag", roleTag);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        adapter.setmActionDo(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        EventBus.getDefault().register(this);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        ButterKnife.reset(this);
    }

    @Override
    public void getactivityList(List<ActivityBean> data) {
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
    public void getGoodAction(GoodAction data) {
        if (data.getSupportAmount() == 0) {
            ToastUtils.showShort(mContext, "取消点赞");
            datas.get(ps).setSupportAmount((Integer.parseInt(datas.get(ps).getSupportAmount()) - 1) + "");
        } else if (data.getSupportAmount() == 1) {
            datas.get(ps).setSupportAmount((Integer.parseInt(datas.get(ps).getSupportAmount()) + 1) + "");
            ToastUtils.showShort(mContext, "点赞+1");
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        isRefresh = true;
        mPresenter.activityList(CacheUtils.getToken(mContext), mId, 0, 10);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        isRefresh = false;
        mPresenter.activityList(CacheUtils.getToken(mContext), mId, datas.size(), 10);
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void getCreateActionEvent(CreateActionEvent mCreateActionEvent) {
        //接收到通知刷新列表
        ToastUtils.showShort(getContext(), "接收到发帖成功通知");
        isRefresh = true;
        mPresenter.activityList(CacheUtils.getToken(mContext), mId, 0, 10);
    }

    @Override
    public void giveGood(int pos) {
        mPresenter.getGoodAction(CacheUtils.getToken(mContext), datas.get(pos).getId());
        ps = pos;
    }

    @Override
    public void giveComment(int pos) {
        Intent intent = new Intent(mContext, ActionCommentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ActionId", datas.get(pos).getId());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
