package com.yusong.community.ui.school.teacher.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.school.mvp.entity.TeacherLeave;
import com.yusong.community.utils.DateUtil;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by ds on 2017/1/13.
 */
public class ShowLeaveHolder extends BaseHolder<TeacherLeave> {

    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.tv_status)
    TextView tvStatus;
    @InjectView(R.id.tv_reason)
    TextView tvReason;

    public ShowLeaveHolder(View itemView, Context mContext) {
        super(itemView, mContext);
    }

    @Override
    public void setData(List<TeacherLeave> datas, int position) {
        if (datas != null && datas.size() != 0) {
            tvName.setText(datas.get(position).getStudentName() + "申请" + DateUtil.getMinusToStringNew(datas.get(position).getBeginTime(), datas.get(position).getEndTime()) + "假");
            tvReason.setText(datas.get(position).getReason());
            if (datas.get(position).getStatus() == 1) {
                tvStatus.setText("待审核");
            } else if (datas.get(position).getStatus() == 2) {
                tvStatus.setText("审核通过");
            } else if (datas.get(position).getStatus() == 3) {
                tvStatus.setText("审核不通过");
            }
        }
    }
}
