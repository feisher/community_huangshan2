package com.yusong.community.ui.school.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.school.teacher.bean.AllTeacher;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/8.
 */

public interface IDutyTeacherActivityView extends BaseView{
    void getAllTeacher(List<AllTeacher> data);
    void setOndutyTeacher(String data);
void closeRefresh();
}
