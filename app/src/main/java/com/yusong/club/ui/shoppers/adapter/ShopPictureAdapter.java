package com.yusong.club.ui.shoppers.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.shoppers.holder.ShopPictureHolder;

import java.util.List;


/**
 * @author Mr_Peng
 *         crated at on 2017/5/24.
 *         描述: null
 */

public class ShopPictureAdapter extends DefaultAdapter<String> {

    public ShopPictureAdapter(List<String> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public BaseHolder<String> getHolder(View v) {
        return new ShopPictureHolder(v,mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_shop_picture;
    }
}
