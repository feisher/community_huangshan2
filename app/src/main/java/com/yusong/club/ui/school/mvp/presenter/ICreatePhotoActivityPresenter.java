package com.yusong.club.ui.school.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by ruanjian on 2017/3/13.
 */

public interface ICreatePhotoActivityPresenter extends BasePresenter {
    void createPhoto_album(String token, String albumName, String memo);
}
