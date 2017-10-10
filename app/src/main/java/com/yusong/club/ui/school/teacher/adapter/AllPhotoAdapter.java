package com.yusong.club.ui.school.teacher.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.school.teacher.bean.PhotoAlbum;
import com.yusong.club.ui.school.teacher.holder.AllPhotoHolder;

import java.util.List;

/**
 * Created by feisher on 2017/3/7.
 */
public class AllPhotoAdapter extends DefaultAdapter<PhotoAlbum> {
    public AllPhotoHolder mHolder;

    public AllPhotoAdapter(List<PhotoAlbum> noticeDatas, Context mContext) {
        super(noticeDatas, mContext);
    }

    @Override
    public BaseHolder<PhotoAlbum> getHolder(View v) {
        mHolder = new AllPhotoHolder(v, mContext);
        return mHolder;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_all_photo;
    }

}
