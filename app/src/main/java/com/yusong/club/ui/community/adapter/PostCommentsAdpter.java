package com.yusong.club.ui.community.adapter;

import android.content.Context;
import android.view.View;

import com.yusong.club.R;
import com.yusong.club.ui.base.BaseHolder;
import com.yusong.club.ui.base.DefaultAdapter;
import com.yusong.club.ui.community.hoder.PostCommentHolder;
import com.yusong.club.ui.community.mvp.entity.PostComment;

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
