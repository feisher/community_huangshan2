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
 * Created by ds on 2017/1/13.
 */
public class DetailImageHolder extends BaseHolder<String> {
    @InjectView(R.id.item_img)
    ImageView itemImg;
    public DetailImageHolder(View itemView, Context mContext) {
        super(itemView, mContext);
    }

    @Override
    public void setData(List<String> datas, int position) {
        if (datas.size() != 0) {
            GlideImgManager.loadImage(mContext, datas.get(position), itemImg);
        }
    }
}
