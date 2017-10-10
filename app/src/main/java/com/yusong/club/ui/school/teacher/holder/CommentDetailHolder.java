package com.yusong.club.ui.school.teacher.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.school.mvp.entity.DetailComment;
import com.yusong.club.utils.glide.GlideImgManager;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by ds on 2017/1/13.
 */
public class CommentDetailHolder extends BaseHolder<DetailComment> {
    @InjectView(R.id.iv_header)
    ImageView ivHeader;
    @InjectView(R.id.tv_user_name)
    TextView tvUserName;
    @InjectView(R.id.tv_time)
    TextView tvTime;
    @InjectView(R.id.tv_content)
    TextView tvContent;

    public CommentDetailHolder(View itemView, Context mContext) {
        super(itemView, mContext);
    }

    @Override
    public void setData(List<DetailComment> datas, int position) {
        tvUserName.setText(datas.get(position).getCreateUserName());
        tvTime.setText(datas.get(position).getCreateTime());
        tvContent.setText(datas.get(position).getContent());
        if (datas.get(position).getCreateUserIconPath() != null && !datas.get(position).getCreateUserIconPath().equals("")) {
            GlideImgManager.loadCircleImage(mContext, datas.get(position).getCreateUserIconPath(), ivHeader);
        } else {
            ivHeader.setImageResource(R.mipmap.school_four);
        }
    }
}
