package com.yusong.community.ui.school.teacher.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;
import com.yusong.community.MyApplication;
import com.yusong.community.R;
import com.yusong.community.ui.base.BaseActivity;
import com.yusong.community.ui.school.mvp.implView.IVideoDetailActivityView;
import com.yusong.community.ui.school.mvp.presenter.impl.IVideoDetailActivityPresenterImpl;
import com.yusong.community.ui.school.teacher.adapter.VideoDetailAdapter;
import com.yusong.community.ui.school.teacher.bean.VideoAlbum;
import com.yusong.community.ui.school.teacher.view.MyGridView;
import com.yusong.community.ui.video.VideoPlayDetailActivity;
import com.yusong.community.utils.CacheUtils;
import com.yusong.community.utils.ToastUtils;
import com.yusong.community.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.community.utils.bga.BGARefreshLayout;
import com.yusong.community.utils.bga.BGARefreshViewHolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class VideoDetailActivity extends BaseActivity implements View.OnClickListener, IVideoDetailActivityView, BGARefreshLayout.BGARefreshLayoutDelegate {
    @InjectView(R.id.linear_back)
    LinearLayout linearBack;
    @InjectView(R.id.im_msg)
    ImageView imMsg;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.im_back)
    ImageView imBack;
    @InjectView(R.id.tv_back)
    TextView tvBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.gv_List)
    MyGridView rvList;
    @InjectView(R.id.mBGA_layout)
    BGARefreshLayout mBGALayout;
    @InjectView(R.id.activity_video_detail)
    LinearLayout activityVideoDetail;
    @InjectView(R.id.not_data_layout)
    RelativeLayout notDataLayout;
    private Context mContext;
    private VideoAlbum videoAlbum;
    private String id = null;
    private VideoDetailAdapter mAdapter;
    private List<VideoAlbum.VideoListBean> videoList;
    private IVideoDetailActivityPresenterImpl mPresenter;
    private List<String> chooseVideos;
    private int canEdit = 0;

    @Override
    protected int layoutId() {
        // 设置一个exit transition
        return R.layout.activity_video_detail;
    }

    @Override
    public void initData() {
        videoList = new ArrayList<>();
        chooseVideos = new ArrayList<>();
        chooseVideos.add("上传视频");
        chooseVideos.add("管理视频");
        chooseVideos.add("删除视册");
        mContext = VideoDetailActivity.this;
        linearBack.setOnClickListener(this);
        imMsg.setOnClickListener(this);
        mPresenter = new IVideoDetailActivityPresenterImpl(this, this);
        if (getIntent() != null && getIntent().getSerializableExtra("videoAlbum") != null) {
            videoAlbum = (VideoAlbum) getIntent().getSerializableExtra("videoAlbum");
            tvTitle.setText(videoAlbum.getAlbumName());
            canEdit = videoAlbum.getCanEdit();
            videoList = videoAlbum.getVideoList();
            id = videoAlbum.getId() + "";
            if (videoAlbum.getCanEdit() == 1) {
                imMsg.setImageResource(R.mipmap.ic_photo_dian);
                imMsg.setVisibility(View.VISIBLE);
            } else {
                imMsg.setVisibility(View.INVISIBLE);
            }
        }
        mAdapter = new VideoDetailAdapter(VideoDetailActivity.this, videoList);
        rvList.setAdapter(mAdapter);
        rvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, VideoPlayDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("videoUrl", videoList.get(position).getFilePath());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        initRefreshLayout();
    }

    private void showPopupWindow(List<String> data) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popwindow_express_layout, null);
        contentView.getBackground().setAlpha(100);
        final PopupWindow window;
        window = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        window.setContentView(contentView);
        final WheelView wheelView = (WheelView) contentView.findViewById(R.id.wheelview);
        wheelView.setWheelAdapter(new ArrayWheelAdapter(this)); // 文本数据源
        wheelView.setWheelData(data);  // 数据集合
        wheelView.setSkin(WheelView.Skin.Holo);
        wheelView.setWheelSize(5);
        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        style.textColor = Color.parseColor("#888888");
        style.selectedTextColor = Color.parseColor("#333333");
        wheelView.setStyle(style);
        TextView tv_ok = (TextView) contentView.findViewById(R.id.tv_ok);
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wheelView.getSelectionItem().toString().equals("上传视频")) {
                    Intent intent = new Intent(mContext, UploadVideoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("videoAlbum", videoAlbum);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else if (wheelView.getSelectionItem().toString().equals("管理视频")) {
                    if (canEdit == 1) {
                        Intent intent = new Intent(mContext, ManagerVideoActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("videoAlbum", (Serializable) videoAlbum);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else {
                        ToastUtils.showShort(mContext, "您没有管理视频的权限");
                    }
                } else if (wheelView.getSelectionItem().toString().equals("删除视册")) {
                    if (canEdit == 1) {
                        mPresenter.deleteAllVideo(CacheUtils.getToken(mContext), videoAlbum.getId());
                    } else {
                        ToastUtils.showShort(mContext, "您没有删除视册的权限");
                    }
                }
                window.dismiss();
            }
        });
        TextView tv_cancel = (TextView) contentView.findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
        window.setFocusable(true);
        window.setOutsideTouchable(true);
        window.setBackgroundDrawable(new BitmapDrawable());
        window.showAtLocation(activityVideoDetail, Gravity.BOTTOM, 0, 0);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_back:
                finish();
                break;
            case R.id.im_msg:
                showPopupWindow(chooseVideos);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getClazzVideoList(CacheUtils.getToken(mContext), videoAlbum.getId());

    }

    @Override
    public void getAllVideo(VideoAlbum data) {
        if (data != null && data.getVideoList() != null && data.getVideoList().size() != 0) {
            notDataLayout.setVisibility(View.GONE);
            videoList.clear();
            videoList.addAll(data.getVideoList());
            mAdapter.notifyDataSetChanged();
        } else {
            notDataLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void closeRefresh() {
        if (mBGALayout != null) {
            mBGALayout.endRefreshing();
            mBGALayout.endLoadingMore();
        }
    }

    @Override
    public void deleteAllVideo(String data) {
        finish();

    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(mContext);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mPresenter.getClazzVideoList(CacheUtils.getToken(mContext), videoAlbum.getId());

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}
