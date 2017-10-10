package com.yusong.club.ui.community.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.yusong.club.MyApplication;
import com.yusong.club.R;
import com.yusong.club.ui.base.BaseActivity;
import com.yusong.club.ui.community.adapter.PostCommentsAdpter;
import com.yusong.club.ui.community.event.PostsCommentEvent;
import com.yusong.club.ui.community.mvp.entity.PostComment;
import com.yusong.club.ui.community.mvp.entity.Posts;
import com.yusong.club.ui.community.mvp.implView.PostDetailActivityView;
import com.yusong.club.ui.community.mvp.presenter.impl.PostDetailActivityPresenterIpml;
import com.yusong.club.ui.community.photoview.PhotoViewActivity;
import com.yusong.club.utils.AndroidBug5497Workaround;
import com.yusong.club.utils.bga.BGANormalRefreshViewHolder;
import com.yusong.club.utils.bga.BGARefreshLayout;
import com.yusong.club.utils.bga.BGARefreshViewHolder;
import com.yusong.club.utils.glide.GlideImgManager;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**  帖子详情界面
 * create by feisher on 2017/3/2 13:31
 * Email：458079442@qq.com
 */
public class PostDetailActivity extends BaseActivity implements PostDetailActivityView, BGARefreshLayout.BGARefreshLayoutDelegate {

