package com.yusong.club.ui.school.teacher.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yusong.club.R;
import com.yusong.club.ui.school.mvp.entity.LatestPhoto;
import com.yusong.club.ui.school.teacher.view.RectImageView;
import com.yusong.club.utils.glide.GlideImgManager;

import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/29.
 */

public class LatestImageAdapter extends BaseAdapter {
    private Context mContext;
    private List<LatestPhoto.ImageListBean> images;

    public LatestImageAdapter(Context mContext, List<LatestPhoto.ImageListBean> images) {
        this.mContext = mContext;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public LatestPhoto.ImageListBean getItem(int i) {
        return images.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_latest_photo_img, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (getItem(i) != null && !StringUtils.isEmpty(getItem(i).getImagePath())) {
            GlideImgManager.loadImage(mContext, getItem(i).getImagePath(), viewHolder.item_img);
        }
        return view;
    }

    protected class ViewHolder {
        View rootView;
        RectImageView item_img;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            item_img = (RectImageView) rootView.findViewById(R.id.item_img);
        }
    }


}
