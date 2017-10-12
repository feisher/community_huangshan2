package com.yusong.community.ui.school.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * Created by ruanjian on 2017/3/11.
 */

public interface IAddworkActivityPresenter extends BasePresenter {
    void createClasswork(String token, int courseId,String content,String workDate);
  void   searchCourseList(String token);
}
