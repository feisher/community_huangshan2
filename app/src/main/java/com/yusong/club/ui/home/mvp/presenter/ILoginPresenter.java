package com.yusong.club.ui.home.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by quaner on 16/12/30.
 */

public interface ILoginPresenter extends BasePresenter {

    void login(String username, String pswd , int agentId);

}
