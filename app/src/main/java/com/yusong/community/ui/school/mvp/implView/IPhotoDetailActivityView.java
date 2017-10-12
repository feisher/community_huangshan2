package com.yusong.community.ui.school.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.school.teacher.bean.PhotoAlbum;

/**
 * Created by ruanjian on 2017/3/22.
 */

public interface IPhotoDetailActivityView extends BaseView {
    void closeRefreshing();
    void setPhotList(PhotoAlbum list);
    void deleteAllPhoto(String  data);
}
