package com.yusong.community.ui.school.teacher.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.school.teacher.bean.GroupPeople;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by admin on 2017/1/13.
 */
public class GroupHolder extends BaseHolder<GroupPeople> {

    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.tv_count)
    TextView tvCount;

    public GroupHolder(View itemView,Context mContext) {
        super(itemView, mContext);
    }

    @Override
    public void setData(List<GroupPeople> datas, int position) {
        tvName.setText(datas.get(position).getName());
        tvCount.setText(datas.get(position).getCount());
    }
}
