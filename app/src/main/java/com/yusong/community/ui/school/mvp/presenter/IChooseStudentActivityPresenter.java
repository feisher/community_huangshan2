package com.yusong.community.ui.school.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * Created by ruanjian on 2017/3/18.
 */

public interface IChooseStudentActivityPresenter extends BasePresenter {

    void ueryStuList(String token,String studentName,int offset,int limit);

}
