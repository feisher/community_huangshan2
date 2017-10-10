package com.yusong.club.ui.shoppers.used.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * @author Mr_Peng
 *         crated at on 2017/3/22.
 *         描述: null
 */

public interface ImplCommentPreseter extends BasePresenter {
    void queryComment(int itemId, int offset, int limit);//查询评论

    void reply(int commentId, String content);//回复

    void comment(int itemId, String content);//评论
}
