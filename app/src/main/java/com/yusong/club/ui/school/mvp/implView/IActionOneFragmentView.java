package com.yusong.club.ui.school.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.school.mvp.entity.GoodAction;
import com.yusong.club.ui.school.school.bean.ActivityBean;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/10.
 */

public interface IActionOneFragmentView extends BaseView{
    void getactivityList(List<ActivityBean> data);
    void closeRefreshing();
    void getGoodAction(GoodAction data);

}
