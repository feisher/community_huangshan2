package com.yusong.community.ui.community.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * Created by feisher on 2017/1/10.
 */

public interface ICommunityItemPresenter extends BasePresenter {
    void requestGetCommubityItemFragementItem(int type, int offset, int limit);
//    void commitLikePosts(int id);
//    void postsComment(int id,String content);
}
