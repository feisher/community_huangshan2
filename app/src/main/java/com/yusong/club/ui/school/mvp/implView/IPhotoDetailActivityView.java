package com.yusong.club.ui.school.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.school.teacher.bean.PhotoAlbum;

/**
 * Created by ruanjian on 2017/3/22.
 */

public interface IPhotoDetailActivityView extends BaseView {
    void closeRefreshing();
    void setPhotList(PhotoAlbum list);
    void deleteAllPhoto(String  data);
}
