package com.yusong.community.ui.school.teacher.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.school.mvp.entity.LatestPhoto;
import com.yusong.community.ui.school.teacher.holder.LatestPhotoHolder;

import java.util.List;

/**
 * Created by feisher on 2017/3/7.
 */
public class LatestPhotoAdapter extends DefaultAdapter<LatestPhoto> {
    public LatestPhotoHolder mHolder;

    public LatestPhotoAdapter(List<LatestPhoto> noticeDatas, Context mContext) {
        super(noticeDatas, mContext);
    }

    @Override
    public BaseHolder<LatestPhoto> getHolder(View v) {
        mHolder = new LatestPhotoHolder(v, mContext);
        return mHolder;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_latest_photo;
    }

}
