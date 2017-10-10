package com.yusong.club.ui.shoppers.used.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.utils.glide.GlideImgManager;

import java.util.List;

import butterknife.InjectView;

/**
 * @author Mr_Peng
 *         crated at on 2017/3/22.
 *         描述: null
 */

public class UsedImageHolder extends BaseHolder<String> {
    @Override
    public void setData(List<String> datas, int position) {
        GlideImgManager.loadImage(mContext, datas.get(position), usedImage);
    }

    @InjectView(R.id.used_image)
    ImageView usedImage;

    public UsedImageHolder(View itemView, Context context) {
        super(itemView, context);
    }
}
