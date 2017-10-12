package com.yusong.community.ui.shoppers.used.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.shoppers.used.bean.UsedCommentBean;
import com.yusong.community.ui.shoppers.used.holder.CommentHolder;

import java.util.List;

/**
 * @author Mr_Peng
 *         crated at on 2017/3/22.
 *         描述: null
 */

public class CommentAdapter extends DefaultAdapter<UsedCommentBean> {


    public CommentAdapter(List<UsedCommentBean> mDatas, Context context) {
        super(mDatas, context);
    }

    @Override
    public BaseHolder<UsedCommentBean> getHolder(View v) {
        return new CommentHolder(v,mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_used_comment;
    }
}
