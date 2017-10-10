package com.yusong.club.ui.repairs;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;

import java.util.List;


/**
 * @author Mr_Peng
 * @created at 2017-09-01.
 * @describe: null
 */

public class RepairsHistoryAdapter extends DefaultAdapter<RepairsHistoryBean> {

    public RepairsHistoryAdapter(List<RepairsHistoryBean> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public BaseHolder<RepairsHistoryBean> getHolder(View v) {
        return new RepairsHistoryHolder(v,mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_repairs_history;
    }
}
