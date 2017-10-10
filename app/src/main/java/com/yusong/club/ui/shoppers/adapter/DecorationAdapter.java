package com.yusong.club.ui.shoppers.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.shoppers.bean.CommodityBean;
import com.yusong.club.ui.shoppers.holder.DecorationHolder;

import java.util.List;

/**
 * @author Mr_Peng
 *         created at 2017/3/16 11:02.
 */

public class DecorationAdapter extends DefaultAdapter<CommodityBean> {


    public DecorationAdapter(List<CommodityBean> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public BaseHolder<CommodityBean> getHolder(View v) {
        return new DecorationHolder(v, mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_jiazhuang_layout;
    }
}