    public PostDetailActivityPresenterIpml mPresenter;
    public List<PostComment> mPostCommentDatas = new ArrayList<>();
    public boolean isRefresh;
    public Posts post;
    public LinearLayoutManager mLinearLayoutManager;
    public PostCommentsAdpter mAdapter;
    public Info mInfo;
    @InjectView(R.id.iv_adaptive_down_api18)
    ImageView ivAdaptiveDownApi18;
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.iv_user_icon)
    ImageView ivUserIcon;
    @InjectView(R.id.tv_user_name)
    TextView tvUserName;
    @InjectView(R.id.tv_user_sex)
    TextView tvUserSex;
    @InjectView(R.id.tv_agentName)
    TextView tvAgentName;
    @InjectView(R.id.show_time)
    TextView showTime;
    @InjectView(R.id.tv_content)
    TextView tvContent;
    @InjectView(R.id.iv_3_1)
    ImageView iv31;
    @InjectView(R.id.iv_3_2)
    ImageView iv32;
    @InjectView(R.id.iv_3_3)
    ImageView iv33;
    @InjectView(R.id.ll_img_3_layout)
    LinearLayout llImg3Layout;
    @InjectView(R.id.iv_4_1)
    ImageView iv41;
    @InjectView(R.id.iv_4_2)
    ImageView iv42;
    @InjectView(R.id.iv_4_3)
    ImageView iv43;
    @InjectView(R.id.iv_4_4)
    ImageView iv44;
    @InjectView(R.id.ll_img_4_layout)
    LinearLayout llImg4Layout;
    @InjectView(R.id.iv_2_1)
    ImageView iv21;
    @InjectView(R.id.iv_2_2)
    ImageView iv22;
    @InjectView(R.id.ll_img_2_layout)
    LinearLayout llImg2Layout;
    @InjectView(R.id.iv_single)
    ImageView ivSingle;
    @InjectView(R.id.ll_single_img)
    LinearLayout llSingleImg;
    @InjectView(R.id.rv_postcomments)
    RecyclerView rvPostcomments;
    @InjectView(R.id.mBGARefreshLayout)
    BGARefreshLayout mBGARefreshLayout;
    @InjectView(R.id.et_content)
    EditText etContent;
    @InjectView(R.id.btn_send)
    Button btnSend;
    @InjectView(R.id.photoview)
    PhotoView photoview;

    @Override
    protected int layoutId() {
        return R.layout.activity_post_detail;
    }

    @Override
    public void initView() {
        photoview.enable();//设置photoview启用缩放
        AndroidBug5497Workaround.assistActivity(this);
        initRefreshLayout();
//        EventBus.getDefault().register(this);//订阅
        tvTitle.setText("帖子详情");
        etContent.setFocusable(true);
    }


    @Override
    public void initData() {
        mPresenter = new PostDetailActivityPresenterIpml(this, this);
        post = (Posts) getIntent().getParcelableExtra("POST");
        if (post != null) {
            GlideImgManager.loadCircleImage(this, post.getCustomer().getPortrait(), ivUserIcon);
            Posts.CustomerBean customer = post.getCustomer();
            if (!TextUtils.isEmpty(customer.getNickname())) {
                tvUserName.setText(post.getCustomer().getNickname());
            }else if(!TextUtils.isEmpty(customer.getRealName())){
                tvUserName.setText(post.getCustomer().getRealName());
            }
            if (post.getCustomer().getGender() == 1) {
                tvUserSex.setText("♂");
            } else {
                tvUserSex.setText("♀");
            }
            tvAgentName.setText("");
            String createTime = post.getCreateTime();
            long currentTimeMillis = System.currentTimeMillis();//获取当前时间毫秒值
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            try {
                Date d = dateFormat.parse(createTime);
                long time = d.getTime();
                long time_D_Value = currentTimeMillis - time;//时间差
                if (time_D_Value < 60 * 1000) {//一分钟内
                    showTime.setText("刚刚");
                } else if (time_D_Value < 60 * 60 * 1000) {//一小时内
                    showTime.setText((int) (time_D_Value / (1000 * 60)) + "分钟前");
                } else if (time_D_Value < 24 * 60 * 60 * 1000) {//一天内
                    showTime.setText((int) (time_D_Value / (1000 * 60 * 60)) + "小时前");
                } else if (time_D_Value < 7 * 24 * 60 * 60 * 1000) {//一周内
                    showTime.setText((int) (time_D_Value / (1000 * 60 * 60 * 24)) + "天前");
                } else {
                    SimpleDateFormat dateFormat2 = new SimpleDateFormat("MM月dd日");
                    String releaseDate = dateFormat2.format(d);
                    showTime.setText(releaseDate);
                }
            } catch (Exception e) {

            }
            tvContent.setText(post.getContent());
            int size = post.getImageList().size();
            if (size == 1) {
                llSingleImg.setVisibility(View.VISIBLE);
                llImg2Layout.setVisibility(View.GONE);
                llImg3Layout.setVisibility(View.GONE);
                llImg4Layout.setVisibility(View.GONE);
                GlideImgManager.loadImage(this, post.getImageList().get(0), ivSingle);
            } else if (size == 2) {
                llSingleImg.setVisibility(View.GONE);
                llImg2Layout.setVisibility(View.VISIBLE);
                llImg3Layout.setVisibility(View.GONE);
                llImg4Layout.setVisibility(View.GONE);
                GlideImgManager.loadImage(this, post.getImageList().get(0), iv21);
                GlideImgManager.loadImage(this, post.getImageList().get(1), iv22);
            } else if (size == 3) {
                llSingleImg.setVisibility(View.GONE);
                llImg2Layout.setVisibility(View.GONE);
                llImg3Layout.setVisibility(View.VISIBLE);
                llImg4Layout.setVisibility(View.GONE);
                GlideImgManager.loadImage(this, post.getImageList().get(0), iv31);
                GlideImgManager.loadImage(this, post.getImageList().get(1), iv32);
                GlideImgManager.loadImage(this, post.getImageList().get(2), iv33);
            } else if (size == 4) {
                llSingleImg.setVisibility(View.GONE);
                llImg2Layout.setVisibility(View.GONE);
                llImg3Layout.setVisibility(View.GONE);
                llImg4Layout.setVisibility(View.VISIBLE);
                GlideImgManager.loadImage(this, post.getImageList().get(0), iv41);
                GlideImgManager.loadImage(this, post.getImageList().get(1), iv42);
                GlideImgManager.loadImage(this, post.getImageList().get(2), iv43);
                GlideImgManager.loadImage(this, post.getImageList().get(3), iv44);
            } else {
                llSingleImg.setVisibility(View.GONE);
                llImg2Layout.setVisibility(View.GONE);
                llImg3Layout.setVisibility(View.GONE);
                llImg4Layout.setVisibility(View.GONE);
            }
            mPresenter.queryPostComments(post.getId(), 0, 10);
        }
    }

    @Override
    protected void adaptiveSystemVersions() {
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= 18) {
            ivAdaptiveDownApi18.setVisibility(View.VISIBLE);
        } else {
            ivAdaptiveDownApi18.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initListener() {

    }

    /**
     * 初始化刷新布局
     */
    private void initRefreshLayout() {
        mBGARefreshLayout.setDelegate(this);//设置代理
        BGARefreshViewHolder mBGARefreshViewHolder = new BGANormalRefreshViewHolder(this, true);
        mBGARefreshLayout.setRefreshViewHolder(mBGARefreshViewHolder);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        isRefresh = true;
        if (post != null) {
            mPresenter.queryPostComments(post.getId(), 0, 10);
        } else {
            refreshLayout.endRefreshing();
        }
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        isRefresh = false;
        if (post != null) {
            mPresenter.queryPostComments(post.getId(), mPostCommentDatas.size(), 10);
        } else {
            refreshLayout.endLoadingMore();
        }
        return true;
    }


    @OnClick({R.id.ll_back, R.id.btn_send, R.id.iv_3_1, R.id.iv_3_2, R.id.iv_3_3, R.id.iv_4_1, R.id.iv_4_2, R.id.iv_4_3, R.id.iv_4_4, R.id.iv_2_1, R.id.iv_2_2, R.id.iv_single, R.id.photoview})
    public void onClick(View view) {
//        Intent mIntent = new Intent(this, BaseWebViewActivity.class);
//        mIntent.putExtra("title", "图片浏览");
        Intent intent = new Intent(this, PhotoViewActivity.class);
        intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_URLS, (Serializable) post.getImageList());

        switch (view.getId()) {
            case R.id.iv_3_1:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX,0);
                startActivity(intent);
//                Bitmap bitmapFromView = BitmapUtil.getBitmapFromView(iv31);
//                communityFragment.imageZom(iv31,imageList.get(0));
                break;
            case R.id.iv_3_2:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX,1);
                startActivity(intent);
