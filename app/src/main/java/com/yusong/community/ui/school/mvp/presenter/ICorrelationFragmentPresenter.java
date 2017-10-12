package com.yusong.community.ui.school.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * Created by ruanjian on 2017/3/13.
 */

public interface ICorrelationFragmentPresenter  extends BasePresenter{
    void studyVideoList(String token,int offset, int limit);

}
