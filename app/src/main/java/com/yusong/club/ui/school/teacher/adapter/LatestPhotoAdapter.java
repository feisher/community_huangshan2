package com.yusong.club.ui.school.teacher.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.school.mvp.entity.LatestPhoto;
import com.yusong.club.ui.school.teacher.holder.LatestPhotoHolder;

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
