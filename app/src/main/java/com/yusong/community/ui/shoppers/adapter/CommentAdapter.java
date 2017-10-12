package com.yusong.community.ui.shoppers.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.shoppers.bean.PinLunBean;
import com.yusong.community.ui.shoppers.holder.CommentHolder;

import java.util.List;

/**
 * @author Mr_Peng
 *         created at 2017/3/9 11:36.
 *         评论适配器
 */

public class CommentAdapter extends DefaultAdapter<PinLunBean> {

    public CommentAdapter(List<PinLunBean> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public BaseHolder<PinLunBean> getHolder(View v) {
        return new CommentHolder(v, mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_shop_pinlun;
    }
}
