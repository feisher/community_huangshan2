package com.yusong.community.ui.school.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.school.teacher.bean.VideoAlbum;

/**
 * Created by ruanjian on 2017/3/25.
 */

public interface IVideoDetailActivityView extends BaseView{
    void getAllVideo(VideoAlbum data);
    void closeRefresh();
    void  deleteAllVideo(String data);
}
