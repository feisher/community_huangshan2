package com.yusong.community.ui.school.school.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.school.mvp.entity.School;
import com.yusong.community.ui.school.school.holder.FindHolder;

import java.util.List;


/**
 * @author Mr_Peng
 * @created at 2017-08-01.
 * @describe: null
 */

public class FindAdapter extends DefaultAdapter<School> {

    public FindAdapter(List<School> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public BaseHolder<School> getHolder(View v) {
        return new FindHolder(v,mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_school_find;
    }
}
