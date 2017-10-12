package com.yusong.community.ui.school.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.school.teacher.bean.ClassHomework;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/10.
 */

public interface IHomeworkActivityView extends BaseView{
    void getClassHomework(List<ClassHomework> data);
    void closeRefresh();
}
