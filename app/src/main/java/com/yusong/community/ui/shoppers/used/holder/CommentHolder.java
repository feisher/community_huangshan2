package com.yusong.community.ui.shoppers.used.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.shoppers.used.bean.UsedCommentBean;
import com.yusong.community.utils.glide.GlideImgManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.InjectView;

/**
 * @author Mr_Peng
 *         crated at on 2017/3/22.
 *         描述: null
 */

public class CommentHolder extends BaseHolder<UsedCommentBean> {
    private View itemView;

    public CommentHolder(View itemView, Context context) {
        super(itemView, context);
        this.itemView = itemView;
    }

    @Override
    public void setData(List<UsedCommentBean> datas, int position) {
        UsedCommentBean bean = datas.get(position);
        UsedCommentBean.Passport passport = bean.getPassport();
        if (passport.getPortrait() != null) {
            GlideImgManager.loadCircleImage(mContext, passport.getPortrait(), headImage);
        }
        userNameTv.setText(passport.getNickname());
        commentTimeTv.setText(bean.getCreateTime());
        long currentTimeMillis = System.currentTimeMillis();//获取当前时间毫秒值
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date d = dateFormat.parse(bean.getCreateTime());
            long time = d.getTime();
            long time_D_Value = currentTimeMillis - time;//时间差
            if (time_D_Value<60*1000){//一分钟内
                commentTimeTv.setText("刚刚");
            }else if (time_D_Value<60*60*1000){//一小时内
                commentTimeTv.setText((int)(time_D_Value/(1000*60))+"分钟前");
            }else if (time_D_Value<24*60*60*1000){//一天内
                commentTimeTv.setText((int)(time_D_Value/(1000*60*60))+"小时前");
            }else if (time_D_Value<7*24*60*60*1000){//一周内
                commentTimeTv.setText((int)(time_D_Value/(1000*60*60*24))+"天前");
            }else {
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("MM月dd日");
                String releaseDate = dateFormat2.format(d);
                commentTimeTv.setText(releaseDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (bean.getType() == 1) {
            nullTv.setVisibility(View.GONE);
            commentMessageTv.setText(bean.getContent());
        } else {
            nullTv.setVisibility(View.VISIBLE);
            commentMessageTv.setText("回复@" + passport.getNickname() + ":" + bean.getContent());
        }
    }

    @InjectView(R.id.head_image)
    ImageView headImage;
    @InjectView(R.id.user_name_tv)
    TextView userNameTv;
    @InjectView(R.id.comment_time_tv)
    TextView commentTimeTv;
    @InjectView(R.id.comment_message_tv)
    TextView commentMessageTv;
    @InjectView(R.id.null_tv)
    TextView nullTv;
}
