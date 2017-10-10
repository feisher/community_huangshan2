package com.yusong.club.ui.school.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.school.mvp.entity.TeacherLeave;

import java.util.List;

/**
 * Created by ruanjian on 2017/4/1.
 */

public interface IParentLeaveActivityView extends BaseView {
    void getLeaveParentInfo(List<TeacherLeave> data);
    void closeRefreshing();
}
