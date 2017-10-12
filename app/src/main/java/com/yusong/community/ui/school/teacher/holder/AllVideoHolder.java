package com.yusong.community.ui.school.teacher.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.school.teacher.bean.VideoAlbum;
import com.yusong.community.utils.glide.GlideImgManager;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by feisher on 2017/3/7.
 */
public class AllVideoHolder extends BaseHolder<VideoAlbum> {
    @InjectView(R.id.iv_header)
    ImageView ivHeader;
    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.tv_num)
    TextView tvNum;
    @InjectView(R.id.iv_go)
    ImageView ivGo;

    public AllVideoHolder(View v, Context mContext) {
        super(v, mContext);
    }

    @Override
    public void setData(final List<VideoAlbum> datas, final int position) {
        VideoAlbum videoAlbum = datas.get(position);
        if (videoAlbum.getVideoList() != null && videoAlbum.getVideoList().size() != 0 && videoAlbum.getVideoList().get(0) != null) {
            GlideImgManager.loadImage(mContext, videoAlbum.getVideoList()
                    .get(0)
                    .getImagePath(), ivHeader);
        } else {
            GlideImgManager.loadImage(mContext, ""
                    , ivHeader);
        }
        tvName.setText(videoAlbum.getAlbumName());
        tvNum.setText(videoAlbum.getAmount() + "张");


    }

}
