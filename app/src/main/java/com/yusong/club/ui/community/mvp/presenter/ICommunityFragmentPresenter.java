package com.yusong.club.ui.community.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by feisher on 2017/1/14.
 */
public interface ICommunityFragmentPresenter extends BasePresenter{
    void queryPostCatogrey(String token);
    void commitLikePosts(int id);
}
