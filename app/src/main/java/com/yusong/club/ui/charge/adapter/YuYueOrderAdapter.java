package com.yusong.club.ui.charge.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.charge.bean.SancContentBean;
import com.yusong.club.ui.charge.holder.YuYueOrderHodler;

import java.util.List;

/**
 * Created by Mr_Peng on 2017/2/22.
 */

public class YuYueOrderAdapter extends DefaultAdapter<SancContentBean> {

    public YuYueOrderAdapter(List<SancContentBean> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public BaseHolder<SancContentBean> getHolder(View v) {
        return new YuYueOrderHodler(v, mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_charge_yuyueorder;
    }
}
