package com.yusong.club.ui.school.teacher.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.school.mvp.entity.TeacherLeave;
import com.yusong.club.ui.school.teacher.holder.ShowLeaveHolder;

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
