package com.yusong.club.ui.school.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by ruanjian on 2017/3/13.
 */

public interface ICreateVideoActivityPresenter extends BasePresenter {
    void createVideo_album(String token, String albumName, String memo);
}