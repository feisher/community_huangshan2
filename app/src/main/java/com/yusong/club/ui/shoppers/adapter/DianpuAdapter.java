package com.yusong.club.ui.shoppers.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.shoppers.bean.CommodityBean;
import com.yusong.club.ui.shoppers.holder.DianpuHolder;

import java.util.List;

/**
 * @author Mr_Peng
 *         created at 2017/3/15 10:04.
 */

public class DianpuAdapter extends DefaultAdapter<CommodityBean> {

    public DianpuAdapter(List<CommodityBean> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public BaseHolder<CommodityBean> getHolder(View v) {
        return new DianpuHolder(v, mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_dianpu;
    }
}
