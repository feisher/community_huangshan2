package com.yusong.community.ui.express.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.express.event.QueryLogistics;
import com.yusong.community.ui.express.holder.SenderOrderHolder;
import com.yusong.community.ui.express.mvp.entity.SenderOrderInfo;
import com.yusong.community.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class SenderOrderAdapter extends DefaultAdapter<SenderOrderInfo> {

    public SenderOrderHolder mHodler;

    public SenderOrderAdapter(List<SenderOrderInfo> mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public BaseHolder<SenderOrderInfo> getHolder(View v) {
        mHodler = new SenderOrderHolder(v, mContext);
        return mHodler;
    }

    @Override
    public void onBindViewHolder(BaseHolder<SenderOrderInfo> holder,final int position) {

        SenderOrderHolder hodler = (SenderOrderHolder) holder;
        super.onBindViewHolder(holder, position);
        if (position == 0) {
            hodler.mView1.setVisibility(View.GONE);
        } else {
            hodler.mView1.setVisibility(View.VISIBLE);
        }

        final SenderOrderInfo orderInfo = mDatas.get(position);
        hodler.mTvOrderNumber.setText("订单编号：" + orderInfo.getId());
        hodler.mTvOrderState.setText(orderInfo.getOrderStatusName());
        hodler.mTvName.setText(orderInfo.getReceiver());
        hodler.mTvAddress.setText(orderInfo.getReceiverAddress());
        hodler.mTvPhone.setText(orderInfo.getReceiverMobile());
        final int status = orderInfo.getOrderStatus();
        hodler.mBtnDelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status < 2) {
                    //取消订单
                    if (mBtnClickLisener != null) {
                        mBtnClickLisener.btnClick(orderInfo.getId(),position);
                    }

                } else {
                    ToastUtils.showShort(mContext, "订单不可取消");
                }
            }
        });

        hodler.mBtnLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status > 1) {
                    EventBus.getDefault().post(new QueryLogistics(orderInfo.getOrderNo()));
                } else {
                    ToastUtils.showShort(mContext, "暂无物流信息");
                }
            }
        });



    }

    public BtnClickLisener mBtnClickLisener;

    public void setBtnClickLisener(BtnClickLisener btnClickLisener) {
        mBtnClickLisener = btnClickLisener;
    }

    public interface BtnClickLisener {
        void btnClick(String id, int position);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_express_sender_order;
    }
}
