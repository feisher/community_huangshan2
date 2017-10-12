package com.yusong.community.ui.school.teacher.holder;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.school.school.bean.StudentComment;
import com.yusong.community.utils.DateUtil;
import com.yusong.community.utils.glide.GlideImgManager;

import java.util.List;

import butterknife.InjectView;


public class EvaluateHolder extends BaseHolder<StudentComment> {
    @InjectView(R.id.item_name)
    TextView itemName;
    @InjectView(R.id.rating_bar)
    RatingBar ratingBar;
    @InjectView(R.id.item_content)
    TextView itemContent;
    @InjectView(R.id.item_time)
    TextView itemTime;
    @InjectView(R.id.iv_header)
    ImageView ivHeader;

    public EvaluateHolder(View itemView, Context mContext) {
        super(itemView, mContext);
    }


    @Override
    public void setData(List<StudentComment> datas, int position) {
        LayerDrawable layerDrawable = (LayerDrawable) ratingBar.getProgressDrawable();
        layerDrawable.getDrawable(2).setColorFilter(0xFFFF9001, PorterDuff.Mode.SRC_ATOP);
        if (datas.get(position).getStudent() != null && datas.get(position).getStudent().getPortrait() != null) {
            GlideImgManager.loadCircleImage(mContext, datas.get(position).getStudent().getPortrait(), ivHeader);
        } else {
            ivHeader.setImageResource(R.mipmap.school_four);
        }
        ratingBar.setRating(datas.get(position).getStar1());
        itemName.setText(datas.get(position).getStudent().getUsername());
        itemContent.setText(datas.get(position).getContent());
        if (datas.get(position).getCreateTime() != null) {
            itemTime.setText(DateUtil.getFormateDate(datas.get(position).getCreateTime()));
        }
    }


}
