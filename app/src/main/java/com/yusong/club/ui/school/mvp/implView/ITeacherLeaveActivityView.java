package com.yusong.club.ui.school.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.school.mvp.entity.TeacherLeave;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/27.
 */

public interface ITeacherLeaveActivityView extends BaseView {
    void getTeacherLeave(List<TeacherLeave> data);
    void closeRefresh();
    void judgeServeLeave(String data);
}
