package com.yusong.club.ui.school.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by ruanjian on 2017/3/10.
 */

public interface IHomeworkActivityPresenter extends BasePresenter{
   void searchClassHomework(String token,String day);
}
