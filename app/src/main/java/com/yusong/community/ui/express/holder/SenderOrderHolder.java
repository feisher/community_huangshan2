package com.yusong.community.ui.express.holder;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.express.mvp.entity.SenderOrderInfo;

import java.util.List;

import butterknife.InjectView;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class SenderOrderHolder extends BaseHolder<SenderOrderInfo> {

    @InjectView(R.id.view1)
    public View mView1;
    @InjectView(R.id.tv_orderNumber)
    public TextView mTvOrderNumber;
    @InjectView(R.id.tv_order_state)
    public TextView mTvOrderState;
    @InjectView(R.id.tv_name)
    public TextView mTvName;
    @InjectView(R.id.tv_phone)
    public TextView mTvPhone;
    @InjectView(R.id.tv_address)
    public TextView mTvAddress;
    @InjectView(R.id.btn_delOrder)
    public Button mBtnDelOrder;
    @InjectView(R.id.btn_look)
    public Button mBtnLook;
    @InjectView(R.id.ll_bottomBtn)
    public LinearLayout mLlBottomBtn;

    public SenderOrderHolder(View itemView, Context mContext) {
        super(itemView, mContext);
    }

    @Override
    public void setData(List<SenderOrderInfo> datas, final int position) {


    }


}
