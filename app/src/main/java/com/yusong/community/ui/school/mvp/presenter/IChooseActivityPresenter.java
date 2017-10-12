package com.yusong.community.ui.school.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * Created by feisher on 2017/2/24.
 */

public interface IChooseActivityPresenter extends BasePresenter{
    void querySchoolList(String token,int offset,int limit);
    void selectRole(String token,int schoolId,int roleId,int type);
}
