package com.yusong.community.ui.express.holder;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.express.mvp.entity.Order;

import java.util.List;

import butterknife.InjectView;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class OpenBoxListHolder extends BaseHolder<Order> {


    @InjectView(R.id.tv_orderNumber)
    public TextView mTvOrderNumber;
    @InjectView(R.id.tv_order_state)
    public TextView mTvOrderState;
    @InjectView(R.id.tv_boxNumber)
    public TextView mTvBoxNumber;
    @InjectView(R.id.btn_openBox)
    public Button mBtnOpenBox;

    public OpenBoxListHolder(View itemView, Context mContext) {
        super(itemView, mContext);

    }

    @Override
    public void setData(List<Order> datas, int position) {


    }
}
