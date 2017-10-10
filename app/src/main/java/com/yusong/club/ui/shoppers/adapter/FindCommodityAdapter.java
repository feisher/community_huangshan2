package com.yusong.club.ui.shoppers.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.shoppers.bean.CommodityBean;
import com.yusong.club.ui.shoppers.holder.FindCommodityHolder;

import java.util.List;

/**
 * @author Mr_Peng
 * @created at 2017-09-18.
 * @describe: null
 */

public class FindCommodityAdapter extends DefaultAdapter<CommodityBean> {

    public FindCommodityAdapter(List<CommodityBean> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public BaseHolder<CommodityBean> getHolder(View v) {
        return new FindCommodityHolder(v, mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_school_find;//重用布局
    }
}
