package com.yusong.club.ui.school.teacher.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.school.teacher.bean.PhotoAlbum;
import com.yusong.club.utils.glide.GlideImgManager;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by feisher on 2017/3/7.
 */
public class AllPhotoHolder extends BaseHolder<PhotoAlbum> {
    @InjectView(R.id.iv_header)
    ImageView ivHeader;
    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.tv_num)
    TextView tvNum;
    @InjectView(R.id.iv_go)
    ImageView ivGo;

    public AllPhotoHolder(View v, Context mContext) {
        super(v, mContext);
    }

    @Override
    public void setData(final List<PhotoAlbum> datas, final int position) {
        PhotoAlbum photoAlbum = datas.get(position);
        if (photoAlbum.getImageList() != null && photoAlbum.getImageList().size()!= 0&&photoAlbum.getImageList()
                .get(0)!=null) {
            GlideImgManager.loadImage(mContext, photoAlbum.getImageList()
                            .get(0)
                            .getImagePath()
                    , ivHeader);
        } else {
            GlideImgManager.loadImage(mContext, ""
                    , ivHeader);
        }

        tvName.setText(photoAlbum.getAlbumName());
        tvNum.setText(photoAlbum.getAmount() + "张");


    }

}
