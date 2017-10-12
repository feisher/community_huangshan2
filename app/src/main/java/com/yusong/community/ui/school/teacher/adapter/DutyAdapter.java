package com.yusong.community.ui.school.teacher.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.school.teacher.bean.AllTeacher;
import com.yusong.community.ui.school.teacher.view.SlipButton;

import java.util.ArrayList;
import java.util.List;

public class DutyAdapter extends BaseAdapter {
    private List<AllTeacher> data;
    private LayoutInflater inflater;
    private Context context;
    private SetDuty mSetDuty;

    public SetDuty getmSetDuty() {
        return mSetDuty;
    }

    public void setmSetDuty(SetDuty mSetDuty) {
        this.mSetDuty = mSetDuty;
    }

    public DutyAdapter(List<AllTeacher> data, Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public AllTeacher getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_teacher_duty, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_state = (TextView) convertView.findViewById(R.id.tv_state);
            viewHolder.tv_job = (TextView) convertView.findViewById(R.id.tv_job);
            viewHolder.tv_mobile = (TextView) convertView.findViewById(R.id.tv_mobile);
            viewHolder.slip_button = (SlipButton) convertView.findViewById(R.id.slip_button);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText(getItem(position).getUserName());
        viewHolder.tv_job.setText(getItem(position).getJobName());
        viewHolder.tv_mobile.setText(getItem(position).getMobile());
        if (getItem(position).getOnDuty() == 0) {
            viewHolder.tv_state.setVisibility(View.GONE);
            viewHolder.slip_button.setCheck(false);
        } else {
            viewHolder.tv_state.setVisibility(View.VISIBLE);
            viewHolder.slip_button.setCheck(true);
        }
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.slip_button.SetOnChangedListener(new SlipButton.OnChangedListener() {
            @Override
            public void OnChanged(boolean CheckState) {
                if (CheckState == true) {
                    finalViewHolder.tv_state.setVisibility(View.VISIBLE);
                    if (mSetDuty != null) {
                        mSetDuty.setDuty(position);
                    }
                } else {
                    finalViewHolder.tv_state.setVisibility(View.GONE);
                    if (mSetDuty != null) {
                        mSetDuty.setDuty(position);
                    }
                }
            }
        });
        return convertView;
    }

    public interface SetDuty {
        void setDuty(int pos);

    }

    class ViewHolder {
        TextView tv_name;
        TextView tv_state;
        TextView tv_job;
        TextView tv_mobile;
        SlipButton slip_button;
    }
}
