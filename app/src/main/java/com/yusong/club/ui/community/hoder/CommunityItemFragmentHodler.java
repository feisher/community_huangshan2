package com.yusong.club.ui.community.hoder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.MainActivity;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.community.activity.PostDetailActivity;
import com.yusong.club.ui.community.fragment.CommunityFragment;
import com.yusong.club.ui.community.mvp.entity.Posts;
import com.yusong.club.ui.community.photoview.PhotoViewActivity;
import com.yusong.club.utils.CacheUtils;
import com.yusong.club.utils.SPUtils;
import com.yusong.club.utils.glide.GlideImgManager;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by feisher on 2017/1/11.
 */
public class CommunityItemFragmentHodler extends BaseHolder<Posts> {

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
    @InjectView(R.id.iv_like)
    ImageView ivLike;
    @InjectView(R.id.tv_likeCount)
    TextView tvLikeCount;
    @InjectView(R.id.ll_like)
    LinearLayout llLike;
    @InjectView(R.id.tv_commentCount)
    TextView tvCommentCount;
    @InjectView(R.id.ll_comment)
    LinearLayout llComment;

    List<Posts> datas;
    int position;
    public MainActivity activity;
    public CommunityFragment communityFragment;

    public CommunityItemFragmentHodler(View itemView, Context mContext,ShowInputView showInputView) {
        super(itemView, mContext);
        this.showInputView=showInputView;
    }

    @Override
    public void setData(final List<Posts> datas, final int position) {
        this.datas = datas;
        this.position = position;
        activity = (MainActivity)mContext;
        Posts posts = datas.get(position);
        Posts.CustomerBean customer = posts.getCustomer();
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EventBus.getDefault().post(datas.get(position));
                Intent intent = new Intent(mContext, PostDetailActivity.class);
                intent.putExtra("POST",datas.get(position));
                mContext.startActivity(intent);
            }
        });
        //设置头像
        GlideImgManager.loadCircleImage(mContext,customer.getPortrait(),ivUserIcon);
        if (!TextUtils.isEmpty(customer.getNickname())) {
            tvUserName.setText(customer.getNickname());//设置姓名
        }else if (!TextUtils.isEmpty(customer.getMobile())){
            tvUserName.setText(customer.getMobile());
        }else if (!TextUtils.isEmpty(customer.getRealName())){
            tvUserName.setText(customer.getRealName());//设置姓名
        }else {
            tvUserName.setText("匿名用户");//设置姓名
        }
        if (customer.getGender() ==1) {
            tvUserSex.setText("♂");
        }else {
            tvUserSex.setText("♀");
        }
        if (CacheUtils.getUserInfo(mContext)!=null&&!TextUtils.isEmpty(CacheUtils.getUserInfo(mContext).getCommunityName())) {
            tvAgentName.setText(CacheUtils.getUserInfo(mContext).getCommunityName());//写死小区
        }
        if (posts.getSupported()==0) {
            tvLikeCount.setTextColor(Color.parseColor("#999999"));
            ivLike.setBackgroundResource(R.mipmap.icon_not_supported);
            SPUtils.put(mContext,posts.getId()+customer.getId()+"isLikeClicked",false);
        }else {
            tvLikeCount.setTextColor(Color.parseColor("#ff871c"));
            ivLike.setBackgroundResource(R.mipmap.icon_supported);
            SPUtils.put(mContext,posts.getId()+customer.getId()+"isLikeClicked",true);
        }
        SPUtils.put(mContext,posts.getId()+"SupportCount",posts.getSupportCount());
        tvLikeCount.setText(posts.getSupportCount()+"");//点赞数量
        tvCommentCount.setText(posts.getCommentCount()+"");//评论数量
        String createTime = posts.getCreateTime();
        //时间显示需要特殊处理   2016-8-28  09:42:40
        long currentTimeMillis = System.currentTimeMillis();//获取当前时间毫秒值
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date d = dateFormat.parse(createTime);
            long time = d.getTime();
            long time_D_Value = currentTimeMillis - time;//时间差
            if (time_D_Value<60*1000){//一分钟内
                showTime.setText("刚刚");
            }else if (time_D_Value<60*60*1000){//一小时内
                showTime.setText((int)(time_D_Value/(1000*60))+"分钟前");
            }else if (time_D_Value<24*60*60*1000){//一天内
                showTime.setText((int)(time_D_Value/(1000*60*60))+"小时前");
            }else if (time_D_Value<7*24*60*60*1000){//一周内
                showTime.setText((int)(time_D_Value/(1000*60*60*24))+"天前");
            }else {
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("MM月dd日");
                String releaseDate = dateFormat2.format(d);
                showTime.setText(releaseDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        tvContent.setText(posts.getContent());
        int size = posts.getImageList().size();
        if (size==1) {
            llSingleImg.setVisibility(View.VISIBLE);
            llImg2Layout.setVisibility(View.GONE);
            llImg3Layout.setVisibility(View.GONE);
            llImg4Layout.setVisibility(View.GONE);
            GlideImgManager.loadImage(mContext,posts.getImageList().get(0),ivSingle);
        }else if (size==2){
            llSingleImg.setVisibility(View.GONE);
            llImg2Layout.setVisibility(View.VISIBLE);
            llImg3Layout.setVisibility(View.GONE);
            llImg4Layout.setVisibility(View.GONE);
            GlideImgManager.loadImage(mContext,posts.getImageList().get(0),iv21);
            GlideImgManager.loadImage(mContext,posts.getImageList().get(1),iv22);
        }else if (size==3){
            llSingleImg.setVisibility(View.GONE);
            llImg2Layout.setVisibility(View.GONE);
            llImg3Layout.setVisibility(View.VISIBLE);
            llImg4Layout.setVisibility(View.GONE);
            GlideImgManager.loadImage(mContext,posts.getImageList().get(0),iv31);
            GlideImgManager.loadImage(mContext,posts.getImageList().get(1),iv32);
            GlideImgManager.loadImage(mContext,posts.getImageList().get(2),iv33);
        }else if (size==4){
            llSingleImg.setVisibility(View.GONE);
            llImg2Layout.setVisibility(View.GONE);
            llImg3Layout.setVisibility(View.GONE);
            llImg4Layout.setVisibility(View.VISIBLE);
            GlideImgManager.loadImage(mContext,posts.getImageList().get(0),iv41);
            GlideImgManager.loadImage(mContext,posts.getImageList().get(1),iv42);
            GlideImgManager.loadImage(mContext,posts.getImageList().get(2),iv43);
            GlideImgManager.loadImage(mContext,posts.getImageList().get(3),iv44);
        }else {
            llSingleImg.setVisibility(View.GONE);
            llImg2Layout.setVisibility(View.GONE);
            llImg3Layout.setVisibility(View.GONE);
            llImg4Layout.setVisibility(View.GONE);
        }
//        notifyItemChanged(position);
    }
    @OnClick({R.id.iv_3_1, R.id.iv_3_2, R.id.iv_3_3, R.id.iv_4_1, R.id.iv_4_2, R.id.iv_4_3, R.id.iv_4_4, R.id.iv_2_1, R.id.iv_2_2, R.id.iv_single, R.id.ll_like, R.id.ll_comment})
    public void onClick(View view) {
        Posts posts = datas.get(position);
        Posts.CustomerBean customer = posts.getCustomer();
//        Intent mIntent = new Intent(mContext, BaseWebViewActivity.class);
//        mIntent.putExtra("title","图片浏览");
        List<String> imageList = posts.getImageList();
        communityFragment = (CommunityFragment) activity.getSupportFragmentManager().findFragmentByTag("CommunityFragment");
        Intent intent = new Intent(mContext, PhotoViewActivity.class);
        intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_URLS, (Serializable) imageList);

        switch (view.getId()) {
            case R.id.iv_3_1:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX,0);
                mContext.startActivity(intent);
//                Bitmap bitmapFromView = BitmapUtil.getBitmapFromView(iv31);
//                communityFragment.imageZom(iv31,imageList.get(0));
                break;
            case R.id.iv_3_2:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX,1);
                mContext.startActivity(intent);
