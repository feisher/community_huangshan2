package com.yusong.club.ui.school.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.school.teacher.bean.ClassHomework;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/10.
 */

public interface IHomeworkActivityView extends BaseView{
    void getClassHomework(List<ClassHomework> data);
    void closeRefresh();
}
