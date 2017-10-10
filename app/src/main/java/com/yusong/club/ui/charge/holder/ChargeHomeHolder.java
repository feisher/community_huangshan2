package com.yusong.club.ui.charge.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.club.R;


/**
 * Created by Mr_Peng on 2017/1/11.
 */

public class ChargeHomeHolder extends RecyclerView.ViewHolder {
    public ImageView iv_itemImg;
    public TextView tv_itemName;
    public LinearLayout ll_items;

    public ChargeHomeHolder(View itemView) {
        super(itemView);
        ll_items = (LinearLayout) itemView.findViewById(R.id.charge_home_item);
        iv_itemImg = (ImageView) itemView.findViewById(R.id.iv_itemImg);
        tv_itemName = (TextView) itemView.findViewById(R.id.tv_itemName);
    }
}
