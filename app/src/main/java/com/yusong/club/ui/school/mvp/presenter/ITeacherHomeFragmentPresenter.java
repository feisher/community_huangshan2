package com.yusong.club.ui.school.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by feisher on 2017/2/24.
 */

public interface ITeacherHomeFragmentPresenter extends BasePresenter{
    void queryTeacherNoticeList(String token, int offset, int limit);
     void querySchoolIntro(String token);
}
