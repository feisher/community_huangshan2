package com.yusong.club.ui.school.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by ruanjian on 2017/3/13.
 */

public interface ICorrelationFragmentPresenter  extends BasePresenter{
    void studyVideoList(String token,int offset, int limit);

}