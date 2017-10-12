package com.yusong.community.ui.school.teacher.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.school.teacher.bean.Schedule;
import com.yusong.community.ui.school.teacher.holder.ScheduleHolder;

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
