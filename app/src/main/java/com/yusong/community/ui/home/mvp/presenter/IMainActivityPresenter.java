package com.yusong.community.ui.home.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * Created by feisher on 2017/1/21.
 */

public interface IMainActivityPresenter extends BasePresenter {
    void queryUserInfo(String token);
}
