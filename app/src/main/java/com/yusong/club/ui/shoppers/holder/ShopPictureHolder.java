package com.yusong.club.ui.shoppers.holder;

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
 *         crated at on 2017/5/24.
 *         描述: null
 */

public class ShopPictureHolder extends BaseHolder<String> {
    @InjectView(R.id.shop_picture)
    ImageView shopPicture;

    public ShopPictureHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void setData(List<String> datas, int position) {
        GlideImgManager.loadImage(mContext, datas.get(position), shopPicture);
    }
}
