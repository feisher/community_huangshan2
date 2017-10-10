package com.yusong.club.ui.express.holder;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.express.mvp.entity.GetOrderInfo;

import java.util.List;

import butterknife.InjectView;
/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class GetOrderHolder extends BaseHolder<GetOrderInfo> {


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


    public GetOrderHolder(View itemView, Context mContext) {
        super(itemView, mContext);
    }

    @Override
    public void setData(List<GetOrderInfo> datas, int position) {

        final GetOrderInfo orderInfo = datas.get(position);
        if (position == 0){
            mView1.setVisibility(View.GONE);
        }else {
            mView1.setVisibility(View.VISIBLE);
        }

        mTvOrderNumber.setText("订单编号："+orderInfo.getOrderNo());
        mTvOrderState.setText(orderInfo.getOrderStatusName());
        mTvBoxNumber.setText(orderInfo.getBoxNum());
        mTvOrderTime.setText(orderInfo.getCreateTime());
        mTvAddress.setText(orderInfo.getAddress());
        mTvAuthCode.setText(orderInfo.getAuthCode()+"");
    }
}
