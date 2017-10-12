package com.yusong.community.ui.school.mvp.presenter;

import com.yusong.community.mvp.BasePresenter;

/**
 * Created by ruanjian on 2017/3/22.
 */

public interface IEditPhotoActivityPresenter extends BasePresenter {
    void editPhoto(String token, int albumId, String memo,String albumName);
}
