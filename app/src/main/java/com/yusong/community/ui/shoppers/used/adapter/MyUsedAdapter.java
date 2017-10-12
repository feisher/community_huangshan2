package com.yusong.community.ui.shoppers.used.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.shoppers.used.bean.MyUsedBean;
import com.yusong.community.ui.shoppers.used.holder.MyUsedHolder;

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
