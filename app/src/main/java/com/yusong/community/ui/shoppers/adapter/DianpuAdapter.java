package com.yusong.community.ui.shoppers.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.shoppers.bean.CommodityBean;
import com.yusong.community.ui.shoppers.holder.DianpuHolder;

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
