package com.yusong.community.ui.school.school.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.school.mvp.entity.School;

import java.util.List;

import butterknife.InjectView;

/**
 * @author Mr_Peng
 * @created at 2017-08-01.
 * @describe: null
 */

public class FindHolder extends BaseHolder<School> {

    @InjectView(R.id.find_tv)
    TextView findTv;

    public FindHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void setData(List<School> datas, int position) {
        findTv.setText(datas.get(position).getSchoolName());
    }
}
