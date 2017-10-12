package com.yusong.community.ui.me.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * Created by feisher on 2017/1/21.
 */

public interface IMeFragmentPresenter extends BasePresenter {
    void queryUserInfo(String token);
    void signIn();
}
