package com.yusong.club.ui.school.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by ruanjian on 2017/3/28.
 */

public interface ITeacherMineFragmentaPresenter extends BasePresenter {
    void queryTeacherInfo(String token,Integer roleId);
}
