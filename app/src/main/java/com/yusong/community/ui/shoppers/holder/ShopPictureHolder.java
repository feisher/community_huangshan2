package com.yusong.community.ui.shoppers.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.utils.glide.GlideImgManager;

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
