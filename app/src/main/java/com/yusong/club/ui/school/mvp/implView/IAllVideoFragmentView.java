package com.yusong.club.ui.school.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.school.teacher.bean.VideoAlbum;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/9.
 */

public interface IAllVideoFragmentView extends BaseView{
    void getVideoAlbumList(List<VideoAlbum> data);
    void closeRefreshing();
}
