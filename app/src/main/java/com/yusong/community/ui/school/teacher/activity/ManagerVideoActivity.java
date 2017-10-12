package com.yusong.community.ui.school.teacher.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.school.mvp.implView.IManagerVideoActivityView;
import com.yusong.community.ui.school.mvp.presenter.impl.IManagerVideoActivityPresenterImpl;
import com.yusong.community.ui.school.teacher.adapter.ManagerVideoAdapter;
import com.yusong.community.ui.school.teacher.bean.VideoAlbum;
import com.yusong.community.ui.school.teacher.view.MyGridView;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.DateUtil;
import com.yusong.community.utils.ToastUtils;
import com.yusong.community.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.community.utils.bga.BGARefreshLayout;
import com.yusong.community.utils.bga.BGARefreshViewHolder;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ManagerVideoActivity extends BaseActivity implements View.OnClickListener, BGARefreshLayout.BGARefreshLayoutDelegate, IManagerVideoActivityView {
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.linear_back)
    LinearLayout linearBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_choose_all)
    TextView tvChooseAll;
    @InjectView(R.id.checkBox)
    CheckBox checkBox;
    @InjectView(R.id.tv_date)
    TextView tvDate;
    @InjectView(R.id.lg_list)
    MyGridView lgList;
    @InjectView(R.id.mBGA_layout)
    BGARefreshLayout mBGALayout;
    @InjectView(R.id.not_data_layout)
    RelativeLayout notDataLayout;
    @InjectView(R.id.activity_manager_video)
    LinearLayout activityManagerVideo;
    private Context mContext;
    private VideoAlbum videoAlbum;
    private List<VideoAlbum.VideoListBean> videoListBeanList;
    private ManagerVideoAdapter managerVideoAdapter;
    private String videoId = "";
    private IManagerVideoActivityPresenterImpl mPresenter;

    @Override
    protected int layoutId() {
        return R.layout.activity_manager_video;
    }

    @Override
    public void initData() {
        mContext = ManagerVideoActivity.this;
        videoListBeanList = new ArrayList<>();
        linearBack.setOnClickListener(this);
        checkBox.setOnClickListener(this);
        tvChooseAll.setOnClickListener(this);
        mPresenter = new IManagerVideoActivityPresenterImpl(this, this);
        if (getIntent() != null && getIntent().getSerializableExtra("videoAlbum") != null) {
            videoAlbum = (VideoAlbum) getIntent().getSerializableExtra("videoAlbum");
            tvTitle.setText(videoAlbum.getAlbumName() + "");
            for (VideoAlbum.VideoListBean videoListBean : videoAlbum.getVideoList()) {
                if (!StringUtils.isEmpty(videoListBean.getFilePath())) {
                    videoListBeanList.add(videoListBean);
                }
            }
            if (videoListBeanList.size()!=0){
                notDataLayout.setVisibility(View.GONE);
            }else {
                notDataLayout.setVisibility(View.VISIBLE);
            }
            if (videoAlbum.getCreateTime() != null) {
                tvDate.setText(DateUtil.getHidatenew(videoAlbum.getCreateTime()) + "");
            }
        }
        managerVideoAdapter = new ManagerVideoAdapter(ManagerVideoActivity.this, videoListBeanList);
        managerVideoAdapter.setManagerPhoto(new ManagerVideoAdapter.ManagerPhoto() {
            @Override
            public void judegeSelect(int pos) {
                videoListBeanList.get(pos).setCheck(true);
                judegeCheck();
            }
        });
        lgList.setAdapter(managerVideoAdapter);
        initRefreshLayout();
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_back:
                finish();
                break;
            case R.id.checkBox:
                setImageInfo();
                break;
            case R.id.tv_choose_all:
                videoId = "";
                if (checkImag()) {
                    showAlert("确定删除吗?");
                } else {
                    ToastUtils.showShort(mContext, "你至少选择一张图片");
                }
                break;
        }
    }

    private void showAlert(String msg) {
        new AlertDialog.Builder(mContext)
                .setMessage(msg)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.deleteVideo(CacheUtils.getToken(mContext), videoId, videoAlbum.getId());

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

    }

    private boolean checkImag() {
        boolean flag = false;
        for (int i = 0; i < videoListBeanList.size(); i++) {
            if ((videoListBeanList.get(i).isCheck())) {
                flag = true;
                break;
            }
        }
        List<String> ids = new ArrayList<>();
        for (int j = 0; j < videoListBeanList.size(); j++) {
            if ((videoListBeanList.get(j).isCheck())) {
                ids.add(videoListBeanList.get(j).getVideoId() + "");
            }
        }
        if (ids.size() == 1) {
            videoId = ids.get(0);
        } else {
            if (ids.size() != 0) {
                for (int a = 0; a < ids.size() - 1; a++) {
                    videoId = ids.get(a) + "," + videoId;
                }
                videoId = videoId + ids.get(ids.size() - 1);
            }
        }
        return flag;
    }

    private void judegeCheck() {
        boolean flag = true;
        for (int i = 0; i < videoListBeanList.size(); i++) {
            if (!(videoListBeanList.get(i).isCheck())) {
                flag = false;
                break;
            }
        }
        if (flag) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
    }

    private void setImageInfo() {
        if (checkBox.isChecked()) {
            for (int i = 0; i < videoListBeanList.size(); i++) {
                videoListBeanList.get(i).setCheck(true);
            }
        } else {
            for (int i = 0; i < videoListBeanList.size(); i++) {
                videoListBeanList.get(i).setCheck(false);
            }
        }
        managerVideoAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        videoId = "";
        checkBox.setChecked(false);
        mPresenter.getClazzVideoList(CacheUtils.getToken(mContext), videoAlbum.getId());
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public void setVideoList(VideoAlbum list) {
        if(list!=null){
            videoListBeanList.clear();
            videoListBeanList.addAll(list.getVideoList());
            videoId = "";
            if (videoListBeanList.size()!=0){
                notDataLayout.setVisibility(View.GONE);
            }else {
                notDataLayout.setVisibility(View.VISIBLE);
            }
            managerVideoAdapter.notifyDataSetChanged();
        }else {
            notDataLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void closeRefreshing() {
        if (mBGALayout != null) {
            mBGALayout.endRefreshing();
            mBGALayout.endLoadingMore();
        }

    }

    @Override
    public void deleteVideo(String data) {
        finish();
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
