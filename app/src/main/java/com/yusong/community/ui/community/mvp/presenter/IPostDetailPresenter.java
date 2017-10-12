package com.yusong.community.ui.community.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * Created by feisher on 2017/1/10.
 */

public interface IPostDetailPresenter extends BasePresenter {
    void queryPostComments(int postId,int offset,int limit);
    void createPostComment(int postId,String content);
}
