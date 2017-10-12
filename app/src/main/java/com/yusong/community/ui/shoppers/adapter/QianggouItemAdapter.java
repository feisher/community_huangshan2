package com.yusong.community.ui.shoppers.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.shoppers.bean.QiangGouBean;
import com.yusong.community.ui.shoppers.holder.QianggouItemHolder;

import java.util.List;

/**
 * @author Mr_Peng
 *         created at 2017/3/4 12:48.
 */

public class QianggouItemAdapter extends DefaultAdapter<QiangGouBean.Categorys> {

    public QianggouItemAdapter(List<QiangGouBean.Categorys> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public BaseHolder getHolder(View v) {
        return new QianggouItemHolder(v, mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_shop_qianggou;
    }

}
