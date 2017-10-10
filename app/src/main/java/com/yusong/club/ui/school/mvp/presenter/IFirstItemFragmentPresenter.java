package com.yusong.club.ui.school.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by ruanjian on 2017/3/21.
 */

public interface IFirstItemFragmentPresenter extends BasePresenter{
    void getStuCommentList(String token,int period, int offset,  int limit);
}
