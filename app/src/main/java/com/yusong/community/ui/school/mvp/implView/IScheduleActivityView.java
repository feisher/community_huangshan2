package com.yusong.community.ui.school.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.school.teacher.bean.SubjectTable;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/8.
 */

public interface IScheduleActivityView extends BaseView{
    void getAllTable(List<SubjectTable> data);
}
