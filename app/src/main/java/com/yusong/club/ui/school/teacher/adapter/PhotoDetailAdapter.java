package com.yusong.club.ui.school.teacher.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.yusong.club.R;
import com.yusong.club.ui.base.SuperBaseAdapter;
import com.yusong.club.ui.school.teacher.bean.PhotoAlbum;
import com.yusong.club.utils.glide.GlideImgManager;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/20.
 */

public class PhotoDetailAdapter extends SuperBaseAdapter<PhotoAlbum.ImageListBean> {

    public PhotoDetailAdapter(Activity activity, List dataList) {
        super(activity, dataList);
    }

    @Override
    public int getItemLayoutResId() {
        return R.layout.item_photo_detail_img;
    }

    @Override
    public Object getViewHolder(View rootView) {
        return new ViewHolder(rootView);
    }


    @Override
    public void setItemData(PhotoAlbum.ImageListBean dataItem, Object viewHolder) {
        ViewHolder holder = (ViewHolder) viewHolder;
        GlideImgManager.loadImage(context, dataItem.getImagePath(), holder.item_img);
    }

    class ViewHolder {
        public final View root;
        public final ImageView item_img;

        public ViewHolder(View root) {
            this.root = root;
            this.item_img = (ImageView) this.root.findViewById(R.id.iv_itemImage);

        }
    }
}
