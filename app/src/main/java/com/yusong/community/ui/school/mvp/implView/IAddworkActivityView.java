package com.yusong.community.ui.school.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.school.teacher.bean.Course;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/11.
 */

public interface IAddworkActivityView extends BaseView{
    void createClassWork(String data);
    void getCourseList(List<Course> data);
}
