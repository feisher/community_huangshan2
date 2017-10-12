package com.yusong.community.ui.school.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.school.teacher.bean.VideoAlbum;

/**
 * Created by ruanjian on 2017/4/7.
 */

public interface IManagerVideoActivityView extends BaseView {
    void setVideoList(VideoAlbum list);
  void   closeRefreshing();
    void deleteVideo(String data);

}
