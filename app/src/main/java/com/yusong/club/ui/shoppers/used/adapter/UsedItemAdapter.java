package com.yusong.club.ui.shoppers.used.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.shoppers.used.bean.UsedBean;
import com.yusong.club.ui.shoppers.used.holder.UsedHolder;

import java.util.List;


/**
 * @author Mr_Peng
 *         crated at on 2017/3/20.
 *         描述: null
 */

public class UsedItemAdapter extends DefaultAdapter<UsedBean> {

    public UsedItemAdapter(List<UsedBean> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public BaseHolder getHolder(View v) {
        return new UsedHolder(v, mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_used_item;
    }

}
