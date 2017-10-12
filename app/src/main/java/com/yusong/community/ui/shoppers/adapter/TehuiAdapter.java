package com.yusong.community.ui.shoppers.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.shoppers.bean.TuiJianBean;
import com.yusong.community.ui.shoppers.holder.TehuiHolder;

import java.util.List;

/**
 * @author Mr_Peng
 *         created at 2017/3/6 8:38.
 */

public class TehuiAdapter extends DefaultAdapter<TuiJianBean> {


    public TehuiAdapter(List<TuiJianBean> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public BaseHolder<TuiJianBean> getHolder(View v) {
        return new TehuiHolder(v, mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_shop_home_qianggou;
    }
}
