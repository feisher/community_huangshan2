package com.yusong.club.ui.school.teacher.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.school.teacher.holder.DetailImageHolder;

import java.util.List;

/**
 * Created by admin on 2017/1/13.
 */
public class DetailImageAdapter extends DefaultAdapter<String> {


    public DetailImageAdapter(List<String> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public BaseHolder<String> getHolder(View v) {
        return new DetailImageHolder(v, mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_action_detail_img;
    }
}
