package com.yusong.community.ui.school.teacher.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.school.teacher.bean.TelBook;
import com.yusong.community.ui.school.teacher.holder.StudentHolder;

import java.util.List;

/**
 * Created by admin on 2017/1/13.
 */
public class StudentAdapter extends DefaultAdapter<TelBook.StudentListBean> {


    public StudentAdapter(List<TelBook.StudentListBean> mDatas,Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public BaseHolder<TelBook.StudentListBean> getHolder(View v) {
        return new StudentHolder(v,mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_teacher_student;
    }
}
