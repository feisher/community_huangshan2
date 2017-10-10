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

public class TenementHistoryAdapter extends DefaultAdapter<TenementBean> {

    @Override
    public BaseHolder<TenementBean> getHolder(View v) {
        return new TenementHistoryHolder(v, mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_tenement_history;
    }

    public TenementHistoryAdapter(List<TenementBean> mDatas, Context context) {
        super(mDatas, context);
    }
}
