package com.yusong.club.ui.school.teacher.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.school.school.bean.Comment;
import com.yusong.club.ui.school.teacher.holder.CommentHolder;

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
