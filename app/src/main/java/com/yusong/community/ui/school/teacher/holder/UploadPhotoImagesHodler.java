package com.yusong.community.ui.school.teacher.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.utils.glide.GlideImgManager;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by feisher on 2017/1/19.
 */
public class UploadPhotoImagesHodler extends BaseHolder<String> {
    @InjectView(R.id.iv_itemImage)
    ImageView ivItemImage;
    public UploadPhotoImagesHodler(View v, Context mContext) {
        super(v,mContext);
    }

    @Override
    public void setData(List<String> datas, int position) {
        GlideImgManager.loadImage(mContext,datas.get(position),ivItemImage);
//        ivItemImage.setImageURI(Uri.parse(datas.get(position)));
    }
}
