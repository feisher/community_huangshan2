package com.yusong.club.ui.school.mvp.implView;

import com.yusong.club.mvp.implView.BaseView;
import com.yusong.club.ui.school.teacher.bean.StudyVideo;

import java.util.List;

/**
 * Created by ruanjian on 2017/3/13.
 */

public interface ICorrelationFragmentView extends BaseView{
    void getVideoAlbumList(List<StudyVideo> data);
    void closeRefreshing();
}
