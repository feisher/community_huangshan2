package com.yusong.community.ui.community.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * Created by feisher on 2017/1/14.
 */
public interface ICommunityFragmentPresenter extends BasePresenter{
    void queryPostCatogrey(String token);
    void commitLikePosts(int id);
}
