package com.yusong.community.ui.school.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * Created by ruanjian on 2017/3/21.
 */

public interface IVideoDetailActivityPresenter extends BasePresenter {
    void getClazzVideoList(String token, int albumId);
     void deleteAllVideo(String token,int albumId);
}
