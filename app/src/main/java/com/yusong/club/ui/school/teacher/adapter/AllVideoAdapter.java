package com.yusong.club.ui.school.teacher.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.school.teacher.bean.VideoAlbum;
import com.yusong.club.ui.school.teacher.holder.AllVideoHolder;

import java.util.List;

/**
 * Created by feisher on 2017/3/7.
 */
public class AllVideoAdapter extends DefaultAdapter<VideoAlbum> {
    public AllVideoHolder mHolder;
    public AllVideoAdapter(List<VideoAlbum> noticeDatas, Context mContext) {
        super(noticeDatas, mContext);
    }
    @Override
    public BaseHolder<VideoAlbum> getHolder(View v) {
        mHolder = new AllVideoHolder(v, mContext);
        return mHolder;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_all_photo;
    }

}
