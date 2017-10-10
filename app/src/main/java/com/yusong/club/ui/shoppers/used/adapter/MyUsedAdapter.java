package com.yusong.club.ui.shoppers.used.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.shoppers.used.bean.MyUsedBean;
import com.yusong.club.ui.shoppers.used.holder.MyUsedHolder;

import java.util.List;

/**
 * @author Mr_Peng
 *         crated at on 2017/3/20.
 *         描述: null
 */

public class MyUsedAdapter extends DefaultAdapter<MyUsedBean> {

    @Override
    public BaseHolder<MyUsedBean> getHolder(View v) {
        return new MyUsedHolder(v, mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_my_used;
    }


    public MyUsedAdapter(List<MyUsedBean> mDatas, Context context) {
        super(mDatas, context);
    }
}
