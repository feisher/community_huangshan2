package com.yusong.club.ui.school.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.school.teacher.bean.AllTeacher;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/8.
 */

public interface IDutyTeacherActivityView extends BaseView{
    void getAllTeacher(List<AllTeacher> data);
    void setOndutyTeacher(String data);
void closeRefresh();
}
