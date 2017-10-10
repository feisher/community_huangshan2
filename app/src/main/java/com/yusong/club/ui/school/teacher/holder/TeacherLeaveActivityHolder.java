package com.yusong.club.ui.school.teacher.holder;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.school.mvp.entity.TeacherLeave;
import com.yusong.club.utils.DateUtil;
import com.yusong.club.utils.glide.GlideImgManager;

import java.util.List;

import butterknife.InjectView;

import static android.view.View.VISIBLE;

/**
 * create by feisher on 2017/3/9
 * Email：458079442@qq.com
 */
public class TeacherLeaveActivityHolder extends BaseHolder<TeacherLeave> {
    @InjectView(R.id.iv_header)
    ImageView ivHeader;
    @InjectView(R.id.item_title)
    TextView itemTitle;
    @InjectView(R.id.item_content)
    TextView itemContent;
    @InjectView(R.id.item_status)
    TextView itemStatus;
    @InjectView(R.id.item_no)
    Button itemNo;
    @InjectView(R.id.item_time)
    TextView itemTime;
    private JudgeLeave mJudgeLeave;
    @InjectView(R.id.item_yes)
    Button itemYes;

    public void setmJudgeLeave(JudgeLeave mJudgeLeave) {
        this.mJudgeLeave = mJudgeLeave;
    }

    public TeacherLeaveActivityHolder(View itemView, Context mContext) {
        super(itemView, mContext);
    }

    public interface JudgeLeave {
        void judgeLeaveYes(int pos);

        void judgeLeaveNo(int pos);
    }

    @Override
    public void setData(List<TeacherLeave> datas, final int position) {
        try {
            TeacherLeave teacherLeave = datas.get(position);
            itemTitle.setText("请假条");
            itemContent.setText(teacherLeave.getReason());
            if (teacherLeave != null && teacherLeave.getAuditUserIconPath() != null) {
                GlideImgManager.loadCircleImage(mContext, teacherLeave.getAuditUserIconPath(), ivHeader);
            }
            if (datas.get(position) != null && datas.get(position).getBeginTime() != null) {
                itemTime.setText(DateUtil.getHidatWeek(datas.get(position).getBeginTime()) + "至" + DateUtil.getHidatWeek(datas.get(position).getEndTime()));
            } else {
                itemTime.setText("");
            }
            switch (teacherLeave.getStatus()) {
                case 1:
                    itemNo.setVisibility(VISIBLE);
                    itemYes.setVisibility(VISIBLE);
                    itemStatus.setVisibility(View.GONE);
                    break;
                case 2:
                    itemNo.setVisibility(View.GONE);
                    itemYes.setVisibility(View.GONE);
                    itemStatus.setVisibility(VISIBLE);
                    itemStatus.setText("审核通过");
                    break;
                case 3:
                    itemNo.setVisibility(View.GONE);
                    itemYes.setVisibility(View.GONE);
                    itemStatus.setVisibility(VISIBLE);
                    itemStatus.setText("审核未通过");
                    break;
            }
            itemNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mJudgeLeave != null) {
                        mJudgeLeave.judgeLeaveNo(position);
                    }
                }
            });
            itemYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mJudgeLeave != null) {
                        mJudgeLeave.judgeLeaveYes(position);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
