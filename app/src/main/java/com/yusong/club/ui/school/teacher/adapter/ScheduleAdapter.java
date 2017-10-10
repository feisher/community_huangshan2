package com.yusong.club.ui.school.teacher.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.school.teacher.bean.Schedule;
import com.yusong.club.ui.school.teacher.holder.ScheduleHolder;

import java.util.List;


public class ScheduleAdapter extends DefaultAdapter<Schedule> {



    public ScheduleAdapter(List<Schedule> mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public BaseHolder<Schedule> getHolder(View v) {
        return new ScheduleHolder(v, mContext);
    }


    @Override
    public int getLayoutId() {
        return R.layout.item_schedule;
    }
}
