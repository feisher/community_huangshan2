package com.yusong.community.ui.school.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * Created by ruanjian on 2017/3/8.
 */

public interface IDutyTeacherActivityPresenter extends BasePresenter{
    void  searchTeacherList(String token);
    void setOndutyTeacher(String token,int teacherId);
}
