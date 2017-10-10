package com.yusong.club.ui.shoppers.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.shoppers.bean.TuiJianBean;
import com.yusong.club.ui.shoppers.holder.TuijianHolder;

import java.util.List;

/**
 * @author Mr_Peng
 *         created at 2017/3/6 9:28.
 */
public class TuijianAdapter extends DefaultAdapter<TuiJianBean.Commodity> {

    @Override
    public BaseHolder<TuiJianBean.Commodity> getHolder(View v) {
        return new TuijianHolder(v,mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_shop_tuijian;
    }

    public TuijianAdapter(List<TuiJianBean.Commodity> mDatas, Context context) {
        super(mDatas, context);
    }
}
