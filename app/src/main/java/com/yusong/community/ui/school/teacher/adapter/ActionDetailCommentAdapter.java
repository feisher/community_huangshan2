package com.yusong.community.ui.school.teacher.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.school.mvp.entity.DetailComment;
import com.yusong.community.ui.school.teacher.holder.CommentDetailHolder;

import java.util.List;

/**
 * Created by admin on 2017/1/13.
 */
public class ActionDetailCommentAdapter extends DefaultAdapter<DetailComment> {



    public ActionDetailCommentAdapter(List<DetailComment> mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public BaseHolder<DetailComment> getHolder(View v) {
        return new CommentDetailHolder(v, mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_action_comment;
    }
}
