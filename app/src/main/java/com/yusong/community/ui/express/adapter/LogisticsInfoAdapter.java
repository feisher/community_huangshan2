package com.yusong.community.ui.express.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.express.holder.LogisticsInfoHolder;
import com.yusong.community.ui.express.mvp.entity.OrderLogistics;

import java.util.List;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */
public class LogisticsInfoAdapter extends DefaultAdapter<OrderLogistics.LogisticsInfo> {


    public LogisticsInfoAdapter(List<OrderLogistics.LogisticsInfo> mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public BaseHolder<OrderLogistics.LogisticsInfo> getHolder(View v) {
        return new LogisticsInfoHolder(v,mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_express_logisticsinfo_detailed;
    }
}
