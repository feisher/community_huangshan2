package com.yusong.community.ui.school.teacher.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.school.teacher.bean.PhotoAlbum;
import com.yusong.community.ui.school.teacher.holder.AllPhotoHolder;

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
