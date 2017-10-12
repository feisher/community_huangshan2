package com.yusong.community.ui.school.teacher.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.SuperBaseAdapter;
import com.yusong.community.ui.school.teacher.bean.VideoAlbum;
import com.yusong.community.utils.DateUtil;
import com.yusong.community.utils.glide.GlideImgManager;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/20.
 */

public class VideoDetailAdapter extends SuperBaseAdapter<VideoAlbum.VideoListBean> {


    public VideoDetailAdapter(Activity activity, List dataList) {
        super(activity, dataList);
    }

    @Override
    public int getItemLayoutResId() {
        return R.layout.item_video_detail_img;
    }

    @Override
    public Object getViewHolder(View rootView) {
        return new ViewHolder(rootView);
    }


    @Override
    public void setItemData(VideoAlbum.VideoListBean dataItem, Object viewHolder) {
        ViewHolder holder = (ViewHolder) viewHolder;
        if (dataItem.getImagePath() != null) {

            GlideImgManager.loadVideoImage(context, dataItem.getImagePath(), holder.ivItemvido);
        }
        if (dataItem.getCreateTime() != null) {
            holder.tvName.setText(DateUtil.getHidatenew(dataItem.getCreateTime()));
        }
    }

    class ViewHolder {
        public final View root;
        public final ImageView ivItemvido;
        TextView tvName;

        public ViewHolder(View root) {
            this.root = root;
            this.ivItemvido = (ImageView) this.root.findViewById(R.id.iv_itemvido);
            this.tvName = (TextView) this.root.findViewById(R.id.tv_name);
        }
    }
}
