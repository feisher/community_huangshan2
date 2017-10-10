package com.yusong.club.ui.school.mvp.presenter;

import com.yusong.club.mvp.BasePresenter;

/**
 * Created by ruanjian on 2017/3/22.
 */

public interface IManagerPhotoActivityPresenter extends BasePresenter {
    void getClazzPhotoList(String token, int albumId);

    void deletePhoto(String oken, String photoId,int albumId);
}
