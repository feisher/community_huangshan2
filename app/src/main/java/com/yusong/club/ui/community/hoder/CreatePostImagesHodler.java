package com.yusong.club.ui.community.hoder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.utils.glide.GlideImgManager;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by feisher on 2017/1/19.
 */
public class CreatePostImagesHodler extends BaseHolder<String> {
    @InjectView(R.id.iv_itemImage)
    ImageView ivItemImage;
    public CreatePostImagesHodler(View v, Context mContext) {
        super(v,mContext);
    }

    @Override
    public void setData(List<String> datas, int position) {
        GlideImgManager.loadImage(mContext,datas.get(position),ivItemImage);
//        ivItemImage.setImageURI(Uri.parse(datas.get(position)));
    }
}
