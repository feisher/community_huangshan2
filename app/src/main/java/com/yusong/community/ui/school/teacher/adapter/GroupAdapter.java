package com.yusong.community.ui.school.teacher.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.school.teacher.bean.GroupPeople;
import com.yusong.community.ui.school.teacher.holder.GroupHolder;

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
