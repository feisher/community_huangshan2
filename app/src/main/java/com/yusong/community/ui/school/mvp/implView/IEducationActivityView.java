package com.yusong.community.ui.school.mvp.implView;

import com.yusong.community.mvp.implView.BaseView;
import com.yusong.community.ui.school.teacher.bean.StudyVideo;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/27.
 */

public interface IEducationActivityView extends BaseView {
     void deleteVideo(String data);
     void  closeRefreshing();
     void   getVideoAlbumList(List<StudyVideo>data );
}
