package com.yusong.community.ui.community.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.community.R;
import com.yusong.community.ui.base.BaseHolder;
import com.yusong.community.ui.base.DefaultAdapter;
import com.yusong.community.ui.community.hoder.PostCommentHolder;
import com.yusong.community.ui.community.mvp.entity.PostComment;

import java.util.List;

/**
 * Created by feisher on 2017/2/17.
 */
public class PostCommentsAdpter extends DefaultAdapter<PostComment> {


    public PostCommentsAdpter(List<PostComment> mPostCommentDatas, Context mContext) {
        super(mPostCommentDatas, mContext);
    }

    @Override
    public BaseHolder<PostComment> getHolder(View v) {
        return new PostCommentHolder(v, mContext);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_postdetail_comments;
    }
}
