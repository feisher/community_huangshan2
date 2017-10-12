package com.yusong.community.ui.school.teacher.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.school.school.bean.Comment;
import com.yusong.community.ui.school.teacher.holder.CommentHolder;

import java.util.List;

/**
 * Created by admin on 2017/1/13.
 */
public class ActionCommentAdapter extends DefaultAdapter<Comment> {



    public ActionCommentAdapter(List<Comment> mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public BaseHolder<Comment> getHolder(View v) {
        return new CommentHolder(v, mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_action_comment;
    }
}
