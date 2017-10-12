package com.yusong.community.ui.school.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.school.mvp.entity.TeacherLeave;

import java.util.List;

/**
 * Created by ruanjian on 2017/4/1.
 */

public interface IShowLeaveListActivityView extends BaseView {
    void getLeaveParentInfo(List<TeacherLeave> data);
    void closeRefreshing();
}