//                communityFragment.imageZom(iv32,imageList.get(1));
                break;
            case R.id.iv_3_3:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX,2);
                startActivity(intent);
//                communityFragment.imageZom(iv33,imageList.get(2));
                break;
            case R.id.iv_4_1:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX,0);
                startActivity(intent);
//                communityFragment.imageZom(iv41,imageList.get(0));
                break;
            case R.id.iv_4_2:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX,1);
                startActivity(intent);
//                communityFragment.imageZom(iv42,imageList.get(1));
                break;
            case R.id.iv_4_3:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX,2);
                startActivity(intent);
//                communityFragment.imageZom(iv43,imageList.get(2));
                break;
            case R.id.iv_4_4:
//                communityFragment.imageZom(iv44,imageList.get(3));
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX,3);
                startActivity(intent);
                break;
            case R.id.iv_2_1:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX,0);
                startActivity(intent);
//                communityFragment.imageZom(iv21,imageList.get(0));
                break;
            case R.id.iv_2_2:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX,1);
                startActivity(intent);
//                communityFragment.imageZom(iv22,imageList.get(1));
                break;
            case R.id.iv_single:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX,0);
                startActivity(intent);
//                communityFragment.imageZom(ivSingle,imageList.get(0));
                break;
//        switch (view.getId()) {
//            case R.id.iv_3_1:
//                imageZom(iv31, post.getImageList().get(0));
//                break;
//            case R.id.iv_3_2:
//                imageZom(iv32, post.getImageList().get(1));
//                break;
//            case R.id.iv_3_3:
//                imageZom(iv33, post.getImageList().get(2));
//                break;
//            case R.id.iv_4_1:
//                imageZom(iv41, post.getImageList().get(0));
//                break;
//            case R.id.iv_4_2:
//                imageZom(iv42, post.getImageList().get(1));
//                break;
//            case R.id.iv_4_3:
//                imageZom(iv43, post.getImageList().get(2));
//                break;
//            case R.id.iv_4_4:
//                imageZom(iv44, post.getImageList().get(3));
//                break;
//            case R.id.iv_2_1:
//                imageZom(iv21, post.getImageList().get(0));
//                break;
//            case R.id.iv_2_2:
//                imageZom(iv22, post.getImageList().get(1));
//                break;
//            case R.id.iv_single:
//                imageZom(ivSingle, post.getImageList().get(0));
//                break;
            case R.id.ll_back:
                finish();
                break;
            case R.id.btn_send:
                String content = etContent.getText().toString().trim();
                if (!TextUtils.isEmpty(content)) {
                    mPresenter.createPostComment(post.getId(), content);
                } else {
                    MyApplication.showMessage("请输入内容后再发送！");
                }
                break;
            case R.id.photoview:
                photoview.animaTo(mInfo, new Runnable() {
                    @Override
                    public void run() {
                        photoview.setVisibility(View.GONE);
//                        img.setVisibility(View.VISIBLE);
                    }
                });
                break;
        }
    }

    private void imageZom(ImageView img, String url) {
        photoview.setVisibility(View.VISIBLE);
//        img.setVisibility(View.GONE);
        mInfo = PhotoView.getImageViewInfo(img);
//        Glide.with(this).load(url).fitCenter().into(photoview);
        GlideImgManager.loadImageFitCenter(this, url, photoview);
        photoview.animaFrom(mInfo);
    }


    @Override
    public void setCommentsAdapter(List<PostComment> data) {
        if (isRefresh) {
            mPostCommentDatas.clear();
            mPostCommentDatas.addAll(data);
        } else {
            mPostCommentDatas.addAll(data);
        }
        if (mAdapter == null) {
            mLinearLayoutManager = new LinearLayoutManager(this) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            mAdapter = new PostCommentsAdpter(mPostCommentDatas, this);
            rvPostcomments.setAdapter(mAdapter);
            rvPostcomments.setLayoutManager(mLinearLayoutManager);
            mBGARefreshLayout.setLinearLayoutManager(mPostCommentDatas, mLinearLayoutManager);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void commentSucceedCallback() {
        etContent.setText("");
        isRefresh = true;
        mPresenter.queryPostComments(post.getId(), 0, 10);
        EventBus.getDefault().post(new PostsCommentEvent(post.getId()));
    }

    @Override
    public void closeRefresh() {
        if (mBGARefreshLayout != null) {
            mBGARefreshLayout.endRefreshing();
            mBGARefreshLayout.endLoadingMore();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);//解除订阅
    }

    @Override
    public void showProgressDialog() {
        MyApplication.showProgressDialog(this);
    }
}
