package com.yusong.community.ui.school.teacher.holder;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.school.teacher.bean.Schedule;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by admin on 2017/1/12.
 */
public class ScheduleHolder extends BaseHolder<Schedule>{
    @InjectView(R.id.item_subject)
    TextView itemSubject;

    @InjectView(R.id.linear_subject)
    LinearLayout linearSubject;
    public ScheduleHolder(View itemView,Context mContext) {
        super(itemView, mContext);
    }

    @Override
    public void setData(List<Schedule> datas, int position) {
        itemSubject.setText(datas.get(position).getSubject());
        if (datas.get(position).isTagId()){
            linearSubject.setBackgroundColor(Color.parseColor("#ffe9cc"));
            itemSubject.setTextColor(Color.parseColor("#FF9001"));
        }else {
            linearSubject.setBackgroundColor(Color.parseColor("#ffffff"));
            itemSubject.setTextColor(Color.parseColor("#888888"));
        }

    }
}
