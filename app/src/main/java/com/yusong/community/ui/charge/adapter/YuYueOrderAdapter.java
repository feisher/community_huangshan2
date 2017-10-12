package com.yusong.community.ui.charge.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.charge.bean.SancContentBean;
import com.yusong.community.ui.charge.holder.YuYueOrderHodler;

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
