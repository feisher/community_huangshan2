package com.yusong.community.ui.shoppers.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.shoppers.bean.OrderShopBean;
import com.yusong.community.ui.shoppers.holder.ShopOrderHolder;

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
