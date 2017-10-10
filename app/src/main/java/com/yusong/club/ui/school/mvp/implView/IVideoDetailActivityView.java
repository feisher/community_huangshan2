package com.yusong.club.ui.school.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.school.teacher.bean.VideoAlbum;

/**
 * Created by ruanjian on 2017/3/25.
 */

public interface IVideoDetailActivityView extends BaseView{
    void getAllVideo(VideoAlbum data);
    void closeRefresh();
    void  deleteAllVideo(String data);
}
