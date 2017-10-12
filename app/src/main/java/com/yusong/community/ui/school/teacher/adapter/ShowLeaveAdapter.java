package com.yusong.community.ui.school.teacher.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.school.mvp.entity.TeacherLeave;
import com.yusong.community.ui.school.teacher.holder.ShowLeaveHolder;

import java.util.List;

/**
 * Created by admin on 2017/1/13.
 */
public class ShowLeaveAdapter extends DefaultAdapter<TeacherLeave> {



    public ShowLeaveAdapter(List<TeacherLeave> mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public BaseHolder<TeacherLeave> getHolder(View v) {
        return new ShowLeaveHolder(v, mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_show_leave;
    }
}
