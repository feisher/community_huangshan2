package com.yusong.club.ui.school.teacher.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.school.teacher.bean.GroupPeople;
import com.yusong.club.ui.school.teacher.holder.GroupHolder;

import java.util.List;

/**
 * Created by admin on 2017/1/13.
 */
public class GroupAdapter extends DefaultAdapter<GroupPeople> {

    public GroupAdapter(List<GroupPeople> mDatas,Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public BaseHolder<GroupPeople> getHolder(View v) {
        return new GroupHolder(v,mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_teacher_group;
    }
}
