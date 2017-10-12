package com.yusong.community.ui.school.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * Created by feisher on 2017/2/24.
 */

public interface IParentHomeFragmentPresenter extends BasePresenter{
    void queryGuardianNoticeList(String token, int offset, int limit);
     void querySchoolIntro(String token);
}
