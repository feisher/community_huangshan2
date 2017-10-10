package com.yusong.club.ui.school.school.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.school.mvp.entity.ApplyRole;
import com.yusong.club.ui.school.school.holder.AssessorActivityHolder;

import java.util.List;

/**
 * create by feisher on 2017/3/9
 * Email：458079442@qq.com
 */
public class AssessorActivityAdapter extends DefaultAdapter<ApplyRole> {



    public AssessorActivityAdapter(List<ApplyRole> data, Context mContext) {
        super(data, mContext);
    }

    @Override
    public BaseHolder<ApplyRole> getHolder(View v) {
        return new AssessorActivityHolder(v, mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_assessor;
    }
}
