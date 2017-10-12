package com.yusong.community.ui.express.holder;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.express.mvp.entity.SaveOrderInfo;

import java.util.List;

import butterknife.InjectView;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class SaveOrderHolder extends BaseHolder<SaveOrderInfo> {

    @InjectView(R.id.view1)
    View mView1;
    @InjectView(R.id.tv_orderNumber)
    TextView mTvOrderNumber;
    @InjectView(R.id.tv_order_state)
    TextView mTvOrderState;
    @InjectView(R.id.tv_boxNumber)
    TextView mTvBoxNumber;
    @InjectView(R.id.tv_orderTime)
    TextView mTvOrderTime;
    @InjectView(R.id.tv_address)
    TextView mTvAddress;
    @InjectView(R.id.tv_authCode)
    TextView mTvAuthCode;
    @InjectView(R.id.tv_qu)
    TextView mTvQu;
    @InjectView(R.id.btn)
    public Button mBtn;
    @InjectView(R.id.view2)
    View mView2;
    @InjectView(R.id.open_layout)
    LinearLayout openLayout;

    public SaveOrderHolder(View itemView, Context mContext) {
        super(itemView, mContext);
    }

    @Override
    public void setData(List<SaveOrderInfo> datas, int position) {

        final SaveOrderInfo orderInfo = datas.get(position);

        if (position == 0) {
            mView1.setVisibility(View.GONE);
        } else {
            mView1.setVisibility(View.VISIBLE);
        }
        int status = orderInfo.getOrderStatus();
        if (status < 3 && status > 1) {
            mTvAuthCode.setText(orderInfo.getPutAuthCode());
            mTvQu.setText("存包码");
        } else {
            mTvQu.setText("取件码");
            mTvAuthCode.setText(orderInfo.getTakeAuthCode());
        }

        if ("已付款".equals(orderInfo.getOrderStatusName())) {
            mTvAuthCode.setText(orderInfo.getPutAuthCode());
            mTvQu.setText("存包码");
        }
        if (orderInfo.getOrderStatus() == 2) {
            openLayout.setVisibility(View.VISIBLE);
        } else {
            openLayout.setVisibility(View.GONE);
        }

        mTvOrderNumber.setText("订单编号：" + orderInfo.getId());
        mTvOrderState.setText(orderInfo.getOrderStatusName());
        mTvBoxNumber.setText(orderInfo.getBoxNum());
        mTvOrderTime.setText(orderInfo.getCreateTime());
        mTvAddress.setText(orderInfo.getAddress());

    }

}
