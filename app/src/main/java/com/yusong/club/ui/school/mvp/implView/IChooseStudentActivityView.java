package com.yusong.club.ui.school.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.school.mvp.entity.StuList;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/18.
 */

public interface IChooseStudentActivityView extends BaseView {
    void getStuList(List<StuList> data);
    void closeRefresh();
}
