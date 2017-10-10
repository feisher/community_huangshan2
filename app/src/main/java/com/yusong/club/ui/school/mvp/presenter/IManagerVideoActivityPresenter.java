package com.yusong.club.ui.school.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by ruanjian on 2017/4/7.
 */

public interface IManagerVideoActivityPresenter extends BasePresenter {
    void getClazzVideoList(String token, int albumId);
   void deleteVideo(String token,String videoIds,int albumId);
}
