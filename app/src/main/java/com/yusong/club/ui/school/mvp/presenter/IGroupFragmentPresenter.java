package com.yusong.club.ui.school.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by ruanjian on 2017/3/13.
 */

public interface IGroupFragmentPresenter extends BasePresenter {
  void   chatGroupList(String token);
  void   memberGroupList(String token,int groupId);
}