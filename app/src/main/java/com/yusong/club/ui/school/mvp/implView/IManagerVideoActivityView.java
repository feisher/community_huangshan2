package com.yusong.club.ui.school.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.school.teacher.bean.VideoAlbum;

/**
 * Created by ruanjian on 2017/4/7.
 */

public interface IManagerVideoActivityView extends BaseView {
    void setVideoList(VideoAlbum list);
  void   closeRefreshing();
    void deleteVideo(String data);

}
