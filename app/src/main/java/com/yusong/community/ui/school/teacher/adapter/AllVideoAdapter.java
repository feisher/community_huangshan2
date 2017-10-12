package com.yusong.community.ui.school.teacher.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.school.teacher.bean.VideoAlbum;
import com.yusong.community.ui.school.teacher.holder.AllVideoHolder;

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
