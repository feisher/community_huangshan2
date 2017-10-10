package com.yusong.club.ui.school.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.school.teacher.bean.PhotoAlbum;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/9.
 */

public interface IAllPhotoFragmentView extends BaseView{
    void getPhotoAlbumList(List<PhotoAlbum> data);
    void closeRefreshing();
}
