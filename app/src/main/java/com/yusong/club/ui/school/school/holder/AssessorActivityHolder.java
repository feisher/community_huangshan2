package com.yusong.club.ui.school.school.holder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.school.mvp.entity.ApplyRole;
import com.yusong.club.ui.school.school.activity.RoleApplyDetailActivity;

import java.util.List;

import butterknife.InjectView;

/**
 * create by feisher on 2017/3/9
 * Email：458079442@qq.com
 */
public class AssessorActivityHolder extends BaseHolder<ApplyRole> {
    @InjectView(R.id.item_day)
    TextView itemDay;
    @InjectView(R.id.item_month)
    TextView itemMonth;
    @InjectView(R.id.item_title)
    TextView itemTitle;
    @InjectView(R.id.item_content)
    TextView itemContent;
    @InjectView(R.id.item_no)
    Button itemNo;
    public AssessorActivityHolder(View itemView, Context mContext) {
        super(itemView,mContext);
    }
    @Override
    public void setData(List<ApplyRole> datas, int position) {
        try {
            final ApplyRole applyRole = datas.get(position);
            switch (applyRole.getType()) {
                case 1:
                    itemTitle.setText("家长申请");
                    itemContent.setText(applyRole.getClazzName()+"的学生家长"+applyRole.getUserName()+"申请审批账号");
                    break;
                case 2:
                    itemTitle.setText("老师申请移动APP账号开通");
                    itemContent.setText(applyRole.getClazzName()+"的老师"+applyRole.getUserName()+"申请审批账号");
                    break;
            }
            String applyTime = applyRole.getCreateTime();
            String[] split = applyTime.split("-");
            itemDay.setText(split[2].substring(0,2));
            itemMonth.setText(split[1]+"月");

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent mIntent = new Intent(mContext,RoleApplyDetailActivity.class);
                    mIntent.putExtra("applyRole",applyRole);
                    mContext.startActivity(mIntent);
                }
            });
            itemNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent mIntent = new Intent(mContext,RoleApplyDetailActivity.class);
                    mIntent.putExtra("applyRole",applyRole);
                    mContext.startActivity(mIntent);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
