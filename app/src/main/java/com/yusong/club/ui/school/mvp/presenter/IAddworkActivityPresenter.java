package com.yusong.club.ui.school.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by ruanjian on 2017/3/11.
 */

public interface IAddworkActivityPresenter extends BasePresenter {
    void createClasswork(String token, int courseId,String content,String workDate);
  void   searchCourseList(String token);
}
