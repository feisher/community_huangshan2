package com.yusong.club.ui.school.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by ruanjian on 2017/3/8.
 */

public interface IDutyTeacherActivityPresenter extends BasePresenter{
    void  searchTeacherList(String token);
    void setOndutyTeacher(String token,int teacherId);
}
