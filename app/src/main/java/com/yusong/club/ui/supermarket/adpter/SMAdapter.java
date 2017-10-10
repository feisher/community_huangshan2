package com.yusong.club.ui.supermarket.adpter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.supermarket.entity.SMCommodityBean;

import java.util.List;

/**
 * @author Mr_Peng
 * @created at 2017-09-05.
 * @describe: null
 */

public class SMAdapter extends DefaultAdapter<SMCommodityBean> {
    @Override
    public BaseHolder<SMCommodityBean> getHolder(View v) {
        return new SMHolder(v, mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_dianpu;
    }

    public SMAdapter(List<SMCommodityBean> mDatas, Context context) {
        super(mDatas, context);
    }
}
