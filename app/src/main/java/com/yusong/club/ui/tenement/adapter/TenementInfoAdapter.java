package com.yusong.club.ui.tenement.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.tenement.entity.TenementBean;

import java.util.List;

/**
 * @author Mr_Peng
 * @created at 2017-09-04.
 * @describe: null
 */

public class TenementInfoAdapter extends DefaultAdapter<TenementBean> {

    public TenementInfoAdapter(List<TenementBean> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public BaseHolder<TenementBean> getHolder(View v) {

        return new TenementInfoHolder(v, mContext);
    }

    @Override
    public void onBindViewHolder(BaseHolder<TenementBean> holder, int position) {
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_tenement_pay_info;
    }
}
