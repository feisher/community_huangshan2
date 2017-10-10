package com.yusong.club.ui.community.hoder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.community.mvp.entity.PostComment;
import com.yusong.club.utils.glide.GlideImgManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by feisher on 2017/2/17.
 */
public class PostCommentHolder extends BaseHolder<PostComment> {
    @InjectView(R.id.iv_user_icon)
    ImageView ivUserIcon;
    @InjectView(R.id.tv_user_name)
    TextView tvUserName;
    @InjectView(R.id.tv_user_sex)
    TextView tvUserSex;
    @InjectView(R.id.show_time)
    TextView showTime;
    @InjectView(R.id.tv_content)
    TextView tvContent;
    public PostCommentHolder(View v, Context mContext) {
        super(v,mContext);
    }

    @Override
    public void setData(List<PostComment> datas, int position) {
        PostComment postComment = datas.get(position);
        PostComment.CustomerBean customer = postComment.getCustomer();
        GlideImgManager.loadCircleImage(mContext,customer.getPortrait(),ivUserIcon);
        tvUserName.setText(customer.getRealName());
        if (customer.getGender()==1) {
            tvUserSex.setText("♂");
        }else {
            tvUserSex.setText("♀");
        }
        String createTime = postComment.getCreateTime();
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
        }catch (Exception e){

        }
        tvContent.setText(postComment.getContent());
    }
}
