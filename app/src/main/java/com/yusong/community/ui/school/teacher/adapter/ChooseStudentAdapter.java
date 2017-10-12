package com.yusong.community.ui.school.teacher.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.school.mvp.entity.StuList;
import com.yusong.community.ui.school.teacher.holder.ChooseStudentHolder;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by feisher on 2017/3/7.
 */
public class ChooseStudentAdapter extends DefaultAdapter<StuList> {
    @InjectView(R.id.item_delete)
    LinearLayout itemDelete;
    public ChooseStudentHolder mHolder;

    public ChooseStudentAdapter(List<StuList> noticeDatas, Context mContext) {
        super(noticeDatas, mContext);
    }

    @Override
    public BaseHolder<StuList> getHolder(View v) {
        mHolder = new ChooseStudentHolder(v, mContext);
        return mHolder;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_teacher_student;
    }

}
