package com.yusong.club.ui.school.teacher.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.school.school.bean.StudentComment;
import com.yusong.club.ui.school.teacher.holder.EvaluateHolder;

import java.util.List;

/**
 * Created by admin on 2017/1/13.
 */
public class EvaluateAdapter extends DefaultAdapter<StudentComment> {


    public EvaluateAdapter(List<StudentComment> mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public BaseHolder<StudentComment> getHolder(View v) {
        return new EvaluateHolder(v, mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_teacher_evaluate;
    }
}
