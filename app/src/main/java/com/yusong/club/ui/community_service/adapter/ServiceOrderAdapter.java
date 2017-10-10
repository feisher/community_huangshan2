package com.yusong.club.ui.community_service.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.community_service.entity.ServiceOrderBean;

import java.util.List;

/**
 * @author Mr_Peng
 *         created at 2017/3/14 13:11.
 */

public class ServiceOrderAdapter extends DefaultAdapter<ServiceOrderBean> {

    public ServiceOrderAdapter(List<ServiceOrderBean> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public BaseHolder<ServiceOrderBean> getHolder(View v) {
        return new ServiceOrderHolder(v, mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_shop_order;
    }
}
