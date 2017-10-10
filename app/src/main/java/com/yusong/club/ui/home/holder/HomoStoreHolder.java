package com.yusong.club.ui.home.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.club.R;

/**
 * Created by quaner on 16/12/27.
 */

public class HomoStoreHolder extends RecyclerView.ViewHolder {


    public ImageView mIvCommodity;

    public TextView mTvCommodityName;

    public TextView mTvPrice;

    public TextView mTvYj;

    public TextView mTvYjj;
    public LinearLayout mLl_commodity;

    public HomoStoreHolder(View itemView) {
        super(itemView);
        mIvCommodity = (ImageView) itemView.findViewById(R.id.iv_commodity);
        mTvCommodityName = (TextView) itemView.findViewById(R.id.tv_commodityName);
        mTvPrice = (TextView) itemView.findViewById(R.id.tv_price);
        mTvYj = (TextView) itemView.findViewById(R.id.tv_yj);
        mTvYjj = (TextView) itemView.findViewById(R.id.tv_yjj);
        mLl_commodity = (LinearLayout) itemView.findViewById(R.id.ll_commodity);
    }

}
