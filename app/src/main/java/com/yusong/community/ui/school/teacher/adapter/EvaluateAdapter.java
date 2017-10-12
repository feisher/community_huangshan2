package com.yusong.community.ui.school.teacher.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.school.school.bean.StudentComment;
import com.yusong.community.ui.school.teacher.holder.EvaluateHolder;

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
