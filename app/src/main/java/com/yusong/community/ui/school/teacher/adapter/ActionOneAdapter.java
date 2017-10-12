package com.yusong.community.ui.school.teacher.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.school.school.bean.ActivityBean;
import com.yusong.community.ui.school.teacher.holder.ActionHolder;

import java.util.List;

/**
 * Created by admin on 2017/1/13.
 */
public class ActionOneAdapter extends DefaultAdapter<ActivityBean> {

    private ActionHolder.ActionDo mActionDo;

    public void setmActionDo(ActionHolder.ActionDo mActionDo) {
        this.mActionDo = mActionDo;
    }

    public ActionOneAdapter(List<ActivityBean> mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public BaseHolder<ActivityBean> getHolder(View v) {
        ActionHolder holder = new ActionHolder(v, mContext);
        holder.setActionDo(this.mActionDo);
        return holder;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_teacher_action_one;
    }
}
