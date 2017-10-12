package com.yusong.community.ui.school.teacher.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseFragment;
import com.yusong.community.ui.school.mvp.implView.ICorrelationFragmentView;
import com.yusong.community.ui.school.mvp.presenter.impl.ICorrelationFragmentPresenterImpl;
import com.yusong.community.ui.school.teacher.adapter.CorrelationAdapter;
import com.yusong.community.ui.school.teacher.bean.StudyVideo;
import com.yusong.community.ui.school.teacher.bean.VideoBean;
import com.yusong.community.ui.video.VideoPlayDetailActivity;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.community.utils.bga.BGARefreshLayout;
import com.yusong.community.utils.bga.BGARefreshViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 相关视频
 */
public class CorrelationFragment extends BaseFragment implements ICorrelationFragmentView, BGARefreshLayout.BGARefreshLayoutDelegate {
    @InjectView(R.id.mBGA_layout)
    BGARefreshLayout mBGALayout;
    private CorrelationAdapter adapter;
    private List<VideoBean> data;
    private List<StudyVideo> videoDatas;
    @InjectView(R.id.gv_video)
    GridView gvVideo;
    private boolean isRefresh = true;
    private ICorrelationFragmentPresenterImpl mPresenter;

    @Override
    public View initView() {

        return View.inflate(getActivity(), R.layout.fragment_teacher_correlation, null);
    }

    @Override
    public void initData() {
        data = new ArrayList<>();
        videoDatas = new ArrayList<>();
        mPresenter = new ICorrelationFragmentPresenterImpl(this, mContext);
        mPresenter.studyVideoList(CacheUtils.getToken(mContext), 0, 10);
        initRefreshLayout();
        adapter = new CorrelationAdapter(getActivity(), videoDatas);
        gvVideo.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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
    public void initListener() {
        gvVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), VideoPlayDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("videoUrl", videoDatas.get(i).getFilePath());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void getVideoAlbumList(List<StudyVideo> videoData) {
        if (isRefresh) {
            videoDatas.clear();
            videoDatas.addAll(videoData);
        } else {
            videoDatas.addAll(videoData);
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    public void closeRefreshing() {
        if (mBGALayout != null) {
            mBGALayout.endLoadingMore();
            mBGALayout.endRefreshing();
        }

    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);

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
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        isRefresh = true;
        mPresenter.studyVideoList(CacheUtils.getToken(mContext), 0, 10);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        isRefresh = false;
        mPresenter.studyVideoList(CacheUtils.getToken(mContext), videoDatas.size(), 10);
        return false;
    }
}
