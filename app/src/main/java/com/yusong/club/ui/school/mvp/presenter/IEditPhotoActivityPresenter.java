package com.yusong.club.ui.school.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by ruanjian on 2017/3/22.
 */

public interface IEditPhotoActivityPresenter extends BasePresenter {
    void editPhoto(String token, int albumId, String memo,String albumName);
}