package com.yusong.community.ui.community.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseFragment;
import com.yusong.community.ui.community.activity.InputActivity;
import com.yusong.community.ui.community.adapter.CommunityItemFragmentAdapter;
import com.yusong.community.ui.community.event.CreatePostRefashEvent;
import com.yusong.community.ui.community.event.PostsCommentEvent;
import com.yusong.community.ui.community.hoder.CommunityItemFragmentHodler;
import com.yusong.community.ui.community.mvp.entity.Posts;
import com.yusong.community.ui.community.mvp.implView.ICommunityItemView;
import com.yusong.community.ui.community.mvp.presenter.impl.CommunityItemPresenterIpml;
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
 * create by feisher on 2017/1/10 10:12
 * Email：458079442@qq.com
 */
public class CommunityItemFragment extends BaseFragment implements ICommunityItemView,
        BGARefreshLayout.BGARefreshLayoutDelegate, CommunityItemFragmentHodler.ShowInputView {
    @InjectView(R.id.rl_community_item)
    RecyclerView rlCommunityItem;
    @InjectView(R.id.rl_community_item_refresh)
    BGARefreshLayout rlCommunityItemRefresh;
    public CommunityItemPresenterIpml mPresenter;
    public int catogreyId;
    public String catogreyName;
    public LinearLayoutManager mMLinearLayoutManager;
    public CommunityItemFragmentAdapter mAdapter;
    public boolean isRefresh;
    List<Posts> datas = new ArrayList<>();
    @InjectView(R.id.et_content)
    EditText etContent;
    @InjectView(R.id.btn_send)
    Button btnSend;
    @InjectView(R.id.comment_layout)
    LinearLayout commentLayout;
    private int postId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            catogreyId = bundle.getInt("catogreyId");
            catogreyName = bundle.getString("catogreyName");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public View initView() {
        return View.inflate(mContext, R.layout.fragment_communityitem, null);
    }

    @Override
    public void initData() {
        initRefreshLayout();
        mPresenter = new CommunityItemPresenterIpml(this, mContext);
        isRefresh = true;
        mPresenter.requestGetCommubityItemFragementItem(catogreyId, 0, 10);
        EventBus.getDefault().register(this);
    }

    /**
     * 接收到发送了评论就去刷新
     *
     * @param mPostsCommentEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void getCommentEvent(PostsCommentEvent mPostsCommentEvent) {
//        MyApplication.showMessage("接收到评论");
        isRefresh = true;
        mPresenter.requestGetCommubityItemFragementItem(catogreyId, 0, 10);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void getCreatePostEvent(CreatePostRefashEvent mCreatePostRefashEvent) {
//        MyApplication.showMessage("接收到发帖");
        isRefresh = true;
        mPresenter.requestGetCommubityItemFragementItem(mCreatePostRefashEvent.getCatogreyId(), 0, 10);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        ButterKnife.reset(this);
    }

    /**
     * 初始化刷新布局
     */
    private void initRefreshLayout() {
        rlCommunityItemRefresh.setDelegate(this);//设置代理
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(mContext, true);
        rlCommunityItemRefresh.setRefreshViewHolder(mBGARefreshViewHolder);
    }

    @Override
    public void initListener() {
    }

    public static CommunityItemFragment newInstance(String catogreyName, int catogreyId) {
        Bundle bundle = new Bundle();
        bundle.putInt("catogreyId", catogreyId);
        bundle.putString("catogreyName", catogreyName);
        CommunityItemFragment mCommunityItemFragment = new CommunityItemFragment();
        mCommunityItemFragment.setArguments(bundle);
        return mCommunityItemFragment;
    }

    @Override
    public void setFragmentItemAdapter(List<Posts> data) {

        if (isRefresh == true) {
            datas.clear();
            datas.addAll(data);
        } else {
            datas.addAll(data);
        }
        if (mAdapter == null) {
            mMLinearLayoutManager = new LinearLayoutManager(getActivity());
            mAdapter = new CommunityItemFragmentAdapter(datas, mContext, this);
            rlCommunityItem.setAdapter(mAdapter);
            rlCommunityItem.setLayoutManager(mMLinearLayoutManager);
            rlCommunityItemRefresh.setLinearLayoutManager(datas, mMLinearLayoutManager);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void postsCommentCallback() {
        mPresenter.requestGetCommubityItemFragementItem(catogreyId, 0, 10);
    }

    @Override
    public void commitLikePostsCallback() {
        mPresenter.requestGetCommubityItemFragementItem(catogreyId, 0, 10);
    }

    @Override
    public void closeRefreshing() {
        if (rlCommunityItemRefresh != null) {
            rlCommunityItemRefresh.endRefreshing();
            rlCommunityItemRefresh.endLoadingMore();
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(final BGARefreshLayout refreshLayout) {
        isRefresh = true;
        mPresenter.requestGetCommubityItemFragementItem(catogreyId, 0, 10);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(final BGARefreshLayout refreshLayout) {
        isRefresh = false;
        mPresenter.requestGetCommubityItemFragementItem(catogreyId, datas.size(), 10);
        return true;
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    private long oldTime;

    @Override
    public void showInput(int postId) {//评论触发
        long nowTime = System.currentTimeMillis();
        if (nowTime - oldTime > 1000) {
            oldTime = System.currentTimeMillis();
            Intent intent = new Intent(getActivity(), InputActivity.class);
            intent.putExtra("postId",postId);
            startActivity(intent);
        }
    }

//    /**
//     * 接受界面跳转
//     * @param activity
//     */
//    public static void launch(Activity activity) {
//        Router.newIntent()
//                .from(activity)
//                .to(MainActivity.class)
//                .data(new Bundle())
//                .launch();
//    }


}
