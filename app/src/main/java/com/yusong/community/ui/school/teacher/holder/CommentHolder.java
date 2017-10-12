package com.yusong.community.ui.school.teacher.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.school.school.bean.Comment;
import com.yusong.community.utils.glide.GlideImgManager;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by ds on 2017/1/13.
 */
public class CommentHolder extends BaseHolder<Comment> {
    @InjectView(R.id.iv_header)
    ImageView ivHeader;
    @InjectView(R.id.tv_user_name)
    TextView tvUserName;
    @InjectView(R.id.tv_time)
    TextView tvTime;
    @InjectView(R.id.tv_content)
    TextView tvContent;

    public CommentHolder(View itemView, Context mContext) {
        super(itemView, mContext);
    }

    @Override
    public void setData(List<Comment> datas, int position) {
        if (datas.get(position).getPassport() != null) {
            tvUserName.setText(datas.get(position).getPassport().getUsername() + "");
        }
        tvTime.setText(datas.get(position).getCreateTime());
        tvContent.setText(datas.get(position).getContent());
        if (datas.get(position).getPassport() != null && datas.get(position).getPassport().getPortrait() != null && !(datas.get(position).getPassport().getPortrait().equals(""))) {
            GlideImgManager.loadCircleImage(mContext, datas.get(position).getPassport().getPortrait(), ivHeader);
        } else {
            ivHeader.setImageResource(R.mipmap.school_four);
        }
    }
}
