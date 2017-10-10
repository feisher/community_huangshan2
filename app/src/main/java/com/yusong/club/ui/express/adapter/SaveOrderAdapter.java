package com.yusong.club.ui.express.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.express.holder.SaveOrderHolder;
import com.yusong.club.ui.express.mvp.entity.SaveOrderInfo;
import com.yusong.club.utils.CacheUtils;

import java.util.List;

/**
 * <ul>
 * <li>功能说明：</li>
 * <li>作者：杨文新 </li>
 * </ul>
 */

public class SaveOrderAdapter extends DefaultAdapter<SaveOrderInfo> {

    public SaveOrderAdapter(List<SaveOrderInfo> mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public BaseHolder<SaveOrderInfo> getHolder(View v) {
        return new SaveOrderHolder(v, mContext);
    }


    @Override
    public void onBindViewHolder(BaseHolder<SaveOrderInfo> holder, final int position) {
        super.onBindViewHolder(holder, position);
        SaveOrderHolder mSaveOrderHolder = (SaveOrderHolder) holder;
        mSaveOrderHolder.setData(mDatas, position);
        mSaveOrderHolder.mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnBtnClickListener != null) {
                    mOnBtnClickListener.onClick(CacheUtils.getToken(mContext), 2, mDatas.get(position).getId());
                }
            }
        });

    }

    OnBtnClickListener mOnBtnClickListener;

    public interface OnBtnClickListener {
        void onClick(String token, int type, String id);
    }

    public void setOnBtnClickListener(OnBtnClickListener listener) {
        this.mOnBtnClickListener = listener;
    }


    @Override
    public int getLayoutId() {
        return R.layout.item_express_save_order;
    }
}
