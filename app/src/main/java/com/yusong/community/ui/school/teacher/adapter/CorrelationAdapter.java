package com.yusong.community.ui.school.teacher.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.SuperBaseAdapter;
import com.yusong.community.ui.school.teacher.bean.StudyVideo;
import com.yusong.community.utils.glide.GlideImgManager;

import java.util.List;


public class CorrelationAdapter extends SuperBaseAdapter {
    public CorrelationAdapter(Activity activity, List dataList) {
        super(activity, dataList);
    }

    @Override
    public int getItemLayoutResId() {
        return R.layout.item_correlation;
    }

    @Override
    public Object getViewHolder(View rootView) {
        return new ViewHolder(rootView);
    }

    @Override
    public void setItemData(Object dataItem, Object viewHolder) {
        StudyVideo studyVideo = (StudyVideo) dataItem;
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.item_name.setText(studyVideo.getVideoName());
        if (studyVideo != null && studyVideo.getImagePath() != null) {
            GlideImgManager.loadImage(context, studyVideo.getImagePath(), holder.item_img);
        } else {
            holder.item_img.setImageResource(R.mipmap.video_big);
        }
        holder.item_teacher.setText(studyVideo.getTeacherName());
        holder.item_teacher.setText(studyVideo.getScore() + "分");
    }

    class ViewHolder {
        public final View root;
        public final ImageView item_img;
        public final TextView item_name;
        public final TextView item_teacher;
        public final TextView item_score;


        public ViewHolder(View root) {
            this.root = root;
            this.item_img = (ImageView) this.root.findViewById(R.id.item_img);
            this.item_name = (TextView) this.root.findViewById(R.id.item_name);
            this.item_teacher = (TextView) this.root.findViewById(R.id.item_teacher);
            this.item_score = (TextView) this.root.findViewById(R.id.item_score);

        }
    }
}
