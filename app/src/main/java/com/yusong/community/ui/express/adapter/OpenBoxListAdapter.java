package com.yusong.community.ui.express.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.express.holder.OpenBoxListHolder;
import com.yusong.community.ui.express.mvp.entity.Order;

import java.util.List;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class OpenBoxListAdapter extends DefaultAdapter<Order> {


    protected OnOpenBoxClickListener mOnOpenBoxClickListener = null;
    public OpenBoxListAdapter(List<Order> mDatas,Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public BaseHolder<Order> getHolder(View v) {
        return new OpenBoxListHolder(v,mContext);
    }

    @Override
    public void onBindViewHolder(BaseHolder<Order> holder, int position) {
        super.onBindViewHolder(holder, position);
        final Order order = mDatas.get(position);
        OpenBoxListHolder hodler = (OpenBoxListHolder) holder;
        hodler.mTvOrderNumber.setText(order.getOrderNo());
        hodler.mTvBoxNumber.setText(order.getBoxNum());
        hodler.mBtnOpenBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnOpenBoxClickListener != null){
                    mOnOpenBoxClickListener.onBtnClick(order);
                }
            }
        });
    }

    public interface OnOpenBoxClickListener {
        void onBtnClick(Order order);
    }

    public void setOnOpenBoxClickListener(OnOpenBoxClickListener listener) {
        this.mOnOpenBoxClickListener = listener;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_express_openbox;
    }
}
