package com.yusong.club.ui.express.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.club.R;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */
public class HomeItemsHolder extends RecyclerView.ViewHolder{

    public ImageView iv_itemImg;
    public TextView tv_itemName;
    public LinearLayout ll_items;
    public HomeItemsHolder(View itemView) {
        super(itemView);
        ll_items = (LinearLayout) itemView.findViewById(R.id.ll_items);
        iv_itemImg = (ImageView) itemView.findViewById(R.id.iv_itemImg);
        tv_itemName = (TextView) itemView.findViewById(R.id.tv_itemName);
    }

}
