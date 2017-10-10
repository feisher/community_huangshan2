package com.yusong.club.ui.shoppers.used.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.shoppers.used.holder.UsedImageHolder;

import java.util.List;

/**
 * @author Mr_Peng
 *         crated at on 2017/3/22.
 *         描述: 二手详情图片展示
 */

public class UsedImageAdapter extends DefaultAdapter<String> {

    @Override
    public BaseHolder<String> getHolder(View v) {
        return new UsedImageHolder(v, mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_used_image;
    }

    public UsedImageAdapter(List<String> mDatas, Context context) {
        super(mDatas, context);
    }
}
