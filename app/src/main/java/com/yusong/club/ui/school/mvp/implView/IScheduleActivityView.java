package com.yusong.club.ui.school.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.school.teacher.bean.SubjectTable;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/8.
 */

public interface IScheduleActivityView extends BaseView{
    void getAllTable(List<SubjectTable> data);
}
