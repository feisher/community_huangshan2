package com.yusong.club.ui.school.teacher.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.school.teacher.bean.TelBook;
import com.yusong.club.ui.school.teacher.holder.TeacherHolder;

import java.util.List;

/**
 * Created by admin on 2017/1/13.
 */
public class TeacherAdapter extends DefaultAdapter<TelBook.TeacherListBean> {


    public TeacherAdapter(List<TelBook.TeacherListBean> mDatas,Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public BaseHolder<TelBook.TeacherListBean> getHolder(View v) {
        return new TeacherHolder(v,mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_teacher_teacher;
    }
}
