package com.yusong.club.ui.school.teacher.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.school.mvp.entity.TeacherLeave;
import com.yusong.club.ui.school.teacher.holder.TeacherLeaveActivityHolder;

import java.util.List;

/**
 * create by feisher on 2017/3/9
 * Email：458079442@qq.com
 */
public class TeacherLeaveActivityAdapter extends DefaultAdapter<TeacherLeave> {

    private TeacherLeaveActivityHolder.JudgeLeave judgeLeavejudgeLeave;

    public void setJudgeLeavejudgeLeave(TeacherLeaveActivityHolder.JudgeLeave judgeLeavejudgeLeave) {
        this.judgeLeavejudgeLeave = judgeLeavejudgeLeave;
    }

    public TeacherLeaveActivityAdapter(List<TeacherLeave> data, Context mContext) {
        super(data, mContext);
    }

    @Override
    public BaseHolder<TeacherLeave> getHolder(View v) {
        TeacherLeaveActivityHolder teacherLeaveActivityHolder = new TeacherLeaveActivityHolder(v, mContext);
        teacherLeaveActivityHolder.setmJudgeLeave(this.judgeLeavejudgeLeave);
        return teacherLeaveActivityHolder;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_teacher_leave;
    }
}