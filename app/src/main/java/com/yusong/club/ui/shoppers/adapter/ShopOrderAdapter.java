package com.yusong.club.ui.shoppers.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.shoppers.bean.OrderShopBean;
import com.yusong.club.ui.shoppers.holder.ShopOrderHolder;

import java.util.List;

/**
 * @author Mr_Peng
 *         created at 2017/3/14 13:11.
 */

public class ShopOrderAdapter extends DefaultAdapter<OrderShopBean> {

    public ShopOrderAdapter(List<OrderShopBean> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public BaseHolder<OrderShopBean> getHolder(View v) {
        return new ShopOrderHolder(v, mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_shop_order;
    }
}
