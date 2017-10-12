package com.yusong.community.ui.home.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * Created by quaner on 17/1/3.
 */

public interface IHomePresenter extends BasePresenter {
    void requestBanner();
    void queryRoleList(String token);
    void selectRole(String token,int schoolId,int id,int type);
}
