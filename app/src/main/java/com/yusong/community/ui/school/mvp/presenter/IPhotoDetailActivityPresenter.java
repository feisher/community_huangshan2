package com.yusong.community.ui.school.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * Created by ruanjian on 2017/3/22.
 */

public interface IPhotoDetailActivityPresenter extends BasePresenter {
  void   getClazzPhotoList(String token,int albumId);
 void deleteAllPhoto(String token,int albumId);
}