//                communityFragment.imageZom(iv32,imageList.get(1));
                break;
            case R.id.iv_3_3:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX,2);
                mContext.startActivity(intent);
//                communityFragment.imageZom(iv33,imageList.get(2));
                break;
            case R.id.iv_4_1:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX,0);
                mContext.startActivity(intent);
//                communityFragment.imageZom(iv41,imageList.get(0));
                break;
            case R.id.iv_4_2:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX,1);
                mContext.startActivity(intent);
//                communityFragment.imageZom(iv42,imageList.get(1));
                break;
            case R.id.iv_4_3:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX,2);
                mContext.startActivity(intent);
//                communityFragment.imageZom(iv43,imageList.get(2));
                break;
            case R.id.iv_4_4:
//                communityFragment.imageZom(iv44,imageList.get(3));
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX,3);
                mContext.startActivity(intent);
                break;
            case R.id.iv_2_1:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX,0);
                mContext.startActivity(intent);
//                communityFragment.imageZom(iv21,imageList.get(0));
                break;
            case R.id.iv_2_2:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX,1);
                mContext.startActivity(intent);
//                communityFragment.imageZom(iv22,imageList.get(1));
                break;
            case R.id.iv_single:
                intent.putExtra(PhotoViewActivity.EXTRA_IMAGE_INDEX,0);
                mContext.startActivity(intent);
//                communityFragment.imageZom(ivSingle,imageList.get(0));
                break;
            case R.id.ll_like:
                boolean isLikeClicked = (boolean) SPUtils.get(mContext, posts.getId() + customer.getId() + "isLikeClicked", false);
                if (isLikeClicked) {
                    ivLike.setBackgroundResource(R.mipmap.icon_not_supported);
                    SPUtils.put(mContext,posts.getId()+customer.getId()+"isLikeClicked",false);
                    int SupportCount = (int) SPUtils.get(mContext,posts.getId()+"SupportCount", 0);
                    tvLikeCount.setText(SupportCount-1+"");
                    SPUtils.put(mContext, posts.getId()+"SupportCount", SupportCount-1);
                    tvLikeCount.setTextColor(Color.parseColor("#999999"));
                }else {
                    ivLike.setBackgroundResource(R.mipmap.icon_supported);
                    SPUtils.put(mContext,posts.getId()+customer.getId()+"isLikeClicked",true);
                    int SupportCount = (int) SPUtils.get(mContext, posts.getId()+"SupportCount", 0);
                    tvLikeCount.setText(SupportCount+1+"");
                    SPUtils.put(mContext, posts.getId()+"SupportCount", SupportCount+1);
                    tvLikeCount.setTextColor(Color.parseColor("#ff871c"));
                }
                communityFragment.postsLike(posts.getId());
//                EventBus.getDefault().post(new PostsLiketEvent(datas.get(position).getId()));
                break;
            case R.id.ll_comment:
                showInputView.showInput(posts.getId());
//                CommentEvent event= new CommentEvent();
//                event.setPostId(posts.getId());
//                EventBus.getDefault().post(event);
                break;
        }
    }
    public interface  ShowInputView{
        void showInput(int postId);
    }
  private ShowInputView showInputView;

}
