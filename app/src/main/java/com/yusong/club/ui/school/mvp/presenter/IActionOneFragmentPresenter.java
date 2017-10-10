package com.yusong.club.ui.school.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by ruanjian on 2017/3/10.
 */

public interface IActionOneFragmentPresenter   extends BasePresenter{
 void    activityList(String token,int categoryId,int offset,int limit);
    void   getGoodAction(String token,int activityId);
}
