package com.yusong.community.ui.shoppers.adapter;


import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.shoppers.bean.QiangGouBean;
import com.yusong.community.ui.shoppers.holder.QianggouHolder;

import java.util.List;

/**
 * @author Mr_Peng
 *         created at 2017/3/3 15:06.
 */

public class QianggouAdapter extends DefaultAdapter<QiangGouBean> {

    public QianggouAdapter(List<QiangGouBean> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public BaseHolder<QiangGouBean> getHolder(View v) {
        return new QianggouHolder(v,mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_shop_home_qianggou;
    }
}
